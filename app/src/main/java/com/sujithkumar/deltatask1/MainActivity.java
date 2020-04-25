package com.sujithkumar.deltatask1;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {
    NViewModel res;

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        res.setHackerhighscore(sharedPref.getInt("hackerhighscore", 0));
        res.setHackerpphighscore(sharedPref.getInt("hackerpphighscore", 0));
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("hackerhighscore", res.getHackerhighscore());
        editor.apply();
        editor.putInt("hackerpphighscore", res.getHackerpphighscore());
        editor.apply();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = new ViewModelProvider(this).get(NViewModel.class);
    }

}
