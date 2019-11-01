package de.landshut.pluto20_gkw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{

    // UI Variablen deklarieren
    EditText mEmail, mPassword1, mPassword2;
    Button mButtonCreateAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        // Initialisieren
        mEmail = (EditText) findViewById( R.id.create_account_email);
        mPassword1 = (EditText) findViewById( R.id.create_account_password1);
        mPassword2 = (EditText) findViewById( R.id.create_account_password2);
        mButtonCreateAccount = (Button) findViewById( R.id.create_account_button_create_account);

        // Listener registrieren
        mButtonCreateAccount.setOnClickListener( this );

        // UI Felder zum Testen vorbelegen TODO only for testing - remove later
        mEmail.setText("dietergreipl@gmail.com");
        mPassword1.setText("abcdef");
        mPassword2.setText("abcdef");

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.create_account_button_create_account:
                Toast.makeText( getApplicationContext(), "Create Account", Toast.LENGTH_LONG).show();
                return;

        }
    }
}
