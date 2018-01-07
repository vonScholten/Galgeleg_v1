package s145005.galgeleg_v1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import static s145005.galgeleg_v1.GameActivity.logic;

public class WordListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_list);

        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,logic.getMuligeOrd());

        ListView listView = new ListView(this);
        listView.setOnItemClickListener(this);
        listView.setAdapter(adapter);

        setContentView(listView);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long id) {
        System.out.println("You have chosen wisely, you chose " + logic.getMuligeOrd().get(pos));
        logic.valgtOrd(pos);

        Intent start = new Intent(WordListActivity.this,GameActivity.class);
        this.startActivity(start);
    }
}
