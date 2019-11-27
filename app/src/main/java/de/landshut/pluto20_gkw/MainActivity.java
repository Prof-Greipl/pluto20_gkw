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

import java.util.ArrayList;

import de.landshut.pluto20_gkw.model.Post;
import de.landshut.pluto20_gkw.test.PostTestData;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<Post> mAdapter;
    ArrayList<Post> mPostList;

    public static final String TAG = "xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        }
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Intent intent = new Intent(getApplication(), SignInActivity.class);
            startActivity(intent);
        }
    }
}