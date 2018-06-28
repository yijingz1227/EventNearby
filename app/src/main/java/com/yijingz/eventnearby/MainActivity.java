package com.yijingz.eventnearby;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        ListView listview = findViewById(R.id.event_list);
        EventAdapter adapter = new EventAdapter(this);
        listview.setAdapter(adapter);

        Log.e("Error log","Activity Life Cycle Alert: On create!!!");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("Error log","Activity Life Cycle Alert: On start!!!");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("Error log","Activity Life Cycle Alert: On resume!!!");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("Error log","Activity Life Cycle Alert: On pause!!!");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("Error log","Activity Life Cycle Alert: On stop!!!");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("Error log","Activity Life Cycle Alert: On destroy!!!");
    }

}
