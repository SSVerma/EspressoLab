package com.ssverma.espressolab.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ssverma.espressolab.R;

import java.util.ArrayList;
import java.util.List;

public class SomeRecyclerActivity extends AppCompatActivity {

    public static final int TOTAL_ITEMS = 60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_some_recycler);
        setUpContents();
    }

    private void setUpContents() {

        final TextView tvClickedItem = findViewById(R.id.tv_item_clicked);

        RecyclerView rvItems = findViewById(R.id.rv_items);
        rvItems.setLayoutManager(new LinearLayoutManager(this));

        final List<String> listItems = prepareItems();

        SomeRecyclerAdapter adapter = new SomeRecyclerAdapter(listItems);
        rvItems.setAdapter(adapter);

        adapter.setRecyclerViewItemClickListener(new IRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View clickedView, int position) {
                tvClickedItem.setText(listItems.get(position));
            }
        });

    }

    private List<String> prepareItems() {
        List<String> resultList = new ArrayList<>(TOTAL_ITEMS);
        for (int i = 0; i < TOTAL_ITEMS; i++) {
            resultList.add(getString(R.string.item_label) + i);
        }
        return resultList;
    }
}
