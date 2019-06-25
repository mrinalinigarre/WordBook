package com.example.gmlmr.wordbook;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class AddInputActivity extends BaseActivity {


    EditText nameEdt,defedt;
    Button btnAdd,favbtn;
    public int f = 0;
    DatabaseHandler db = new DatabaseHandler(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_add_input);
        super.onCreate(savedInstanceState);

        btnAdd = findViewById(R.id.save_btn);
        favbtn = findViewById(R.id.fav_btn);
        nameEdt = findViewById(R.id.name_edt);
        defedt = findViewById(R.id.def_edt);


        //onclick of favorite button
        favbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                f=1;
               // db.addFav(1, nameEdt);
                Toast.makeText(AddInputActivity.this,"Added to Favorites", Toast.LENGTH_SHORT).show();
            }
        });

        //Onclick of Add button
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(f==1){
                    db.addWords(nameEdt.getText().toString(),defedt.getText().toString(),f);
                }
                else {
                    db.addWords(nameEdt.getText().toString(),defedt.getText().toString(),f);
                }
                Intent i = new Intent(AddInputActivity.this,MyWordList.class);
                startActivity(i);

            }
        });

    }

    //Adding Speech
    public void getSpeechInput(View view) {

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        }
        else if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 5);
        }
        else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    //for definition button
    public void getDefinitionInput(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 5);
        }
        else {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }
    //Displaying the text result from speech
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 10:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    nameEdt.setText(result.get(0));
                }
                break;
            case 5:
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    defedt.setText(result.get(0));
                }
                break;
        }
    }
}