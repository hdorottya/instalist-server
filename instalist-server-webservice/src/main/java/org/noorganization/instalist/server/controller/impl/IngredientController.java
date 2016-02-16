package org.noorganization.instalist.server.controller.impl;

import org.noorganization.instalist.server.controller.IIngredientController;
import org.noorganization.instalist.server.controller.IListController;
import org.noorganization.instalist.server.controller.IProductController;
import org.noorganization.instalist.server.controller.IRecipeController;
import org.noorganization.instalist.server.model.*;
import org.noorganization.instalist.server.support.exceptions.ConflictException;
import org.noorganization.instalist.server.support.exceptions.GoneException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class IngredientController implements IIngredientController {

    private EntityManager mManager;

    public void add(int _groupId, UUID _ingredientUUID, UUID _recipeUUID, UUID _productUUID,
                    float _amount, Instant _lastChanged)
            throws ConflictException, BadRequestException {
        EntityTransaction tx = mManager.getTransaction();
        tx.begin();
        DeviceGroup group = mManager.find(DeviceGroup.class, _groupId);

        Ingredient toCheck = getIngredientByGroupAndUUID(group, _ingredientUUID);
        if (toCheck != null) {
            tx.rollback();
            throw new ConflictException();
        }
        DeletedObject previousDeleted = getDeletedIngredientByGroupAndUUID(group, _ingredientUUID);
        if (previousDeleted != null && _lastChanged.isBefore(previousDeleted.getTime().toInstant())) {
            tx.rollback();
            throw new ConflictException();
        }
        IRecipeController recipeController = ControllerFactory.getRecipeController(mManager);
        Recipe newRecipe = recipeController.getRecipeByGroupAndUUID(group, _recipeUUID);
        IProductController productController = ControllerFactory.getProductController(mManager);
        Product newProduct = productController.getProductByGroupAndUUID(group, _productUUID);
        if (newProduct == null || newRecipe == null) {
            tx.rollback();
            throw new BadRequestException();
        }

        Ingredient toCreate = new Ingredient().withGroup(group);
        toCreate.setUUID(_ingredientUUID);
        toCreate.setRecipe(newRecipe);
        toCreate.setProduct(newProduct);
        toCreate.setAmount(_amount);
        toCreate.setUpdated(_lastChanged);
        mManager.persist(toCreate);

        tx.commit();
    }

    public void update(int _groupId, UUID _ingredientUUID, UUID _recipeUUID, UUID _productUUID,
                       Float _amount, Instant _lastChanged)
            throws ConflictException, GoneException, NotFoundException, BadRequestException {
        EntityTransaction tx = mManager.getTransaction();
        tx.begin();
        DeviceGroup group = mManager.find(DeviceGroup.class, _groupId);

        Ingredient toUpdate = getIngredient(group, _ingredientUUID, tx);
        if (toUpdate.getUpdated().isAfter(_lastChanged)) {
            tx.rollback();
            throw new ConflictException();
        }
        Recipe newRecipe = null;
        if (_recipeUUID != null) {
            IRecipeController recipeController = ControllerFactory.getRecipeController(mManager);
            newRecipe = recipeController.getRecipeByGroupAndUUID(group, _recipeUUID);
            if (newRecipe == null) {
                tx.rollback();
                throw new BadRequestException();
            }
        }
        Product newProduct = null;
        if (_productUUID != null) {
            IProductController productController = ControllerFactory.getProductController(mManager);
            newProduct = productController.getProductByGroupAndUUID(group, _productUUID);
            if (newProduct == null) {
                tx.rollback();
                throw new BadRequestException();
            }
        }

        if (newRecipe != null)
            toUpdate.setRecipe(newRecipe);
        if (newProduct != null)
            toUpdate.setProduct(newProduct);
        if (_amount != null)
            toUpdate.setAmount(_amount);
        toUpdate.setUpdated(_lastChanged);

        tx.commit();
    }

    public void delete(int _groupId, UUID _ingredientUUID) throws GoneException, NotFoundException {
        EntityTransaction tx = mManager.getTransaction();
        tx.begin();
        DeviceGroup group = mManager.find(DeviceGroup.class, _groupId);

        Ingredient toDelete = getIngredient(group, _ingredientUUID, tx);
        DeletedObject oldProduct = new DeletedObject().withGroup(group);
        oldProduct.setUUID(_ingredientUUID);
        oldProduct.setType(DeletedObject.Type.INGREDIENT);
        oldProduct.setTime(Date.from(Instant.now()));
        mManager.persist(oldProduct);
        mManager.remove(toDelete);

        tx.commit();
    }

    public Ingredient getIngredientByGroupAndUUID(DeviceGroup _group, UUID _uuid) {
        TypedQuery<Ingredient> ingredientQuery = mManager.createQuery("select i from Ingredient i "+
                "where i.UUID = :uuid and i.group = :group", Ingredient.class);
        ingredientQuery.setParameter("uuid", _uuid);
        ingredientQuery.setParameter("group", _group);
        List<Ingredient> ingredientResult = ingredientQuery.getResultList();
        if (ingredientResult.size() == 0)
            return null;
        return ingredientResult.get(0);
    }

    public DeletedObject getDeletedIngredientByGroupAndUUID(DeviceGroup _group, UUID _uuid) {
        TypedQuery<DeletedObject> delIngredientQuery = mManager.createQuery("select do from " +
                        "DeletedObject do where do.group = :group and do.UUID = :uuid and " +
                        "do.type = :type order by do.time desc",
                DeletedObject.class);
        delIngredientQuery.setParameter("group", _group);
        delIngredientQuery.setParameter("uuid", _uuid);
        delIngredientQuery.setParameter("type", DeletedObject.Type.INGREDIENT);
        delIngredientQuery.setMaxResults(1);
        List<DeletedObject> delEntryResult = delIngredientQuery.getResultList();
        if (delEntryResult.size() == 0)
            return null;
        return delEntryResult.get(0);
    }

    IngredientController(EntityManager _manager) {
        mManager = _manager;
    }

    private Ingredient getIngredient(DeviceGroup _group, UUID _ingredientUUID,
                                     EntityTransaction _tx)
            throws GoneException, NotFoundException {
        Ingredient ingredient = getIngredientByGroupAndUUID(_group, _ingredientUUID);
        if (ingredient == null) {
            if (getDeletedIngredientByGroupAndUUID(_group, _ingredientUUID) == null) {
                _tx.rollback();
                throw new NotFoundException();
            }
            _tx.rollback();
            throw new GoneException();
        }
        return ingredient;
    }
}
