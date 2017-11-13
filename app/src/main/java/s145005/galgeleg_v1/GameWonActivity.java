package s145005.galgeleg_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameWonActivity extends AppCompatActivity {

    String word;

    TextView winnerText;
    TextView winnerWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
            word = bundle.getString("wonWord");

        winnerText = (TextView) findViewById(R.id.statusText);
        winnerWord = (TextView) findViewById(R.id.showWord);

        winnerWord.setText("Ordet var: " + word); //show winner word
    }
}
