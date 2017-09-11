package io.kimo.realm.data;

import java.util.List;

import io.realm.RealmObject;

public interface Repository<T extends RealmObject> {
    void add(T model);
    List<T> all();
}
