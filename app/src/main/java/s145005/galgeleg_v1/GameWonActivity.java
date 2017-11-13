package s145005.galgeleg_v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameWonActivity extends AppCompatActivity {

    String word;
    int attemps;

    TextView winnerText;
    TextView showAttemps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        //Intent in = getIntent();
        Bundle data = getIntent().getExtras();

        if(data != null) {
            word = data.getString("word");
            attemps = data.getInt("attemps");
        }


        winnerText = (TextView) findViewById(R.id.statusText);
        showAttemps = (TextView) findViewById(R.id.showAttemps);

        showAttemps.setText("Du brugte " + attemps + " forsøg"); //show attemps count
    }
}
