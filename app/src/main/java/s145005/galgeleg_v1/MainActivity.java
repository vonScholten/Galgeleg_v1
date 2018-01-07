package s145005.galgeleg_v1;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import static s145005.galgeleg_v1.GameActivity.logic;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;

    Button start;
    Button highscoreButton;

    ToggleButton gamemode;

    Intent game;
    Intent highscore;
    Intent wordlist;

    AlertDialog select;

    /**
     * Frederik von Scholten, s145005
     *
     * Kilder til kode!
     * Jeg har benyttet stumper af kode fra undervisningen samt fra Android Elementer (Nordfalk)
     * Galgelogik, GalgelegTest & BenytGalgelogik er den vi har fået af underviser
     * AsyncTask & PreferenceManager: Lektion 04 Br.Int.
     *
     * @param savedInstanceState
     */

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
        highscoreButton = (Button) findViewById(R.id.higescoreButton);
        highscoreButton.setOnClickListener(this);

        //switches
        gamemode = (ToggleButton) findViewById(R.id.gamemode1);

        //TextViews
        textView = (TextView) findViewById(R.id.WelcomeText);

        //Intents
        game = new Intent(MainActivity.this, GameActivity.class); //intent for starting game activity
        highscore = new Intent(MainActivity.this, HighscoreActivity.class); //intent for starting highscore activity
        wordlist = new Intent(MainActivity.this, WordListActivity.class); //intent for starting word list activity
    }

    @Override
    public void onClick(View v) {
        if (v == start){ //starting game intent (the hangman game)

            if (gamemode.isChecked()){
                wordSelect();
            }
            else {
                MainActivity.this.startActivity(game);
                logic.nulstil();
            }
        }
        else if (v == highscoreButton){ //starting highscore intent
            MainActivity.this.startActivity(highscore);
            System.out.println("highscore activity started");
            finish();
        }
    }

    public void wordSelect() {
        select = new AlertDialog.Builder(this).create();
        select.setCancelable(true);
        select.setTitle("Vælg ord");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        ListView listView = new ListView(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
                logic.valgtOrd(pos);
                MainActivity.this.startActivity(game);
                select.dismiss();
            }
        });

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1,logic.getMuligeOrd());
        listView.setAdapter(adapter);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);

        layout.addView(listView);
        select.setView(layout);
        select.show();
    }
}
