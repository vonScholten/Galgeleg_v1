package s145005.galgeleg_v1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static s145005.galgeleg_v1.GameWonActivity.countB;

public class HighscoreActivity extends AppCompatActivity {

    TextView showTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        showTest = (TextView) findViewById(R.id.showHighscoreTest);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);

        countB = preferences.getInt("countB", 0);
        showTest.setText("Highscore: " + countB);

    }
}
