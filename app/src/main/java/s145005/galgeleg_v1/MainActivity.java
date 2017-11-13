package s145005.galgeleg_v1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import static s145005.galgeleg_v1.GameActivity.logic;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button start;
    Button rules;
    Button settings;
    Intent game;

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

        game = new Intent(MainActivity.this, GameActivity.class); //intent for starting game activity
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
            rulesDialog.setMessage("Det er bare hængtmand. Du kender reglerne.");
            rulesDialog.show();
            System.out.println("showing rules dialog");
        }
        /*
        else if (v == settings){
            MainActivity.this.startActivity(gameSettings);
            System.out.println("settings activity started");
        }
        */
    }
}
