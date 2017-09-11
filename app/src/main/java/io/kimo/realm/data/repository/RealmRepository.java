package io.kimo.realm.data.repository;

import io.kimo.realm.data.Repository;
import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public abstract class RealmRepository<T extends RealmObject> implements Repository<T> {
    protected Realm mRealm;
    protected Class<T> mClass;

    public RealmRepository(Realm realm, Class<T> clazz) {
        mRealm = realm;
        mClass = clazz;
    }

    public Realm getRealmInstance() {
        return mRealm;
    }

    @Override
    public void add(T model) {
        mRealm.executeTransactionAsync(realm1 -> realm1.insertOrUpdate(model));
    }

    public RealmResults<T> all() {
        return mRealm.where(mClass).findAllAsync();
    }
}
