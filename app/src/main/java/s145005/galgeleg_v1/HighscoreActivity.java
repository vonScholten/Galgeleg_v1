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

    TextView showTest;

    Button re_turn;

    Intent returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        showTest = (TextView) findViewById(R.id.showHighscoreTest);

        re_turn = (Button) findViewById(R.id.returnButton);
        re_turn.setOnClickListener(this);

        returnHome = new Intent(HighscoreActivity.this, MainActivity.class);

        SharedPreferences sharedScore = PreferenceManager.getDefaultSharedPreferences(this);

        count_win = sharedScore.getInt("count_win", 0);
        showTest.setText("Highscore: " + count_win);
    }

    @Override
    public void onClick(View v) {

        if (v == re_turn) {
            HighscoreActivity.this.startActivity(returnHome);
            System.out.println("return from GameWonActivity to MainActivity");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        showTest.setText("Highscore: " + count_win);
    }
}
