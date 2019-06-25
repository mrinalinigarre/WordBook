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
import android.widget.Toast;

import java.util.ArrayList;

public class FavListActivity extends BaseActivity {


    Button btnAdd;
    ListView lv;
    DatabaseHandler db = new DatabaseHandler(this);
    ArrayList<String> array = new ArrayList<>();
    SearchView sv;

    public static final String DEF_EXTRA = "com.example.gmlmr.wordbook";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fav_list);
        super.onCreate(savedInstanceState);

            btnAdd = findViewById(R.id.fav_home);

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(FavListActivity.this,MainActivity.class);
                    startActivity(i);
                }
            });

            lv = findViewById(R.id.favlist);
            sv = findViewById(R.id.search_button2);

            array = db.getFavWords();

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
                Intent i = new Intent(FavListActivity.this,Definition.class);
                String val =(String) parent.getItemAtPosition(position);
                i.putExtra(DEF_EXTRA, val);
                //Toast.makeText(FavListActivity.this,val, Toast.LENGTH_SHORT).show();
                startActivity(i);
            }
        });
        }

    }
