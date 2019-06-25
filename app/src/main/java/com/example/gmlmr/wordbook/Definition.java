package com.example.gmlmr.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Definition extends BaseActivity {

    String passedVar = null;
    Button btnAdd;
    private TextView passedView = null;
    DatabaseHandler db = new DatabaseHandler(this);
    String txtword;
    String txtdef;
    private TextView passedWord, passedDef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_definition);
        super.onCreate(savedInstanceState);
        btnAdd = findViewById(R.id.home);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Definition.this,MainActivity.class);
                startActivity(i);
            }
        });

        passedVar = getIntent().getStringExtra(MyWordList.DEF_EXTRA);
        //passedName = getIntent().getStringExtra(MyWordList.DEF_NAME);
        passedWord = findViewById(R.id.def_word);
        passedDef = findViewById(R.id.def_def);

        txtword = db.getWordbyID(passedVar);
        txtdef = db.getDefbyID(passedVar);


        passedWord.setText("Word \n"+txtword);
        passedDef.setText("Definition \n"+txtdef);



    }
}
