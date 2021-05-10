package com.study.notesapp;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> arrayList;
    private ArrayAdapter<String> arrayAdapter;
    SwipeFlingAdapterView flingAdapterView;

    private Button sbtn, cbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sbtn = findViewById(R.id.share_btn);
        cbtn = findViewById(R.id.copy_btn);


        flingAdapterView = findViewById(R.id.frame);
        String[] Notes = getResources().getStringArray(R.array.notes);
        arrayList = new ArrayList<>(Arrays.asList(Notes));
        arrayAdapter = new ArrayAdapter<>(this, R.layout.item, R.id.frametxt, arrayList);
        flingAdapterView.setAdapter(arrayAdapter);
        flingAdapterView.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

                arrayList.remove(0);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {

            }

            @Override
            public void onRightCardExit(Object o) {

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {


            }

            @Override
            public void onScroll(float v) {
            }
        });


        flingAdapterView.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int i, Object o) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                String item = arrayList.get(i);
                intent.putExtra("Notes", item);
                startActivity(intent);
                finish();
            }


        });


        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, arrayList.get(0));
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share by");
                startActivity(shareIntent);
            }
        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip =
                        ClipData.newPlainText("editText", arrayList.get(0));
                clipboard.setPrimaryClip(clip);


                Toast.makeText(MainActivity.this, "Copied ", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
}