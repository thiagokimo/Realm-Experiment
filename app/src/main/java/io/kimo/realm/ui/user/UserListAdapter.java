package io.kimo.realm.ui.user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.kimo.realm.R;
import io.kimo.realm.data.model.User;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.ViewHolder> {

    private List<User> mItems = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = mItems.get(position);
        holder.name.setText(user.getName());
        holder.email.setText(user.getEmail());
        holder.country.setText(user.getCountry());
        holder.company.setText(user.getCompany());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    void setUsers(List<User> users) {
        mItems = users;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, country, company;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            country = itemView.findViewById(R.id.country);
            company = itemView.findViewById(R.id.company);
        }
    }
}
