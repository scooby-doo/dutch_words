package com.dutchwords.edu.dutchwords;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

public class ShowWordsActivity extends AppCompatActivity {
    private List<String> dutchWords;
    private File FILE_DIRECTORY;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_words);
        FILE_DIRECTORY = new File(this.getFilesDir()+"/dutchwords");
        position = 0;

       try {
           dutchWords = new FileHandler().getAllWords(FILE_DIRECTORY);
           getWord();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void nextWord(View view) throws IOException {
        getWord();
    }

    public void back(View view) {
        finish();
    }


    private void getWord() {
        if(position == 0) shuffleWords();
        if(position == dutchWords.size()) {
            position = 0;
            shuffleWords();
        }
        showWord();
        position++;
    }

    private void shuffleWords(){
        long seed = System.nanoTime();
        Collections.shuffle(dutchWords, new Random(seed));
    }

    private void showWord() {
        String wordAndTranslation = dutchWords.get(position);
        String[] parts = wordAndTranslation.split(Pattern.quote(":"));

        TextView definition = (TextView) findViewById(R.id.show_definition);
        TextView translation = (TextView) findViewById(R.id.show_translation);

        definition.setTextSize(40);
        definition.setText(parts[0]);

        translation.setTextSize(40);
        if (parts.length != 1) translation.setText(parts[1]);
        else translation.setText("");
    }
}
