package com.dutchwords.edu.dutchwords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class SearchWordActivity extends AppCompatActivity {

    List<String> dutchWords;
    ListView listView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_word);
        listView = (ListView) findViewById(R.id.list);
        File FILE_DIRECTORY = new File(this.getFilesDir()+"/dutchwords");
        try {
            dutchWords = new FileHandler().getAllWords(FILE_DIRECTORY);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) { finish(); }

    public void clearWord(View view) {
        TextView definition = (TextView) findViewById(R.id.search_edit_definition);
        definition.setText("");
        listView.setAdapter(null);
    }

    public void searchWord(View view) {
        String definition = ((EditText) findViewById(R.id.search_edit_definition)).getText().toString();
        List<String> results = new ArrayList<>();

        for(String line : dutchWords) {
            String[] parts = line.split(Pattern.quote(":"));
            if(parts[0].toUpperCase().equals(definition.toUpperCase())) {
                if (parts.length != 1) results.add(parts[1]);
            }
        }

        if(results.size() == 0) {
            results.add("Word not found");
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                results);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                int itemPosition     = position;

                // ListView Clicked item value
                String  itemValue    = (String) listView.getItemAtPosition(position);

                // Show Alert
                Toast.makeText(getApplicationContext(),
                        "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                        .show();

            }
        });
    }
}
