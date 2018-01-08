package s145005.galgeleg_v1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class GameWonActivity extends AppCompatActivity implements View.OnClickListener {

    String word;

    int attemps;
    int score;

    public static int count_win;

    KonfettiView konfetti;

    MediaPlayer fanfare;

    TextView winnerText;
    TextView showAttemps;
    TextView showScore;

    Button back;
    Button highscoreButton;

    Intent returnHome;
    Intent highscore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_won);

        Bundle data = getIntent().getExtras(); //get bundle

        if (data != null) { //if the bundle is not empty
            word = data.getString("word_win"); //get winning word with keyword
            attemps = data.getInt("attemps"); //get amount off attempts..
            score = data.getInt("score"); //get calculated score..
            System.out.println(word + ", " + attemps + ", " + score);
        }

        System.out.println(word + ", " + attemps + ", " + score);

        konfetti = (KonfettiView) findViewById(R.id.konfetti);

        winnerText = (TextView) findViewById(R.id.statusText);
        showAttemps = (TextView) findViewById(R.id.showAttemps);
        showScore = (TextView) findViewById(R.id.showScore);

        back = (Button) findViewById(R.id.back);
        back.setOnClickListener(this);
        highscoreButton = (Button) findViewById(R.id.highscoreWonButton);
        highscoreButton.setOnClickListener(this);

        String attempsText = "Du brugte " + attemps + " forsøg";
        String scoreText = "Din score blev " + score;

        showAttemps.setText(attempsText); //show attemps count
        showScore.setText(scoreText);

        returnHome = new Intent(GameWonActivity.this, MainActivity.class);
        highscore = new Intent(GameWonActivity.this, HighscoreListActivity.class); //intent for starting highscore activity

        // Saving and fetching data with PreferenceManager
        SharedPreferences sharedScore = PreferenceManager.getDefaultSharedPreferences(this);

        //get the count of wins and add one
        count_win = sharedScore.getInt("count_win", 0) + 1;

        //fetch and add highscores
        Set<String> fetchSet = sharedScore.getStringSet("highscores", null);
        List<String> highscores = new ArrayList<String>();

        if (fetchSet != null) {
            highscores.addAll(fetchSet);
        }

        String highscoreText = "Ordet: " + word + " - Score: " + score + " point";
        System.out.println(highscoreText);
        highscores.add(highscoreText);
        Set<String> set = new HashSet<String>(highscores);

        //store: put and commit to pref.
        sharedScore.edit().putInt("count_win", count_win).commit();
        sharedScore.edit().putStringSet("highscores", set).commit();

        /** Konfetti by DanielMartinus
         * https://github.com/DanielMartinus/Konfetti
         */

        konfetti.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 0.359)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(10000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, konfetti.getWidth() + 50f, -50f, -50f)
                .stream(300, 10000L);

        /** triumphal_fanfare.mp3
         * http://soundbible.com/1823-Winning-Triumphal-Fanfare.html
         * recorded by John Stracke
         */

        fanfare = MediaPlayer.create(this, R.raw.triumphal_fanfare);
        fanfare.start();
    }

    @Override
    public void onClick(View v) {
        if (v == back) { //this is for returning to MainActivity
            GameWonActivity.this.startActivity(returnHome);
            fanfare.stop();
            finish();
        }
        else if (v == highscoreButton) { //starting highscore intent
            GameWonActivity.this.startActivity(highscore);
            fanfare.stop();
            finish();
        }

    }
}