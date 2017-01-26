package com.dutchwords.edu.dutchwords;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private FileHandler fileHandler;
    private File FILE_DIRECTORY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fileHandler = new FileHandler();
        FILE_DIRECTORY = new File(this.getFilesDir()+"/dutchwords");
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

    /** Called when the user clicks the Add button */
    public void addWord(View view) throws IOException {
        String definition = ((EditText) findViewById(R.id.edit_definition)).getText().toString();
        String translation = ((EditText) findViewById(R.id.edit_translation)).getText().toString();
        fileHandler.appendToFile(definition, translation, FILE_DIRECTORY);
        clearWord(view);
    }

    public void mainGetWord(View view) {
        Intent intent = new Intent(this, ShowWordsActivity.class);
        startActivity(intent);
    }

    public void mainSearchWord(View view){
        Intent intent = new Intent(this, SearchWordActivity.class);
        startActivity(intent);
    }

    /** Called when user clicks the Get Word and Next Word button */
    public void clearWord(View view) {
        TextView  definition = (TextView) findViewById(R.id.edit_definition);
        TextView  translation = (TextView) findViewById(R.id.edit_translation);
        definition.setText("");
        translation.setText("");
    }
}
