package s145005.galgeleg_v1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static s145005.galgeleg_v1.GameActivity.logic;
import static s145005.galgeleg_v1.GameActivity.count;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;

    Button start;
    Button rules;
    Button settings;

    Intent game;
    Intent highscore;

    SharedPreferences memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new AsyncTask(){
            @Override
            protected Object doInBackground(Object... arg0) {
                try {
                    logic.hentOrdFraDr();
                    return "Ordene blev korrekt hentet fra DR's server";
                } catch (Exception e) {
                    e.printStackTrace();
                    return "Ordene blev ikke hentet korrekt: "+e;
                }
            }

            @Override
            protected void onPostExecute(Object resultat) {
                System.out.println("resultat: \n" + resultat);
            }
        }.execute();

        //Buttons
        start = (Button) findViewById(R.id.startGameButton);
        start.setOnClickListener(this);
        rules = (Button) findViewById(R.id.rulesButton);
        rules.setOnClickListener(this);
        settings = (Button) findViewById(R.id.settingsButton);
        settings.setOnClickListener(this);

        //TextViews
        textView = (TextView) findViewById(R.id.WelcomeText);

        //Intents
        game = new Intent(MainActivity.this, GameActivity.class); //intent for starting game activity
        highscore = new Intent(MainActivity.this, HighscoreActivity.class); //intent for starting highscore activity

        memo = getSharedPreferences("count", Context.MODE_PRIVATE);
        memo.getInt("count", count);
        textView.setText("Antal rigtige ord: " + count);

    }

    @Override
    public void onClick(View v) {
        if (v == start){
            MainActivity.this.startActivity(game);
            System.out.println("game activity startet");
        }
        else if (v == rules){ //TO-DO: rework til highscore and add rules in settings
            AlertDialog.Builder rulesDialog = new AlertDialog.Builder(this);
            rulesDialog.setTitle("Regler");
            rulesDialog.setMessage("Meh..");
            rulesDialog.show();
            System.out.println("showing highscore dialog");
        }
        else if (v == settings){
            MainActivity.this.startActivity(highscore);
            System.out.println("highscore activity started");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        textView.setText("Antal rigtige ord " + count);

    }
}
