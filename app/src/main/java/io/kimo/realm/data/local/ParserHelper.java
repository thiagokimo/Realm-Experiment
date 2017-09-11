package io.kimo.realm.data.local;

import android.content.Context;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import io.kimo.realm.R;
import io.kimo.realm.data.model.Movie;
import io.kimo.realm.data.model.User;

public class ParserHelper {

    private Context mContext;

    public ParserHelper(@NonNull Context context) {
        mContext = context;
    }

    public Movie parseLocalMovieJsonFile() {
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.movie);
        Movie movie = new Movie();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

            if (jsonObject.has("Title")) {
                movie.setTitle(jsonObject.getString("Title"));
            }

            if (jsonObject.has("Genre")) {
                movie.setGenre(jsonObject.getString("Genre"));
            }

            if (jsonObject.has("Director")) {
                movie.setDirector(jsonObject.getString("Director"));
            }

            if (jsonObject.has("Actors")) {
                movie.setActors(jsonObject.getString("Actors"));
            }

            if (jsonObject.has("Plot")) {
                movie.setPlot(jsonObject.getString("Plot"));
            }

            if (jsonObject.has("Poster")) {
                movie.setPosterUrl(jsonObject.getString("Poster"));
            }

            return movie;

        } catch (IOException|JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<User> parseLocalUsersJsonFile() {
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.users);
        List<User> users = new ArrayList<>();
        try {
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                User user = new User();

                if (jsonObject.has("name")) {
                    user.setName(jsonObject.getString("name"));
                }

                if (jsonObject.has("email")) {
                    user.setEmail(jsonObject.getString("email"));
                }

                if (jsonObject.has("country")) {
                    user.setCountry(jsonObject.getString("country"));
                }

                if (jsonObject.has("company")) {
                    user.setCompany(jsonObject.getString("company"));
                }

                users.add(user);
            }

            return users;

        } catch (IOException|JSONException e) {
            e.printStackTrace();
            return users;
        }
    }
}
