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

        new AsyncTask(){ //fetch words from DR's server this is a nested class
            @Override
            protected Object doInBackground(Object... arg0) { //download words in background thread
                try { //try to fetch words with a Galgelogik method
                    logic.hentOrdFraDr();
                    return "Transfer of words from DR's server was a SUCCESS"; //success message
                } catch (Exception e) { //if it somehow unable to fetch (may be bad connection)
                    e.printStackTrace();
                    return "Transfer of words from DR's server FAILED: "+e; //fail message
                }
            }

            @Override
            protected void onPostExecute(Object result) { //when background is done run this
                System.out.println("result: \n" + result); //prints results to log
                // TODO: make a onscreen message
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
        if (v == start){ //starting game intent (the hangman game)
            MainActivity.this.startActivity(game);
            System.out.println("game activity startet");
        }
        else if (v == rules){ //a dialog box
            AlertDialog.Builder rulesDialog = new AlertDialog.Builder(this);
            rulesDialog.setTitle("Regler");
            rulesDialog.setMessage("Hvad skal der være her?");
            rulesDialog.show();
            System.out.println("showing rules dialog");
        }
        else if (v == highscoreButton){ //starting highscore intent
            MainActivity.this.startActivity(highscore);
            System.out.println("highscore activity started");
        }
    }
}
