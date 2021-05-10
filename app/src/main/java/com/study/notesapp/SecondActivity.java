package com.study.notesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView txt;
    private Button sbtn, cbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt=findViewById(R.id.second_frametxt);
        sbtn=findViewById(R.id.share_btn);
        cbtn=findViewById(R.id.copy_btn);

        final String rQuotes=getIntent().getStringExtra("Notes");
        txt.setText(rQuotes);

        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,rQuotes);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, "Share by");
                startActivity(shareIntent);
            }
        });

        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboard =(ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip=
                        ClipData.newPlainText("editText",txt.getText().toString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(SecondActivity.this,"Copied",Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent intent = new Intent(SecondActivity.this,MainActivity.class);
            startActivity(intent);
            finish();


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(SecondActivity.this,MainActivity.class);
        super.onBackPressed();
        startActivity(intent);
        finish();
    }
}