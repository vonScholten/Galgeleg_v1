package s145005.galgeleg_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameWonActivity extends AppCompatActivity implements View.OnClickListener {

    String word;
    int attemps;

    TextView winnerText;
    TextView showAttemps;

    Button re_turn;

    Intent returnHome;

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

        re_turn = (Button) findViewById(R.id.returnButton);
        re_turn.setOnClickListener(this);

        showAttemps.setText("Du brugte " + attemps + " forsøg"); //show attemps count

        returnHome = new Intent(GameWonActivity.this, MainActivity.class);
    }

    @Override
    public void onClick(View v) {
        if(v == re_turn){
            GameWonActivity.this.startActivity(returnHome);
            System.out.println("return from GameWonActivity to MainActivity");
        }
    }
}
