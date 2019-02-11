package com.example.milan.madlips;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.CursorJoiner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class ActivityOne extends AppCompatActivity {
    private Story story;
    private EditText hint;
    public int randomstory = -1;
    Random rand = new Random();
    public int placeHolderCount;        // amount of placeholders in a story
    public int placeHolderCountRemain;  // amount of placeholders left
    public String placeHolderCountText; // String with the amount of placeholder and the number of remaining ones

    String[] stories = {"madlib0_simple.txt", "madlib1_tarzan.txt", "madlib2_university.txt",
            "madlib3_clothes.txt", "madlib4_dance.txt"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        if (savedInstanceState != null) {
            randomstory = savedInstanceState.getInt("random");
            story = (Story)savedInstanceState.getSerializable("Storyclass");
        }

        ActivityStory();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("random", randomstory);
        savedInstanceState.putSerializable("Storyclass", story);
    }

    public void onRestoreInstanceState(Bundle saveInstanceState) {
        super.onRestoreInstanceState(saveInstanceState);
        randomstory = saveInstanceState.getInt("random");
    }

    public void ActivityStory() {
        if (randomstory == -1) {
            randomstory = rand.nextInt(4);
            Log.d("Chosen story index", Integer.toString(randomstory));
        }

        if (story == null) {
            AssetManager assetInputStream = getAssets();

            try {
                InputStream loadfile = null;
                loadfile = assetInputStream.open(stories[randomstory]);
                story = new Story(loadfile);
            } catch (IOException e) {
                Log.d("Something went wrong", "story catch");
                e.printStackTrace();
            }

        }
        hint = findViewById(R.id.input);

        placeHolderCount = story.getPlaceholderCount();
        placeHolderCountRemain = story.getPlaceholderRemainingCount();

        placeHolderCountText = (Integer.toString(placeHolderCountRemain) + "/" + Integer.toString(placeHolderCount));

        TextView amountPlaceHolder = (TextView)findViewById(R.id.Count);
        amountPlaceHolder.setText(placeHolderCountText);

        hint.setHint(story.getNextPlaceholder());
    }

    public void ActivityWord(View view){
        EditText word = findViewById(R.id.input);
        story.fillInPlaceholder(word.getText().toString());
        hint.setHint(story.getNextPlaceholder());

        placeHolderCountRemain = story.getPlaceholderRemainingCount();

        placeHolderCountText = (Integer.toString(placeHolderCountRemain) + "/" + Integer.toString(placeHolderCount));

        TextView amountPlaceHolder = (TextView)findViewById(R.id.Count);
        amountPlaceHolder.setText(placeHolderCountText);
        System.out.println(placeHolderCountText);

        word.setText("");
        if (story.isFilledIn()) {
            Intent intent = new Intent(this, Result.class);
            intent.putExtra("ourtext", story.toString());
            startActivity(intent);
            finish();
        }
    }
}
