package org.noorganization.instalist.server.controller.impl;

import org.noorganization.instalist.server.controller.ICategoryController;
import org.noorganization.instalist.server.controller.IListController;
import org.noorganization.instalist.server.model.Category;
import org.noorganization.instalist.server.model.DeletedObject;
import org.noorganization.instalist.server.model.DeviceGroup;
import org.noorganization.instalist.server.model.ShoppingList;
import org.noorganization.instalist.server.support.exceptions.ConflictException;
import org.noorganization.instalist.server.support.exceptions.GoneException;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ListController implements IListController {

    private EntityManager mManager;

    ListController(EntityManager _manager) {
        mManager = _manager;
    }

    public void add(int _groupId, UUID _listUUID, String _name, UUID _category, Date _lastChanged)
            throws ConflictException {
        ICategoryController categoryController = ControllerFactory.getCategoryController(mManager);

        EntityTransaction tx = mManager.getTransaction();
        tx.begin();
        DeviceGroup group = mManager.find(DeviceGroup.class, _groupId);
        ShoppingList found = getListByGroupAndUUID(group, _listUUID);
        if (found != null) {
            tx.rollback();
            throw new NotFoundException();
        }
        Category cat = null;
        if (_category != null) {
            cat = getCategory(_groupId, _category, categoryController, tx);
        }

        ShoppingList newList = new ShoppingList().withGroup(group);
        newList.setName(_name);
        newList.setUUID(_listUUID);
        newList.setCategory(cat);
        newList.setUpdated(_lastChanged);
        mManager.persist(newList);
        tx.commit();
    }

    public void update(int _groupId, UUID _listUUID, String _name, UUID _category,
                       boolean _removeCategory, Date _lastChanged)
            throws ConflictException, GoneException, NotFoundException {
        ICategoryController categoryController = ControllerFactory.getCategoryController(mManager);

        EntityTransaction tx = mManager.getTransaction();
        tx.begin();
        DeviceGroup group = mManager.find(DeviceGroup.class, _groupId);
        ShoppingList listToUpdate = getListByGroupAndUUID(group, _listUUID);
        if (listToUpdate == null) {
            if (getDeletedListByGroupAndUUID(group, _listUUID) != null) {
                System.err.println("List " + _listUUID.toString() + " was deleted.");
                tx.rollback();
                throw new GoneException();
            }
            tx.rollback();
            throw new NotFoundException();
        }
        if (listToUpdate.getUpdated().after(_lastChanged)) {
            tx.rollback();
            throw new ConflictException();
        }

        Category cat = null;
        if (_category != null)
            cat = getCategory(_groupId, _category, categoryController, tx);

        if (_name != null)
            listToUpdate.setName(_name);
        if (cat != null)
            listToUpdate.setCategory(cat);
        else if (_removeCategory)
            listToUpdate.setCategory(null);
        listToUpdate.setUpdated(_lastChanged);

        tx.commit();
    }

    public void delete(int _groupId, UUID _listUUID) throws GoneException, NotFoundException {

    }

    public ShoppingList getListByGroupAndUUID(DeviceGroup _group, UUID _uuid) {
        TypedQuery<ShoppingList> listQuery = mManager.createQuery("select sl from ShoppingList sl" +
                " where sl.group = :group and sl.UUID = :uuid", ShoppingList.class);
        listQuery.setParameter("group", _group);
        listQuery.setParameter("uuid", _uuid);
        List<ShoppingList> lists = listQuery.getResultList();
        if (lists.size() == 0)
            return null;
        return lists.get(0);
    }

    DeletedObject getDeletedListByGroupAndUUID(DeviceGroup _group, UUID _uuid) {
        TypedQuery<DeletedObject> listQuery = mManager.createQuery("select do from " +
                "DeletedObject do where do.group = :group and do.UUID = :uuid and do.type = :type",
                DeletedObject.class);
        listQuery.setParameter("group", _group);
        listQuery.setParameter("uuid", _uuid);
        listQuery.setParameter("type", DeletedObject.Type.LIST);
        List<DeletedObject> lists = listQuery.getResultList();
        if (lists.size() == 0)
            return null;
        return lists.get(0);
    }

    private Category getCategory(int _groupId, UUID _category,
                                 ICategoryController _categoryController, EntityTransaction _tx) {
        Category cat;
        cat = _categoryController.getCategoryByGroupAndUUID(_groupId, _category);
        if (cat == null) {
            _tx.rollback();
            throw new ConflictException();
        }
        return cat;
    }
}