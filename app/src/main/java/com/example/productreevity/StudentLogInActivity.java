package com.example.productreevity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.productreevity.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class StudentLogInActivity extends AppCompatActivity {

    private static final String TAG = "Student Login Activity";
    AppCompatActivity mActivty = this;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef = mRootRef.child("users");
    FirebaseUser mUser;

    private Button button12; //student log-in button
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_log_in);
        button12 = (Button) findViewById(R.id.student_login_button);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudLogInStudHome();
            }
        });
    }
    public void openStudLogInStudHome() {
        Intent intent = new Intent(this, StudentHomeActivity.class);
        startActivity(intent);
    }
    public void login(View view) { // called by button
        final Button loginButton = (Button) findViewById(R.id.student_login_button);
        final EditText name = (EditText) findViewById(R.id.student_name);
        final EditText username = (EditText) findViewById(R.id.student_username);
        final EditText password = (EditText) findViewById(R.id.student_password);
        final EditText passwordConfirm = (EditText) findViewById(R.id.student_confirm_password);
        final EditText studentID = (EditText) findViewById(R.id.student_id);
        DatabaseReference userRef = mUsersRef.child(mUser.getUid());
        userRef.addListenerForSingleValueEvent(postUserReadListener);
        if(username.toString().equals("username") || username.getText().equals("")) {
//            loginExisting();
        } else {
//            verifyNotExisting();
//            checkPasswordMatch();
            User user = new User(mUser.getUid(), name.toString(), username.toString(), "null@null.null", password.toString(), "student", studentID.toString());
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            userRef = database.getReference("users");
            user.changeSeeds(1);
            Map<String, Object> userData = new HashMap<>();
            userData.put(mUser.getUid(), user);
            mUsersRef.updateChildren(userData);
        }
    }
    private ValueEventListener postUserReadListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                Intent i = new Intent(mActivty, StudentHomeActivity.class);
                startActivity(i);

                User user = dataSnapshot.getValue(User.class);
                user.changeSeeds(1);
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
            } else { //create new profile
//                User user = new User(mUser.getUid(), "Varij", "jhaveriv", "jhaveriv@uci.edu", "qwerty", "student", "7894562");
//                FirebaseDatabase database = FirebaseDatabase.getInstance();
//                DatabaseReference userRef = database.getReference("users");
//                user.changeSeeds(1);
//                Map<String, Object> userData = new HashMap<>();
//                userData.put(mUser.getUid(), user);
//                mUsersRef.updateChildren(userData);
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