package de.landshut.pluto20_gkw;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import de.landshut.pluto20_gkw.model.Post;
import de.landshut.pluto20_gkw.test.PostTestData;

public class MainActivity extends AppCompatActivity {

    ChildEventListener mChildEventListener;
    Query mPostQuery;

    ArrayAdapter<Post> mAdapter;
    ArrayList<Post> mPostList;

    public static final String TAG = "xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreateCalled.");
        setContentView(R.layout.activity_main);

        // TODO: remove later - only for testing
        mPostList = (ArrayList<Post>) PostTestData.createTestdata();

        mAdapter = new ArrayAdapter<Post>(
                this,
                android.R.layout.simple_list_item_2,
                android.R.id.text1,
                mPostList
        ) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView line1, line2;
                line1 = view.findViewById(android.R.id.text1);
                line2 = view.findViewById(android.R.id.text2);

                line1.setText("Position :" + position);
                line2.setText("leer");

                Log.d(TAG, "called getView mit Position " + position);
                return view;
            }
        };

        // Erzeuge ListView
        ListView mListView = findViewById(R.id.idListViewMessages);

        // Verbinde Adapter mit der View
        mListView.setAdapter(mAdapter);

        // Set Listener for Firebase Realtime Database
        mPostQuery = FirebaseDatabase.getInstance().getReference("profs/");
        mChildEventListener = getChildEventListener();
        mPostQuery.addChildEventListener( mChildEventListener );

    }

    // Laden des Menus

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.menu_main_manage_account:
                intent  = new Intent( getApplication(), ManageAccountActivity.class);
                startActivity( intent );
                return true;

            case R.id.menu_main_post:
                intent  = new Intent( getApplication(), PostActivity.class);
                startActivity( intent );
                return true;

            case R.id.menu_main_test_write:
                // TODO: just for testing; remove in productive version
                Map<String, Object> testMap = new HashMap<>();
                testMap.put("name","Greipl");
                testMap.put("gehalt", 270);
                testMap.put("fakultät", true);

                // Schreiben in die Datenbank...
                DatabaseReference mDatabase;
                try {
                    mDatabase = FirebaseDatabase.getInstance().getReference("profs/");
                    mDatabase.push().setValue( testMap );
                    Log.d(TAG, "Schreiben erfolgreich");
                } catch (Exception e){
                    Log.d(TAG, "Fehler beim Schreiben : " + e.getLocalizedMessage());
                }

                return true;
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,"onStart called.");
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Log.d(TAG,"User not authenticated. Go to SignInActivity..");
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        }
        else {
            Log.d(TAG,"User authenticated: " + user.getEmail());
        }
    }

    private ChildEventListener getChildEventListener(){
        ChildEventListener cel;
        cel = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildAdded :" + dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildChanged :" + dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                Log.d(TAG, "onChildRemoved :" + dataSnapshot.getKey());
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d(TAG, "onChildMoved :" + dataSnapshot.getKey());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        return cel;
    }
}