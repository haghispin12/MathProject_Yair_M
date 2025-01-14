package com.example.mathproject_yair_m;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mathproject_yair_m.modals.User;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<User> users;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(User item);
    }

    public UserAdapter  (ArrayList<User> users, OnItemClickListener listener) {
        this.users = users;
        this.listener = listener;
    }


    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(users.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUsername ;
        TextView tvRate;
        ImageView ivUserImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvRate = itemView.findViewById(R.id.tvRate);
            ivUserImg = itemView.findViewById(R.id.ivUserImg);
        }

        @SuppressLint("SetTextI18n")
        public void bind(final User item, final OnItemClickListener
                listener)
        {
            tvUsername.setText(item.getUsername());
            Log.d("uir123",item.getBitmap()+"");
            ivUserImg.setImageBitmap(item.getBitmap());
            tvRate.setText(item.getRate()+"");
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }

    }
}
