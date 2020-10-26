package com.example.spaceapplication.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.spaceapplication.R;

import java.util.Arrays;
import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.Holder> {

    private LayoutInflater inflater;
    private List<Item> items;

    public RecycleAdapter(List<Item> items, Context context) {
        Item[] itemsArray = items.toArray(new Item[0]);
        Arrays.sort(itemsArray, (item1, item2) -> {
            Integer numberItem1 = Integer.parseInt(item1.getTitle().split("#")[1]);
            Integer numberItem2 = Integer.parseInt(item2.getTitle().split("#")[1]);
            return numberItem1.compareTo(numberItem2);
        });
        this.items = Arrays.asList(itemsArray);
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Item item = items.get(position);
        holder.title.setText(item.getTitle());
        holder.imageView.setImage(item.getImage());
        holder.message.setText(item.getMessage());
        holder.date.setText(item.getDate());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class Holder extends RecyclerView.ViewHolder {

        TextView title;
        SubsamplingScaleImageView imageView;
        TextView message;
        TextView date;
        private boolean singleLine = true;

        Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            message = itemView.findViewById(R.id.information);
            message.setOnClickListener(v -> {
                if (singleLine) {
                    message.setSingleLine(false);
                    singleLine = false;
                } else {
                    message.setSingleLine(true);
                    singleLine = true;
                }
            });
            date = itemView.findViewById(R.id.date);
        }
    }
}
