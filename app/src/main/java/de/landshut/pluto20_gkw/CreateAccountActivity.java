package de.landshut.pluto20_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CreateAccountActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String TAG = "xx CreateAccActivity";

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
        mPassword1.setText("123456");
        mPassword2.setText("123456");

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.create_account_button_create_account:
                doCreateAccount();
                return;
        }
    }

    private void doCreateAccount() {
        String email = mEmail.getText().toString();
        String password = mPassword1.getText().toString();
        // TODO: Check (1) email, (2) Password rules and (3) equality of passwords

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                    Toast.makeText( getApplicationContext(), "Account created.", Toast.LENGTH_LONG).show();
                                else {
                                    Toast.makeText(getApplicationContext(), "Account creation failed.", Toast.LENGTH_LONG).show();
                                    Log.d(TAG, task.getException().getLocalizedMessage());
                                }
                            }
                        }
                );
    }

}
