package io.kimo.realm.data.repository.realm;


import io.kimo.realm.data.model.Movie;
import io.kimo.realm.data.repository.RealmRepository;
import io.realm.Realm;

public class MovieRealmRepository extends RealmRepository<Movie> {
    public MovieRealmRepository(Realm realm, Class<Movie> clazz) {
        super(realm, clazz);
    }
}
