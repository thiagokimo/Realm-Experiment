package io.kimo.realm.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

public class SharedPreferencesHelper {
    private static final String TAG = "SharedPreferencesHelper";

    private static final String FILE_NAME = "shared_preferences_file";
    private SharedPreferences mSharedPref;

    private static final String DID_INITIAL_SYNC = "DID_INITIAL_SYNC";

    public SharedPreferencesHelper(@NonNull Context context) {
        mSharedPref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
    }

    public boolean didInitialSync() {
        return mSharedPref.getBoolean(DID_INITIAL_SYNC, false);
    }

    public void setDidInitialSync(boolean didInitialSync) {
        mSharedPref.edit()
                .putBoolean(DID_INITIAL_SYNC, didInitialSync)
                .apply();
    }

    public void clear() {
        mSharedPref.edit()
                .clear()
                .apply();
    }

}
