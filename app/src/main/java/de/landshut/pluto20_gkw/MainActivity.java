package de.landshut.pluto20_gkw;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "xx MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate");
    }

    // Laden des Menus

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()){
            case R.id.menu_main_create_account:
                intent = new Intent( getApplication(), CreateAccountActivity.class);
                startActivity( intent );
                return true;

            case R.id.menu_main_manage_account:
                Toast.makeText( getApplicationContext(), "ManageAccount", Toast.LENGTH_LONG).show();
                return true;

            case R.id.menu_main_post:
                intent = new Intent( getApplication(), PostActivity.class);
                startActivity( intent );
                return true;

            case R.id.menu_main_signin:
                intent = new Intent( getApplication(), SignInActivity.class);
                startActivity( intent );
                return true;

        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }
}