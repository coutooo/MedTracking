package com.example.medtrackingtablet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<String> arrayList = new ArrayList<>();
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        initArray();
        FlexboxLayoutManager layoutManager = new FlexboxLayoutManager(this);
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DataAdapter(this, arrayList);
        recyclerView.setAdapter(adapter);

    }

    private void initArray() {

        arrayList.add("ioreeoe");
        arrayList.add("fghfgh");
        arrayList.add("ftyjyjhghgh");
        arrayList.add("jfgewrg");
        arrayList.add("rwrewr");
        arrayList.add("ghyjtyfghh");
        arrayList.add("gfhfgh");
        arrayList.add("gfhfht");
        arrayList.add("retretret");
        arrayList.add("retret");
        arrayList.add("ioreeoe");
        arrayList.add("fghfgh");
        arrayList.add("ftyjyjhghgh");
        arrayList.add("jfgewrg");
        arrayList.add("rwrewr");
        arrayList.add("ghyjtyfghh");
        arrayList.add("gfhfgh");
        arrayList.add("gfhfht");
        arrayList.add("retretret");
        arrayList.add("retret");
        arrayList.add("ioreeoe");
        arrayList.add("fghfgh");
        arrayList.add("ftyjyjhghgh");
        arrayList.add("jfgewrg");
        arrayList.add("rwrewr");
        arrayList.add("ghyjtyfghh");
        arrayList.add("gfhfgh");
        arrayList.add("gfhfht");
        arrayList.add("retretret");
        arrayList.add("retret");
    }

}