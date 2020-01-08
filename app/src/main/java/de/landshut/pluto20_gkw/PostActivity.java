package de.landshut.pluto20_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "xx PostActivity";

    // UI Variablen deklarieren
    Button mButtonPost;
    EditText mTitle, mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        // UI Variablen initialisieren
        mButtonPost = findViewById( R.id.post_button_post);
        mTitle = findViewById( R.id.post_title);
        mMessage = findViewById( R.id.post_text);

        // Listener registrieren
        mButtonPost.setOnClickListener( this );

        // TODO: nur zum Test; remove later
        mTitle.setText("Titel");
        mMessage.setText("Nachricht von " + new Date());
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.post_button_post:
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if (user == null){
                    // TODO: remove later
                    Toast.makeText( getApplicationContext(), "Upps.This should never happen.", Toast.LENGTH_LONG).show();
                    break;
                }

                Map<String, Object> postMap = new HashMap<>();
                postMap.put("uid", user.getUid());
                postMap.put("author",user.getEmail());
                postMap.put("title", mTitle.getText().toString());
                postMap.put("body", mMessage.getText().toString());
                postMap.put("timestamp", ServerValue.TIMESTAMP);


                try {
                    DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("posts/");
                    mDatabase.push().setValue( postMap );
                    Log.d(TAG, "Schreiben erfolgreich");
                    finish();
                    Toast.makeText( getApplicationContext(), "Posted!", Toast.LENGTH_LONG).show();
                } catch (Exception e){
                    Log.d(TAG, "Fehler beim Schreiben : " + e.getLocalizedMessage());
                    Toast.makeText( getApplicationContext(), "Post failed !", Toast.LENGTH_LONG).show();
                }

        }
    }
}
