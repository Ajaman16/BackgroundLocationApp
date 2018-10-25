package com.thunderx.locationapp.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.thunderx.locationapp.R;
import com.thunderx.locationapp.network.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyViewHolder> {

    private Context context;
    private List<User> userList;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.user_name)
        TextView userName;

        @BindView(R.id.user_phone)
        TextView phone;

        @BindView(R.id.user_email)
        TextView email;

        @BindView(R.id.user_dob)
        TextView dob;

        @BindView(R.id.profile_image)
        CircleImageView profileImage;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    public UsersAdapter(Context context, List<User> usersList) {
        this.context = context;
        this.userList = usersList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = userList.get(position);

        String name = user.getUserNameItem().getName();
        String phone = user.getPhone();
        String dob = user.getDobItem().getDate();
        String email = user.getEmail();
        String picURL = user.getPictureItem().getThumbnail();

        holder.userName.setText(name);
        holder.phone.setText(phone);
        holder.dob.setText(dob);
        holder.email.setText(email);

        Picasso.get()
                .load(picURL)
                .placeholder(R.drawable.placeholder_icon)
                .error(R.drawable.placeholder_icon)
                .into(holder.profileImage);

    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

}