package com.realmdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    Realm realm;
    EditText etName, etAge;
    private ListView listView;
    private MyListAdapter adapter;
    private RealmResults<User> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        realm = Realm.getDefaultInstance(); // opens "myrealm.realm"
        listView = (ListView) findViewById(R.id.lvItems);

        etName = (EditText) findViewById(R.id.name);
        etAge = (EditText) findViewById(R.id.age);

        ((Button) findViewById(R.id.save)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                User user = realm.createObject(User.class); // Create a new object
                user.setName(etName.getText().toString());
                user.setAge(Integer.parseInt(etAge.getText().toString()));
                realm.commitTransaction();
                etName.setText(null);
                etAge.setText(null);

                results = realm.where(User.class)
                        .findAll();
                adapter.notifyDataSetChanged();

            }
        });



        results = realm.where(User.class)
                .findAll();
        adapter = new MyListAdapter(results);
        listView.setAdapter(adapter);


    }


    @Override
    protected void onStop() {
        super.onStop();
        if (realm != null) {
            realm.close();
        }

    }
}
