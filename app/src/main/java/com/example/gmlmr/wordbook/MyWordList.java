package com.example.gmlmr.wordbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyWordList extends BaseActivity {

    public static final String DEF_EXTRA = "com.example.gmlmr.wordbook";
    //private static final String DEF_NAME = "com.example.gmlmr.wordbook.def" ;

    Button btnAdd;
    ListView lv;
    SearchView sv;
    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<String> array = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_my_word_list);
        super.onCreate(savedInstanceState);
        btnAdd = findViewById(R.id.nav_home);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyWordList.this,MainActivity.class);
                startActivity(i);
            }
        });

        lv = findViewById(R.id.listview);
        sv = findViewById(R.id.search_button);

        array = db.getAllWords();

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,array);

        lv.setAdapter(adapter);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MyWordList.this,Definition.class);
                String val =(String) parent.getItemAtPosition(position);
                i.putExtra(DEF_EXTRA, val);
               // Toast.makeText(MyWordList.this,val, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
    }
}
