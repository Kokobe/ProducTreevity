package com.example.productreevity;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.tasks.OnCompleteListener;
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

    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef = mRootRef.child("condition");
    
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
                            FirebaseUser user = mAuth.getCurrentUser();

                            // Write a message to the database @VARJ
                            User student = new User("Varij", "lasdjkf", "123456", false);
                            FirebaseDatabase database = FirebaseDatabase.getInstance();
                            DatabaseReference userRef = database.getReference("user2");

                            Map<String, Object> hopperUpdates = new HashMap<>();
                            hopperUpdates.put("nickname", "Amazing");
                            hopperUpdates.put("lol", "lalalal");
                            hopperUpdates.put("jhaveriv", student);
                            userRef.updateChildren(hopperUpdates);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        Log.e(TAG, "did something");

    }


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

    public void openOnboard1() {
        Intent intent = new Intent(this, OccupationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.getValue(String.class);
                Log.e(TAG, "condition: "  + text);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }



}
