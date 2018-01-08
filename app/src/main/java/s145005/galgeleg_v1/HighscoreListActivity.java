package s145005.galgeleg_v1;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static s145005.galgeleg_v1.GameActivity.logic;
import static s145005.galgeleg_v1.GameWonActivity.count_win;

public class HighscoreListActivity extends AppCompatActivity {

    ListView listView;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore_list);

        //get and fetch data with PreferenceManager
        SharedPreferences sharedScore = PreferenceManager.getDefaultSharedPreferences(this);

        count_win = sharedScore.getInt("count_win", 0); //fetch count of wins

        Set<String> set = sharedScore.getStringSet("highscores",null);
        List<String> highscores = new ArrayList<String>(set);

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, highscores);

        listView = new ListView(this);
        listView.setAdapter(adapter);
        setContentView(listView);
    }

    @Override
    public void onResume() { //resume counting wins - build in stuff
        super.onResume();
        listView.setAdapter(adapter);
    }
}
