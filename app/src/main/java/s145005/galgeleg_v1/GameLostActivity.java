package s145005.galgeleg_v1;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameLostActivity extends AppCompatActivity implements View.OnClickListener {

    String word;

    MediaPlayer hyena;

    TextView loserText;
    TextView word_lost;

    Button back;

    Intent returnHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_lost);

        Bundle data = getIntent().getExtras(); //get the bundle

        if(data != null) { //if the bundle is not empty
            word = data.getString("word_lost"); //get the string with keyword
        }

        loserText = (TextView) findViewById(R.id.statusText);
        word_lost = (TextView) findViewById(R.id.showAttemps);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);

        returnHome = new Intent(GameLostActivity.this, MainActivity.class);

        word_lost.setText("Ordet var: " + word); //show word

        /** hyena_laugh.mp3
         * http://soundbible.com/2191-Hyena-Laughing.html
         * recorded by Daniel Simion
         */

        hyena = MediaPlayer.create(this,R.raw.hyena_laugh);
        hyena.start();

    }

    @Override
    public void onClick(View v) {

        if(v == back) { //this is for returning to MainActivity
            GameLostActivity.this.startActivity(returnHome);
            hyena.stop();
            System.out.println("return from GameLostActivity to MainActivity");
            finish();
        }

    }
}
