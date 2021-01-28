package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    boolean active=true;
    int[] state={2,2,2,2,2,2,2,2,2};
    //true,0=yellow
    //false,1=red
    //2=empty
    int[][] winning_pos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean game_active=true;

    public void dropIn(View view) {

        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(view.getTag().toString());
        if (state[tappedCounter] == 2 && game_active) {

            counter.setTranslationY(-1500);

            if (active) {
                counter.setImageResource(R.drawable.yellow);
                state[tappedCounter] = 0;
                active = false;
            } else if (!active) {
                counter.setImageResource(R.drawable.red);
                state[tappedCounter] = 1;
                active = true;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] w : winning_pos) {
                if (state[w[0]] == state[w[1]] && state[w[0]] == state[w[2]] && state[w[2]] != 2) {
                    if (active)
                        Toast.makeText(this, "Red has won", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(this, "Yellow has won", Toast.LENGTH_SHORT).show();
                    game_active=false;
                    Button play_button=(Button)findViewById(R.id.button);
                    play_button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void play_again(View view){
        Button play_button=(Button)findViewById(R.id.button);
        play_button.setVisibility(View.INVISIBLE);
        boolean active=true;
        for(int i=0;i<9;i++)
            state[i]=2;
        game_active=true;

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView child = (ImageView)gridLayout.getChildAt(i);
            child.setImageDrawable(null);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}