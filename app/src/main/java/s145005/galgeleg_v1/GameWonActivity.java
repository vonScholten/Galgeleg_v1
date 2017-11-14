package s145005.galgeleg_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameWonActivity extends AppCompatActivity implements View.OnClickListener {

    String word;
    int attemps;
    int score;

    public static int count_win;

    TextView winnerText;
    TextView showAttemps;
    TextView showScore;

    Button re_turn;

    Intent returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        Bundle data = getIntent().getExtras(); //get bundle

        if(data != null) { //if the bundle is not empty
            word = data.getString("word_won"); //get winning word with keyword
            attemps = data.getInt("attemps"); //get amount off attempts..
            score = data.getInt("score"); //get calculated score..
        }

        winnerText = (TextView) findViewById(R.id.statusText);
        showAttemps = (TextView) findViewById(R.id.showAttemps);
        showScore = (TextView) findViewById(R.id.showScore);

        re_turn = (Button) findViewById(R.id.returnButton);
        re_turn.setOnClickListener(this);

        showAttemps.setText("Du brugte " + attemps + " forsøg"); //show attemps count
        showScore.setText("Din score blev " + score);

        returnHome = new Intent(GameWonActivity.this, MainActivity.class);

        // Saving and fetching data with PreferenceManager
        SharedPreferences sharedScore = PreferenceManager.getDefaultSharedPreferences(this);

        //get the count of wins and add one
        count_win = sharedScore.getInt("count_win", 0) + 1;
        sharedScore.edit().putInt("count_win", count_win).commit(); //store: put and commit to pref.
    }

    @Override
    public void onClick(View v) {
        if(v == re_turn){ //this is for returning to MainActivity
            GameWonActivity.this.startActivity(returnHome);
            System.out.println("return from GameWonActivity to MainActivity");
        }
    }
}
