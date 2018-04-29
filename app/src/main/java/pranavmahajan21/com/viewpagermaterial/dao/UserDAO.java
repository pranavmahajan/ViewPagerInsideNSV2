package pranavmahajan21.com.viewpagermaterial.dao;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import pranavmahajan21.com.viewpagermaterial.MyApp;
import pranavmahajan21.com.viewpagermaterial.model.Profile;
import pranavmahajan21.com.viewpagermaterial.util.Constants;

/**
 * Created by pranav on 12/9/16.
 */
public class UserDAO {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference mDatabase;
    Activity activity;

    Intent nextIntent;
    MyApp myApp;
    Gson gson;

    public UserDAO(Activity activity, DatabaseReference mDatabase) {

        this.activity = activity;
        this.mDatabase = mDatabase;

        myApp = (MyApp) activity.getApplicationContext();
        gson = new Gson();

        mAuth = FirebaseAuth.getInstance();
    }

    public void login(String username, String password) {

        // TODO: 12/9/16 When to exactly remove the listener
//        https://firebase.google.com/docs/auth/android/password-auth#create_a_password-based_account

        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(Constants.APP_NAME, "UserDAO      signInWithEmail:onComplete:  " + task.isSuccessful());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.

                        nextIntent = new Intent(Constants.RECEIVER_LOGIN);

                        if (task.isSuccessful()) {
                            Log.d(Constants.APP_NAME, "UserDAO      signInWithEmail:     SUCCESS");
                            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            findProfileByEmail(user.getEmail());
//                            nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, true);
                        } else {
                            Log.d(Constants.APP_NAME, "signInWithEmail:     FAILED  " + task.getException());
                            nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, false);
                            nextIntent.putExtra(Constants.EXTRAS_LOGIN_FAIL_ERROR_MSG, "Authentication Failed");
                            LocalBroadcastManager.getInstance(activity).sendBroadcast(nextIntent);
                        }

                    }
                });
    }

    public void findProfileByEmail(String email) {
        Log.d(Constants.APP_NAME, "UserDAO      findProfileByEmail()");

        ValueEventListener prefListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                nextIntent = new Intent(Constants.RECEIVER_LOGIN);


                Profile profile = dataSnapshot.getValue(Profile.class);
                if (profile == null) {
                    Log.d(Constants.APP_NAME, "UserDAO      findProfileByEmail()      Profile NOT Found  ");
//                    FirebaseAuth.getInstance().signOut();

                    nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, true);
                    nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_USER_PROFILE_FOUND, false);
                    nextIntent.putExtra(Constants.EXTRAS_LOGIN_FAIL_ERROR_MSG, "Profile Not Found");
                } else {
                    Log.d(Constants.APP_NAME, "UserDAO      findProfileByEmail()      Profile Found  ");
                    myApp.setLoggedInUserProfile(profile);

                    nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, true);
                    nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_USER_PROFILE_FOUND, true);
                }

                LocalBroadcastManager.getInstance(activity).sendBroadcast(nextIntent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(Constants.APP_NAME, "UserDAO      findProfileByEmail()      DatabaseError while finding Profile  ");

                nextIntent.putExtra(Constants.EXTRAS_IS_LOGIN_SUCCESSFUL, false);
                LocalBroadcastManager.getInstance(activity).sendBroadcast(nextIntent);
            }
        };
        DatabaseReference prefReference = mDatabase.child(Constants.TABLE_PROFILE).child(myApp.convertEmailToPath(email));
        prefReference.addListenerForSingleValueEvent(prefListener);
    }

    public void updateProfileAfterImageUpload(boolean isGenericProfile) {
        Map<String, Object> childUpdates = new HashMap<>();

        Profile profile = myApp.getLoggedInUserProfile();

        Map<String, Object> profilePostValues = profile.toMap();
        childUpdates.put("/Profile/" + myApp.convertEmailToPath(profile.getEmail()), profilePostValues);

        mDatabase.updateChildren(childUpdates);
    }

    public void signUpUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(Constants.APP_NAME, "UserDAO      createUserWithEmail:onComplete:  " + task.isSuccessful());
                        nextIntent = new Intent(Constants.RECEIVER_SIGN_UP);
                        if (task.isSuccessful()) {
                            Log.d(Constants.APP_NAME, "UserDAO      signUp:     SUCCESS");

                            nextIntent.putExtra(Constants.EXTRAS_IS_SIGN_UP_SUCCESSFUL, true);
                        } else {
                            String errorMsg = task.getException().getLocalizedMessage();
                            Log.d(Constants.APP_NAME, "UserDAO      signUp:     FAILED  " + errorMsg);
                            nextIntent.putExtra(Constants.EXTRAS_IS_SIGN_UP_SUCCESSFUL, false);
                            nextIntent.putExtra(Constants.EXTRAS_SIGN_UP_ERROR_MSG, "Sign Up failed");

                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                nextIntent.putExtra(Constants.EXTRAS_SIGN_UP_ERROR_MSG, errorMsg);
                            }
                        }
                        LocalBroadcastManager.getInstance(activity).sendBroadcast(nextIntent);
                    }
                });
    }
}
