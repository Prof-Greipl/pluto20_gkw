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
import com.google.firebase.auth.FirebaseUser;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "xx SignActivity";

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

        // TODO: Just for testing, remove later
        m_edit_text_email.setText("dietergreipl@gmail.com");
        m_edit_text_password.setText("123456");

    }

    @Override
    public void onClick(View v) {
        switch( v.getId() ){
            case R.id.sign_in_button_sign_in:
                doSignIn();
                return;

            case R.id.sign_in_button_reset_password:
                doSendResetPasswordMail();
                return;

            case R.id.sign_in_button_create_account:
                // TODO weiter zu create account
                return;
        }
    }

    private void doSignIn() {

        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Toast.makeText(getApplicationContext(), "Still signed in. Pls. sign out first!", Toast.LENGTH_LONG).show();
        }
        else {
            String email = m_edit_text_email.getText().toString();
            String password = m_edit_text_password.getText().toString();

            // TODO: Check password rules and e-mail.

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(
                            this,
                            new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser nUser = FirebaseAuth.getInstance().getCurrentUser();
                                        Toast.makeText(getApplicationContext(), "You are signed in as " + nUser.getEmail(), Toast.LENGTH_LONG).show();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(), "Sign in failed.", Toast.LENGTH_LONG).show();
                                        Log.d(TAG, task.getException().getLocalizedMessage());
                                    }
                                }
                            }
                    );
        }
    }

    private void doSendResetPasswordMail() {
        String email = m_edit_text_email.getText().toString();
        // TODO: Check, ob mail gültig ist

        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(
                        this,
                        new OnCompleteListener<Void>() {

                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Mail sent.", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    Toast.makeText(getApplicationContext(), "Sending mail failed.", Toast.LENGTH_LONG).show();
                                    Log.d(TAG, task.getException().getLocalizedMessage());
                                }
                            }
                        }
                );
    }


}
