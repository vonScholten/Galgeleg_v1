package s145005.galgeleg_v1;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static s145005.galgeleg_v1.GameActivity.logic;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;

    Button start;
    Button rules;
    Button highscoreButton;

    Intent game;
    Intent highscore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask(){
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logic.hentOrdFraDr();
                    return "Transfer of words from DR's server was a SUCCESS";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Transfer of words from DR's server FAILED: "+e;
                }
            }

            @Override
            protected void onPostExecute(Object result) {
                System.out.println("result: \n" + result);
            }
        }.execute();

        //Buttons
        start = (Button) findViewById(R.id.startGameButton);
        start.setOnClickListener(this);
        rules = (Button) findViewById(R.id.rulesButton);
        rules.setOnClickListener(this);
        highscoreButton = (Button) findViewById(R.id.higescoreButton);
        highscoreButton.setOnClickListener(this);

        //TextViews
        textView = (TextView) findViewById(R.id.WelcomeText);

        //Intents
        game = new Intent(MainActivity.this, GameActivity.class); //intent for starting game activity
        highscore = new Intent(MainActivity.this, HighscoreActivity.class); //intent for starting highscore activity
    }

    @Override
    public void onClick(View v) {
        if (v == start){
            MainActivity.this.startActivity(game);
            System.out.println("game activity startet");
        }
        else if (v == rules){
            AlertDialog.Builder rulesDialog = new AlertDialog.Builder(this);
            rulesDialog.setTitle("Regler");
            rulesDialog.setMessage("Hvad skal der være her?");
            rulesDialog.show();
            System.out.println("showing rules dialog");
        }
        else if (v == highscoreButton){
            MainActivity.this.startActivity(highscore);
            System.out.println("highscore activity started");
        }
    }
}
