package io.kimo.realm.data.repository.realm;

import io.kimo.realm.data.model.User;
import io.kimo.realm.data.repository.RealmRepository;
import io.realm.Realm;

public class UserRealmRepository extends RealmRepository<User> {
    public UserRealmRepository(Realm realm, Class<User> clazz) {
        super(realm, clazz);
    }
}
