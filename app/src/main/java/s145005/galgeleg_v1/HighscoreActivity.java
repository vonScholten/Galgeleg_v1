package s145005.galgeleg_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static s145005.galgeleg_v1.GameWonActivity.count_win;

public class HighscoreActivity extends AppCompatActivity implements View.OnClickListener {

    TextView scoreView;

    Button re_turn;

    Intent returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

       scoreView = (TextView) findViewById(R.id.scoreView);

        re_turn = (Button) findViewById(R.id.returnButton);
        re_turn.setOnClickListener(this);

        returnHome = new Intent(HighscoreActivity.this, MainActivity.class);

        //get and fetch data with PreferenceManager
        SharedPreferences sharedScore = PreferenceManager.getDefaultSharedPreferences(this);

        count_win = sharedScore.getInt("count_win", 0); //fetch count of wins
        scoreView.setText("Highscore: antal vundne ord: " + count_win + " (lifetime)"); //show count in a textview

        //TODO: add loses count and score count
        //TODO: add a list
        //TODO: add reset of score counts (maybe in settings)
    }

    @Override
    public void onClick(View v) {

        if (v == re_turn) { //this is for returning to MainActivity
            HighscoreActivity.this.startActivity(returnHome);
            System.out.println("return from GameWonActivity to MainActivity");
        }
    }

    @Override
    public void onResume() { //resume counting wins - build in stuff
        super.onResume();
        scoreView.setText("Highscore: " + count_win);
    }
}
