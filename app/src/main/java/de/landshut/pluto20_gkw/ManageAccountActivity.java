package de.landshut.pluto20_gkw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ManageAccountActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "xx ManageAccount Activi";

    TextView mEmail, mAccountState, mTechnicalId;
    Button mButtonSignOut, mButtonDeleteAccount, mButtonSendActivationMail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_manage_account);
        TextView textview = (TextView) findViewById( R.id.manageAccountTechnicalId);

        // UI Elemente-Initialiseren
        mEmail = findViewById( R.id.manageAccountEmail );
        mAccountState = findViewById( R.id.manageAccountVerificationState );
        mTechnicalId = findViewById( R.id.manageAccountTechnicalId );

        mButtonDeleteAccount = findViewById( R.id.manageAccountButtonDeleteAccount);
        mButtonSendActivationMail = findViewById( R.id.manageAccountButtonSendActivationMail );
        mButtonSignOut = findViewById( R.id.manageAccountButtonSignOut );

        // Listener registrieren
        mButtonSignOut.setOnClickListener( this );
        mButtonSendActivationMail.setOnClickListener( this );
        mButtonDeleteAccount.setOnClickListener( this );

        // Setze Informationen über den Account, wenn der user nicht null ist.
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if ( user==null )
            finish();
        else {
            mEmail.setText( user.getEmail() );
            mAccountState.setText("Account verified:" + user.isEmailVerified());
            mTechnicalId.setText( user.getUid() );
        }

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        switch(i) {
            case R.id.manageAccountButtonDeleteAccount:
                doDeleteAccount();
                return;
            case R.id.manageAccountButtonSignOut:
                doSignOut();
                return;

            case R.id.manageAccountButtonSendActivationMail:
                doSendActivationMail();
                return;
        }
    }

    private void doSignOut() {

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null) {
            Toast.makeText(getApplicationContext(), "You are not signed in!", Toast.LENGTH_LONG).show();
        }
        else {
            // Abmelden
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "You are signed out.", Toast.LENGTH_LONG).show();
        }
    }

    private void doDeleteAccount() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user == null){
            Toast.makeText(getApplicationContext(), "Not deleted, as this needs sign in!", Toast.LENGTH_LONG).show();
        }
        else {
            user.delete().addOnCompleteListener(
                    this,
                    new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Account deleted." , Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Account deletion failed.", Toast.LENGTH_LONG).show();
                                Log.d(TAG, task.getException().getLocalizedMessage());
                            }
                        }
                    }
            );
        }
    }

    private void doSendActivationMail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user==null) {
            Toast.makeText(getApplicationContext(), "You must be signed in for this...", Toast.LENGTH_LONG).show();
            return;
        }

        if (user.isEmailVerified()){
            // User hat email bereits verifiziert
            Toast.makeText(getApplicationContext(), "Your email is already verified. (No mail sent!)", Toast.LENGTH_LONG).show();
            return;
        }

        user.sendEmailVerification().addOnCompleteListener(
                this,
                new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Verification mail sent." , Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Sending mail failed.", Toast.LENGTH_LONG).show();
                            Log.d(TAG, task.getException().getLocalizedMessage());
                        }
                    }
                }
        );

    }


}
