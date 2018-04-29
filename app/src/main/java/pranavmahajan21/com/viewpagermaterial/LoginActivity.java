package pranavmahajan21.com.viewpagermaterial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import pranavmahajan21.com.viewpagermaterial.dao.UserDAO;
import pranavmahajan21.com.viewpagermaterial.util.Constants;

public class LoginActivity extends DaddyActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    @BindView(R.id.username_ET)
    EditText username_ET;
    @BindView(R.id.password_ET)
    EditText password_ET;


    FirebaseAuth myFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private GoogleApiClient mGoogleApiClient;


    UserDAO userDAO;
    Intent previousIntent, nextIntent;

    private static int REQUEST_CODE_SIGNUP = 0;
    //    private static int REQUEST_CODE_UPDATE_PROFILE = 1;
    private static int REQUEST_CODE_FOROGOT = 10;
    private static final int RC_SIGN_IN = 9001;

    private BroadcastReceiver loginReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i(Constants.APP_NAME, "LoginActivity      loginReceiver  : ");
            progressDialog.dismiss();
            if (intent.getBooleanExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, false)) {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                FirebaseUser user = firebaseAuth.getCurrentUser();

                myApp.setLoggedInUser(user);
                if (intent.getBooleanExtra(Constants.EXTRAS_IS_LOGIN_USER_PROFILE_FOUND, false)) {
                    /* If a profile is found for the logged-in user */
                    gotoEventLogin();
                } /*else {
                    onSignUpToCompleteOrEditProfile(true, true);
                }*/

            } else {
                Log.i(Constants.APP_NAME, "LoginActivity      loginReceiver         Login Failed        " + intent.getStringExtra(Constants.EXTRAS_LOGIN_FAIL_ERROR_MSG));
                Toast.makeText(LoginActivity.this, "Login Failed  - " + intent.getStringExtra(Constants.EXTRAS_LOGIN_FAIL_ERROR_MSG), Toast.LENGTH_LONG).show();
            }
        }
    };

    public void initThings() {
        super.initThings();
        userDAO = new UserDAO(this, mDatabase);

        previousIntent = getIntent();


        myFirebaseAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(Constants.APP_NAME, "LoginActivity            onAuthStateChanged:signed_IN:       " + user.getUid());
//                    createUserInFirebaseHelper();
                } else {
                    // User is signed out
                    Log.d(Constants.APP_NAME, "LoginActivity            onAuthStateChanged:signed_OUT");
                }
                // ...
            }
        };
    }

    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    private SignInButton myGoogleSignInButton;

    private void findThings() {
//        username_ET = (EditText) findViewById(R.id.username_ET);
        password_ET = (EditText) findViewById(R.id.password_ET);
    }

    private void initListeners() {

    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: 18-11-2016 https://console.firebase.google.com/project/lovelycrap-62747/database/data
//        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initThings();
        findThings();
        initListeners();

        /* Admin */
//        username_ET.setText("pranavmahajan21@gmail.com");
        /* Speaker */
//        username_ET.setText("speakeremaila@gmail.com");
        /* Attendee */
//        username_ET.setText("attendee1@gmail.com");
//        password_ET.setText("helloworld1!");

        /* Web CREDENTIALS */
        username_ET.setText("officialpmahajan@gmail.com");
        password_ET.setText("helloworld");


        FirebaseUser user = myFirebaseAuth.getCurrentUser();
        if (myApp.getLoggedInUser() != null) {
            if (myApp.getLoggedInUserProfile() != null) {
                gotoEventLogin();
            } else {
                /* If a profile is NOT availabe for the logged-in user */
                /*onSignUpToCompleteOrEditProfile(true, true);*/
            }
        }

    }




    /*public void onLogOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                    }
                });
    }*/

    private boolean validate() {
        boolean notErrorCase = true;
        String email = username_ET.getText().toString().trim();
        if (myApp.isETEmpty(username_ET)) {
            username_ET.setError("Can't be empty");
            notErrorCase = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            username_ET.setError("Not a proper email");
            notErrorCase = false;
        }
        if (password_ET.getText().toString().length() < 6) {
            password_ET.setError("Min 6 characters");
            notErrorCase = false;
        }
        return notErrorCase;
    }

    public void onLogin(View view) {
        Log.i(Constants.APP_NAME, "LoginActivity      FUNCTION       onLogin()");

        if (!validate()) {
            return;
        }

        progressDialog = createDialog.createProgressDialog("Login", "Login", false, null);
        progressDialog.show();

        userDAO.login(username_ET.getText().toString(), password_ET.getText().toString());
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode == REQUEST_CODE_SIGNUP) {
            if (resultCode == RESULT_OK) {
                username_ET.setText(data.getStringExtra(Constants.EXTRAS_SIGN_UP_USERNAME));
                password_ET.setText("");

                FirebaseAuth.getInstance().signOut();
            }
        } else if (requestCode == Constants.REQ_CODE_COMPLETE_PROFILE) {
            if (resultCode == RESULT_OK) {
                gotoEventLogin();
            }
        } else if (requestCode == REQUEST_CODE_FOROGOT) {
            if (resultCode == RESULT_OK) {
                String x = data.getStringExtra(Constants.EXTRAS_SIGN_UP_USERNAME);
                username_ET.setText(x);
            }
        }
    }



    private void firebaseAuthWithGoogle(AuthCredential credential) {
        myFirebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.i(Constants.APP_NAME, "LoginActivity      firebaseAuthWithGoogle()        signInWithCredential:onComplete       " + task.isSuccessful());
                        if (!task.isSuccessful()) {
                            Log.i(Constants.APP_NAME, "LoginActivity      firebaseAuthWithGoogle()        Firebase Sign In was UNSUCCESSFUL" + task.getException().getMessage());
                            task.getException().printStackTrace();

                        } else {
                            Log.i(Constants.APP_NAME, "LoginActivity      firebaseAuthWithGoogle()        Login Successful with G+");

                            Toast.makeText(LoginActivity.this, "Login Successful with G+",
                                    Toast.LENGTH_SHORT).show();

                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            userDAO.findProfileByEmail(user.getEmail());
                        }
                    }
                });
    }

    public void gotoEventLogin() {
        nextIntent = new Intent(LoginActivity.this, EventLoginTabActivity.class);
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(nextIntent);
    }




    @Override
    protected void onResume() {
        super.onResume();

        LocalBroadcastManager.getInstance(this).registerReceiver(
                loginReceiver, new IntentFilter(Constants.RECEIVER_LOGIN));
    }

    @Override
    protected void onPause() {
        super.onPause();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(
                loginReceiver);
    }

    @Override
    public void onStart() {
        super.onStart();
        myFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            myFirebaseAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
