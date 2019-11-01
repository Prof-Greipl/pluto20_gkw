package de.landshut.pluto20_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class PostActivity extends AppCompatActivity implements View.OnClickListener{
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

        mTitle.setText("Titel");
        mMessage.setText("Nachricht von " + new Date());
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.post_button_post:
                mMessage.setText("Nachricht von " + new Date());
                Toast.makeText( getApplicationContext(), "Post", Toast.LENGTH_LONG).show();
                return;

        }
    }
}
