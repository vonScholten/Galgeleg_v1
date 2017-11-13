package s145005.galgeleg_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GameLostActivity extends AppCompatActivity {

    String word;

    TextView loserText;
    TextView loserWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null)
            word = bundle.getString("lostWord");

        loserText = (TextView) findViewById(R.id.statusText);
        loserWord = (TextView) findViewById(R.id.showWord);

        loserWord.setText("Ordet var: " + word);
    }
}
