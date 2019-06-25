package com.example.gmlmr.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class WordofDay extends BaseActivity {

     Button btnAdd;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_wordof_day);
        btnAdd = findViewById(R.id.day_tap);
        super.onCreate(savedInstanceState);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView editText = findViewById(R.id.day_txt);
                TextView editText1 = findViewById(R.id.day_def);
                String value = db.getRandomWords();
                String def = db.getDefbyID(value);
                //Toast.makeText(WordofDay.this,"after random words", Toast.LENGTH_SHORT).show();
                editText.setText("Word \n" +value);
                editText1.setText("Definition \n"+def);

            }
        });
    }
}
