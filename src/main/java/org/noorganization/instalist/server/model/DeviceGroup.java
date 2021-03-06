/*
 * Copyright 2016 Tino Siegmund, Michael Wodniok
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.noorganization.instalist.server.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "devicegroups")
@EntityListeners({DeviceGroup.PostUpdateEventListener.class})
public class DeviceGroup {
    private int    mId;
    private String mReadableId;
    private Date   mUpdated;
    private Date   mCreated;

    private Set<Category> mCategories;
    private Set<DeletedObject> mDeletedObjects;
    private Set<Device> mDevices;
    private Set<Ingredient> mIngredients;
    private Set<ShoppingList> mLists;
    private Set<ListEntry> mListEntries;
    private Set<Product> mProducts;
    private Set<Recipe> mRecipes;
    private Set<Tag> mTags;
    private Set<TaggedProduct> mTaggedProducts;
    private Set<Unit> mUnits;

    public DeviceGroup() {
        long currentTime = System.currentTimeMillis();
        mCreated = new Date(currentTime);
        mUpdated = new Date(currentTime);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    public int getId() {
        return mId;
    }

    public void setId(int _id) {
        mId = _id;
    }

    public DeviceGroup withId(int _id) {
        setId(_id);
        return this;
    }

    @Column(name = "readableid", nullable = true, length = 6, columnDefinition = "char(6)")
    public String getReadableId() {
        return mReadableId;
    }

    public void setReadableId(String _readableId) {
        mReadableId = _readableId;
    }

    public DeviceGroup withReadableId(String _readableId) {
        setReadableId(_readableId);
        return this;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated", columnDefinition = "TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)",
            nullable = false)
    public Date getUpdated() {
        if (mUpdated != null && mUpdated.getClass() == Timestamp.class) {
            Timestamp current = (Timestamp) mUpdated;
            return new Date(current.getTime() + (current.getNanos() / 1000000));
        }
        return mUpdated;
    }

    public void setUpdated(Date _updated) {
        mUpdated = _updated;
    }

    public DeviceGroup withUpdated(Date _updated) {
        setUpdated(_updated);
        return this;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", columnDefinition="TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)",
            /*updatable = false,*/ nullable = false)
    public Date getCreated() {
        if (mCreated != null && mCreated.getClass() == Timestamp.class) {
            Timestamp current = (Timestamp) mCreated;
            return new Date(current.getTime() + (current.getNanos() / 1000000));
        }
        return mCreated;
    }

    public void setCreated(Date _created) {
        mCreated = _created;
    }

    public DeviceGroup withCreated(Date _created) {
        setCreated(_created);
        return this;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Device> getDevices() {
        return mDevices;
    }

    public void setDevices(Set<Device> _devices) {
        mDevices = _devices;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(
            Set<Category> _categories) {
        mCategories = _categories;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<ShoppingList> getLists() {
        return mLists;
    }

    public void setLists(Set<ShoppingList> _lists) {
        mLists = _lists;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<DeletedObject> getDeletedObjects() {
        return mDeletedObjects;
    }

    public void setDeletedObjects(
            Set<DeletedObject> _deletedObjects) {
        mDeletedObjects = _deletedObjects;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Unit> getUnits() {
        return mUnits;
    }

    public void setUnits(Set<Unit> _units) {
        mUnits = _units;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<ListEntry> getListEntries() {
        return mListEntries;
    }

    public void setListEntries(
            Set<ListEntry> _listEntries) {
        mListEntries = _listEntries;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Product> getProducts() {
        return mProducts;
    }

    public void setProducts(Set<Product> _products) {
        mProducts = _products;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Recipe> getRecipes() {
        return mRecipes;
    }

    public void setRecipes(Set<Recipe> _recipes) {
        mRecipes = _recipes;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Ingredient> getIngredients() {
        return mIngredients;
    }

    public void setIngredients(Set<Ingredient> _ingredients) {
        mIngredients = _ingredients;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<Tag> getTags() {
        return mTags;
    }

    public void setTags(Set<Tag> _tags) {
        mTags = _tags;
    }

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group", orphanRemoval = true)
    public Set<TaggedProduct> getTaggedProducts() {
        return mTaggedProducts;
    }

    public void setTaggedProducts(Set<TaggedProduct> _taggedProducts) {
        mTaggedProducts = _taggedProducts;
    }


    @Override
    public boolean equals(Object _o) {
        if (this == _o)
            return true;
        if (_o == null || getClass() != _o.getClass())
            return false;

        DeviceGroup that = (DeviceGroup) _o;

        if (mId != that.mId)
            return false;
        if (mReadableId != null ? !mReadableId.equals(that.mReadableId) : that.mReadableId != null)
            return false;
        if (mUpdated != null ? !mUpdated.equals(that.mUpdated) : that.mUpdated != null)
            return false;
        return mCreated != null ? mCreated.equals(that.mCreated) : that.mCreated == null;
    }

    @Override
    public int hashCode() {
        return mId;
    }

    @Override
    public String toString() {
        return "DeviceGroup{" +
                "mId=" + mId +
                ", mReadableId='" + mReadableId + '\'' +
                ", mUpdated=" + mUpdated +
                ", mCreated=" + mCreated +
                '}';
    }

    /**
     * This entity-listener updates the 'updated' field of {@link DeviceGroup} after updates
     * automatically.
     * Created by damihe on 12.02.16.
     */
    public static class PostUpdateEventListener {

        @PreUpdate
        public void onUpdate(final DeviceGroup _group) {
            _group.setUpdated(new Date(System.currentTimeMillis()));
        }
    }
}
