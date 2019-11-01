package de.landshut.pluto20_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    // Declare UI Variables
    EditText m_edit_text_email;
    EditText m_edit_text_password;
    Button m_button_sign_in;
    Button m_button_reset_password;
    Button m_button_create_account;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Initialisieren der UI Variablen
        m_edit_text_email = (EditText) findViewById( R.id.sign_in_email);
        m_edit_text_password = (EditText) findViewById( R.id.sign_in_password);
        m_button_sign_in = (Button) findViewById( R.id.sign_in_button_sign_in);
        m_button_reset_password = (Button) findViewById( R.id.sign_in_button_reset_password);
        m_button_create_account = (Button) findViewById( R.id.sign_in_button_create_account);

        // Listener registrieren
        m_button_sign_in.setOnClickListener( this );
        m_button_create_account.setOnClickListener( this );
        m_button_reset_password.setOnClickListener( this );
    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.sign_in_button_sign_in:
                Toast.makeText( getApplicationContext(), "Sign In", Toast.LENGTH_LONG).show();
                return;

            case R.id.sign_in_button_reset_password:
                Toast.makeText( getApplicationContext(), "Reset Password", Toast.LENGTH_LONG).show();
                return;

            case R.id.sign_in_button_create_account:
                Toast.makeText( getApplicationContext(), "Create Account", Toast.LENGTH_LONG).show();
                return;

        }
    }
}
