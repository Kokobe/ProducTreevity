package com.example.productreevity;

import android.content.Intent;
import android.os.Bundle;

import com.example.productreevity.classes.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;

import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class StartActivity extends AppCompatActivity {
    private String TAG = "MainActivity";
    private FirebaseAuth mAuth;
    private ImageView button;

    AppCompatActivity mActivty = this;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef = mRootRef.child("users");
    FirebaseUser mUser;

    protected void onCreate(Bundle savedInstanceState) {
        //hello
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        firebaseLogin();
        button = (ImageView) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openOnboard1();
                Log.e("OnboardStart", "click");
            }
        });

    }


    void firebaseLogin() {

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();


        mAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.e(TAG, "signInAnonymously:success");
                            mUser = mAuth.getCurrentUser();

                            DatabaseReference userRef = mUsersRef.child(mUser.getUid());
                            userRef.addListenerForSingleValueEvent(postUserReadListener);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



    private ValueEventListener postUserReadListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                Intent i = new Intent(mActivty, StudentHomeActivity.class);
                startActivity(i);
                /*
                User user = dataSnapshot.getValue(User.class);
                user.seeds += 1;
                mUsersRef.child(user.user_id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e(TAG, "success in writing");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e(TAG, "failure in writing");
                            }
                        });

                 */
            }
            else { //create new profile
                User user = new User(mUser.getUid(),"Varij", "jhaveriv", "jhaveriv@uci.edu", "qwerty", "student", "7894562");
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference userRef = database.getReference("users");
                user.changeSeeds(1);
                Map<String, Object> userData = new HashMap<>();
                userData.put(mUser.getUid(), user);
                mUsersRef.updateChildren(userData);
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };




    public void openOnboard1() {
        Intent intent = new Intent(this, OccupationActivity.class);
        startActivity(intent);
    }

}