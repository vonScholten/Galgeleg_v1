package s145005.galgeleg_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class GameActivity extends AppCompatActivity implements View.OnClickListener{

    public static Galgelogik logic = new Galgelogik(); //Galgelogik object

    ImageView hangman;

    TextView wordView;
    TextView usedView;
    TextView attempCount;

    EditText input;

    Button back;
    Button check;

    String word;
    String used;
    String wordWon;

    int score;
    int attemps;

    Intent won;
    Intent lost;
    Intent returnHome;

    long startTime;
    long elapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        System.out.println(logic.getMuligeOrd()); //prints a list of possible words

        //Intents
        won = new Intent(GameActivity.this, GameWonActivity.class); //intent for game won
        lost = new Intent(GameActivity.this, GameLostActivity.class); //intent for game lost
        returnHome = new Intent(GameActivity.this, MainActivity.class);

        //buttons on click listener
        check = (Button) findViewById(R.id.checkButton); //check letter input
        check.setOnClickListener(this);
        back = (Button) findViewById(R.id.back); //back to start new game
        back.setOnClickListener(this);

        //text view/edit
        wordView = (TextView) findViewById(R.id.wordTextView); //word
        usedView = (TextView) findViewById(R.id.usedLettersView); //used letters
        attempCount = (TextView) findViewById(R.id.attempCount); //count of used letters
        input = (EditText) findViewById(R.id.letterInputTextEdit); //letter input

        //image
        hangman = (ImageView) findViewById(R.id.imageView); //hangman graphic

        update(); //update ui on create

        startTime = System.nanoTime(); //time
        System.out.println(startTime + ": " + "new game started"); //logcat
    }

    @Override
    public void onClick(View v) {

        if (v == back){ //start new game
            logic.nulstil(); //reset everything and set a new word
            System.out.println(logic.getOrdet()); //info for cheating

            GameActivity.this.startActivity(returnHome); //starting returnHome intent
            finish();
        }

        else if (v == check){ //check if the letter input is part of the word
            word = input.getText().toString(); //input to a string
            logic.gætBogstav(word); //check input
            update(); //update user interface / graphics

            if (logic.erSpilletVundet()){
                gameWon();
            }
            else if (logic.erSpilletTabt()){ //show this alert dialog if the game is lost
                gameLost();
            }

            System.out.println("checking input done");
        }

    }

    public void update(){ //updates the user interface

        System.out.println("running update");

        used = logic.getBrugteBogstaver().toString(); //array of used letters to a string
        wordView.setText(logic.getSynligtOrd()); //update wordView with viewable letters
        attempCount.setText("Antal forsøg: " + logic.getBrugteBogstaver().size()); //update count of wrongs
        usedView.setText("Brugte bogstaver: " + used); //update used letters
        input.setText(""); //reset input field
        updateImage(); //update image

        System.out.println("update done");

        System.out.println(logic.getOrdet()); //info for cheating
    }

    public void updateImage(){ //updates gallow graphic

        System.out.println("updating graphics");

        switch(logic.getAntalForkerteBogstaver()){ //takes count of wrongs
            case 0:
                hangman.setImageResource(R.drawable.galge);
                break;
            case 1:
                hangman.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                hangman.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                hangman.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                hangman.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                hangman.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                hangman.setImageResource(R.drawable.forkert6);
                break;
        }
    }

    public void gameWon() { //method for if game is won
        String log_won = "game won \n" + "word: " + logic.getOrdet() + "\n" + "score: " + score;
        System.out.println(log_won);

        wordWon = logic.getOrdet();
        attemps = logic.getBrugteBogstaver().size();
        score = logic.getOrdet().length() - logic.getAntalForkerteBogstaver(); //calculate score
        System.out.println(wordWon + ", " + attemps + ", " + score);

        //store in intents bundle for use in GameWonActivity
        won.putExtra("word_win", logic.getOrdet());
        won.putExtra("attemps", attemps);
        won.putExtra("score", score);

        GameActivity.this.startActivity(won); //starting won intent
        System.out.println("game won activity started");
        finish();
    }

    public void gameLost() { //method for if game is won
        String log_lost = "game won \n" + "word: " + logic.getOrdet() + "\n" + "score: " + score;
        System.out.println(log_lost);

        //store in intents bundle for use in GameLostActivity
        lost.putExtra("word_lost", logic.getOrdet());

        GameActivity.this.startActivity(lost); //starting lost intent
        System.out.println("game lost activity started");
        finish();
    }
}

