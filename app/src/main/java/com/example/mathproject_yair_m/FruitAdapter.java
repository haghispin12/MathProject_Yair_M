//package com.example.mathproject_yair_m;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.mathproject_yair_m.modals.Fruit;
//
//import java.util.ArrayList;
//
//public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
//    private ArrayList<Fruit> fruits;
//    private OnItemClickListener listener;
//
//    public interface OnItemClickListener {
//        void onItemClick(Fruit item);
//    }
//
//    public FruitAdapter  (ArrayList<Fruit> fruits, OnItemClickListener listener) {
//        this.fruits = fruits;
//        this.listener = listener;
//    }
//
//
//    @NonNull
//    @Override
//    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_view, parent, false);
//
//        return new ViewHolder(view);
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(fruits.get(position), listener);
//    }
//
//    @Override
//    public int getItemCount() {
//        return fruits.size();
//    }
//
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        TextView tvFruitName ;
//        ImageView ivFruitImg;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            tvFruitName = itemView.findViewById(R.id.tvFruitName);
//            ivFruitImg = itemView.findViewById(R.id.ivFruitImg);
//        }
//
//        public void bind(final Fruit item, final OnItemClickListener
//                listener)      {
//            tvFruitName.setText(item.getName());
//            ivFruitImg.setImageResource(item.getImage());
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    listener.onItemClick(item);
//                }
//            });
//        }
//
//    }
//}
