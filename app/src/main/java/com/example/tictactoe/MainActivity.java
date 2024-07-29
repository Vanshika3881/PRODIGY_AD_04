package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    private TextView playerOneScore,playerTwoScore,playerStatus;
    private Button[] buttons=new Button[9];

    boolean playerOneActive;
    int[]gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPosition={{0,1,2},{3,4,5},{6,7,8},{0,3,6},
            {1,4,7},{2,5,8},{0,4,8},{2,4,6}};

    int rounds;
    private Button rest,playagain;
    private int playerOneScoreCount,playerTwoScoreCount;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        playerOneScore=findViewById(R.id.score_player1);
        playerTwoScore=findViewById(R.id.score_player2);
        playerStatus=findViewById(R.id.textStatus);
        rest=findViewById(R.id.btn_reset);
        playagain=findViewById(R.id.btn_play_again);


        buttons[0]=findViewById(R.id.btn0);
        buttons[1]=findViewById(R.id.btn1);
        buttons[2]=findViewById(R.id.btn2);
        buttons[3]=findViewById(R.id.btn3);
        buttons[4]=findViewById(R.id.btn4);
        buttons[5]=findViewById(R.id.btn5);
        buttons[6]=findViewById(R.id.btn6);
        buttons[7]=findViewById(R.id.btn7);
        buttons[8]=findViewById(R.id.btn8);


        for (int i=0;i<buttons.length;i++){
            buttons[i].setOnClickListener(this);
        }

        playerOneScoreCount=0;
        playerTwoScoreCount=0;

        playerOneActive=true;
        rounds=0;


    }

    @Override
    public void onClick(View v) {


        if (!((Button) v).getText().toString().equals(""))
        {
            return;
        }
        else if (checkWinner())
        {
            return;
        }

String buttonID=v.getResources().getResourceEntryName(v.getId());
        int gameStatePointer= Integer.parseInt(buttonID.substring(buttonID.length()-1,buttonID.length()));
        if (playerOneActive)
        {
            ((Button)v).setText("X");
            ((Button)v).setTextColor(Color.parseColor("#2f3937"));
            gameState[gameStatePointer]=1;
        }

       else {
           ((Button)v).setText("0");
            ((Button)v).setTextColor(Color.parseColor("#6edabd"));
        }

       rounds++;

       if(checkWinner())
       {
           if(playerOneActive){
               playerOneScoreCount++;
              updatePlayerScore();
               playerStatus.setText("Player-1 is the Winner");

           }

           else {
               playerTwoScoreCount++;
               updatePlayerScore();
               playerStatus.setText("Player-2 is the Winner");
           }
       } else if (rounds==9) {
           playerStatus.setText("No Winner");
       }
       else
       {
           playerOneActive=!playerOneActive;
       }

       rest.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               playagain();
               playerOneScoreCount=0;
               playerTwoScoreCount=0;
               updatePlayerScore();
           }
       });

       playagain.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               playagain();
           }
       });
    }

    private boolean checkWinner(){
        boolean winnerResults=false;
        for (int[] winningPositions: winningPosition)
        {
            if(gameState[winningPositions[0]]==gameState[winningPositions[1]]&&
                    gameState[winningPositions[1]]==gameState[winningPositions[2]]&&
                    gameState[winningPositions[0]]!=2)
            {
                winnerResults=true;
            }
        }
        return winnerResults;
    }

    private void playagain()
    {
        rounds=0;
        playerOneActive=true;
        for (int i=0;i<buttons.length;i++)
        {
            gameState[i]=2;
            buttons[i].setText("");
        }
        playerStatus.setText("STATUS");
    }

    private void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
}