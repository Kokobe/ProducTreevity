package com.example.productreevity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    String studentID;

    protected void onCreate(Bundle savedInstanceState) {
        //hello

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.sharedPrefFileName), Context.MODE_PRIVATE);
        studentID = sharedPref.getString(getString(R.string.student_id_key), "");

        firebaseLogin();
        button = (ImageView) findViewById(R.id.button);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(mActivty, OccupationActivity.class);
            startActivity(intent);
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

                            //save uid for future
                            SharedPreferences sharedPref = getSharedPreferences(
                                    getString(R.string.sharedPrefFileName), Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString(getString(R.string.uid_key), mUser.getUid());
                            editor.apply();

                            studentID = sharedPref.getString(getString(R.string.student_id_key), "");

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

            if (dataSnapshot.exists()) { // if user exists




                User user = dataSnapshot.getValue(User.class);
                if (user.type.equals("student"))
                {
                    Intent i = new Intent(mActivty, StudentHomeActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(mActivty, TeacherHomeActivity.class);
                    startActivity(i);
                }
//                user.changeSeeds(1);
//                mUsersRef.child(user.user_id).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//                        Log.e(TAG, "success in writing");
//                    }
//                })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Log.e(TAG, "failure in writing");
//                            }
//                        });
            }
            else { //create new profile

               //have the user press the start button
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
            Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            // ...
        }
    };


}
