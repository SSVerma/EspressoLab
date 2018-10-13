package com.ssverma.espressolab.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssverma.espressolab.R;

import java.util.List;

public class SomeRecyclerAdapter extends RecyclerView.Adapter<SomeRecyclerAdapter.ViewHolder> {

    private final List<String> listItems;
    private IRecyclerViewItemClickListener recyclerViewItemClickListener;

    public SomeRecyclerAdapter(List<String> listItems) {
        this.listItems = listItems;
    }

    public void setRecyclerViewItemClickListener(IRecyclerViewItemClickListener recyclerViewItemClickListener) {
        this.recyclerViewItemClickListener = recyclerViewItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item_some, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return listItems == null ? 0 : listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvText;

        ViewHolder(View itemView) {
            super(itemView);
            tvText = itemView.findViewById(R.id.tv_text);
            itemView.setOnClickListener(this);
        }

        void bind(int position) {
            tvText.setText(listItems.get(position));
        }

        @Override
        public void onClick(View v) {
            if (recyclerViewItemClickListener == null) {
                return;
            }
            recyclerViewItemClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
