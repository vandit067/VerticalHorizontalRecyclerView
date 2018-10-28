package com.example.patelv1.verticalhorizontalrecyclerview;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.patelv1.verticalhorizontalrecyclerview.adapter.VerticalListRecyclerAdapter;
import com.example.patelv1.verticalhorizontalrecyclerview.model.HorizontalListItem;
import com.example.patelv1.verticalhorizontalrecyclerview.model.VerticalListItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRvVerticalItems;
    private Button btnScrollToPosition;
    private Button btnUpdateView;
    private List<VerticalListItem> verticalListItemList = new ArrayList<>();
    private VerticalListRecyclerAdapter mVerticalListRecyclerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRvVerticalItems = (RecyclerView) findViewById(R.id.activity_main_rv_vertical_items);
        btnScrollToPosition = (Button) findViewById(R.id.activity_main_btn_scroll_to);
        btnUpdateView = (Button) findViewById(R.id.activity_main_btn_update_view);
        mRvVerticalItems = (RecyclerView) findViewById(R.id.activity_main_rv_vertical_items);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvVerticalItems.setLayoutManager(linearLayoutManager);
        mRvVerticalItems.setItemAnimator(new DefaultItemAnimator());
        mRvVerticalItems.setHasFixedSize(false);
        verticalListItemList = getVerticalListItemList();
        mVerticalListRecyclerAdapter = new VerticalListRecyclerAdapter(verticalListItemList);
        mRvVerticalItems.setAdapter(mVerticalListRecyclerAdapter);

        btnScrollToPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollToPosition();
            }
        });

        btnUpdateView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateView();
            }
        });
    }

    private void updateView(){
        Random random = new Random();
        int updateViewPosition = random.nextInt(verticalListItemList.size());
        mVerticalListRecyclerAdapter.updateViewAtPosition(updateViewPosition);
    }

    private void scrollToPosition(){
        Random random = new Random();
        int scrollPosition = random.nextInt(verticalListItemList.size());
        mRvVerticalItems.scrollToPosition(scrollPosition);
        Snackbar.make(mRvVerticalItems, "Scroll To pos : " + verticalListItemList.get(scrollPosition).getTitle(), Snackbar.LENGTH_SHORT).show();
    }

    private List<VerticalListItem> getVerticalListItemList(){
        List<VerticalListItem> verticalListItemList = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            VerticalListItem verticalListItem = new VerticalListItem();
            verticalListItem.setTitle(String.valueOf(2017 - i));
            Random random = new Random();
            int size = 5 + random.nextInt(5);
            List<HorizontalListItem> horizontalListItemList = new ArrayList<>(size);
            for (int j = 0; j < size; j++) {
                HorizontalListItem horizontalListItem = new HorizontalListItem();
                horizontalListItem.setTitle(verticalListItem.getTitle() + " : Pos - " + j);
                horizontalListItemList.add(horizontalListItem);
            }
            verticalListItem.setHorizontalListItemList(horizontalListItemList);
            verticalListItemList.add(verticalListItem);
        }
        return verticalListItemList;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
