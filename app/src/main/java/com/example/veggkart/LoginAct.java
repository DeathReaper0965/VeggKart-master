package com.example.veggkart;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

public class LoginAct extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    AutoCompleteTextView emailText;
    TextInputLayout emailTextInputLayout, passwordTextInputLayout;
    EditText passwordText;
    Button loginButton;


    private static final int SIGNED_IN = 0;
    private static final int SIGNING_IN_STATE = 1;
    private static final int STATE_PROGRESS = 2;
    private static final int SIGN_IN = 0;

    private GoogleApiClient mGoogleApiClient;
    private int mSignInProgress;
    private PendingIntent mSignInIntent;

    private ImageButton mSignInButton;
    private TextView emailLabel, nameLabel, user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTextInputLayout = (TextInputLayout) findViewById(R.id.emailTextInputLayout);
        passwordTextInputLayout = (TextInputLayout) findViewById(R.id.passwordTextInputLayout);
        emailText = (AutoCompleteTextView) findViewById(R.id.emailText);
        passwordText = (EditText) findViewById(R.id.passwordText);
        loginButton = (Button) findViewById(R.id.loginButton);

        mSignInButton = (ImageButton) findViewById(R.id.sign_in_button);
        emailLabel = (TextView) findViewById(R.id.emailLabel);
        mSignInButton.setOnClickListener(this);
        nameLabel = (TextView) findViewById(R.id.nameLabel);
        user_name = (TextView) findViewById(R.id.user_name);

        mGoogleApiClient = buildGoogleApiClient();

    }

    public void submit(View view) {
        if(!validateEmail())
            return;
        if(!validatePassword())
            return;
        Toast.makeText(LoginAct.this, "Yo! Successfully Registered!", Toast.LENGTH_SHORT).show();
    }

    public boolean validateEmail(){
        String email = emailText.getText().toString().trim();
        if(email.isEmpty() || !isValidEmail(email)){
            emailTextInputLayout.setError("Enter a valid email!");
            requestFocus(emailText);
            return false;
        }else{
            emailTextInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    public boolean validatePassword(){
        if(passwordText.getText().toString().trim().isEmpty()){
            passwordTextInputLayout.setError("Enter the password!");
            requestFocus(passwordText);
            return false;
        }else{
            passwordTextInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    private boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private void requestFocus(View view) {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void onClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.forgotPasswordTextView :
                Toast.makeText(LoginAct.this, "Yo! forgotPasswordTextView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.newUserTextView :
                Toast.makeText(LoginAct.this, "Yo! newUserTextView", Toast.LENGTH_SHORT).show();
                break;
            case R.id.sign_in_button :

                googleSignIn();
                break;
        }
    }

    public void onImageClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.facebookImageBtn:
                Toast.makeText(LoginAct.this, "Yo! This is facebook btn", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private GoogleApiClient buildGoogleApiClient() {
        return new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(Plus.API, Plus.PlusOptions.builder().build())
                .addScope(new Scope("email")).addScope(Plus.SCOPE_PLUS_LOGIN)
                .build();
    }

    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    protected void onStop() {
        super.onStop();
        if (mGoogleApiClient.isConnected()) {
            Plus.AccountApi.clearDefaultAccount(mGoogleApiClient);
            mGoogleApiClient.disconnect();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        mSignInButton.setEnabled(false);

        mSignInProgress = SIGNED_IN;

        try {
            String emailAddress = Plus.AccountApi.getAccountName(mGoogleApiClient);
            emailLabel.setText(String.format("Signed In to VeggKart as %s", emailAddress));
            Person person = Plus.PeopleApi.getCurrentPerson(mGoogleApiClient);
            String name = person.getDisplayName();
            nameLabel.setText(String.format("Name retrieved from G+ : %s", name));
            user_name.setText(name);
        }
        catch(Exception ex){
            String exception = ex.getLocalizedMessage();
            String exceptionString = ex.toString();
            Log.d("Exception", exception);
            Log.d("ExceptionString", exceptionString);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if (mSignInProgress != STATE_PROGRESS) {
            mSignInIntent = connectionResult.getResolution();
            if (mSignInProgress == SIGNING_IN_STATE) {
                resolveSignInError();
            }
        }

        onSignedOut();
    }

    private void resolveSignInError() {
        if (mSignInIntent != null) {
            try {
                mSignInProgress = STATE_PROGRESS;
                startIntentSenderForResult(mSignInIntent.getIntentSender(),
                        SIGN_IN, null, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                mSignInProgress = SIGNING_IN_STATE;
                mGoogleApiClient.connect();
            }
        } else {
            Toast.makeText(this, "Play Services Error!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case SIGN_IN:
                if (resultCode == RESULT_OK) {
                    mSignInProgress = SIGNING_IN_STATE;
                } else {
                    mSignInProgress = SIGNED_IN;
                }

                if (!mGoogleApiClient.isConnecting()) {
                    mGoogleApiClient.connect();
                }
                break;
        }
    }

    private void onSignedOut() {
        mSignInButton.setEnabled(true);
        emailLabel.setText("Signed out");
    }

    private void googleSignIn(){
        if(!mGoogleApiClient.isConnecting()){
            emailLabel.setText("Signing In");
            resolveSignInError();
        }
    }
}
