package com.example.productreevity.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.productreevity.R;
import com.example.productreevity.home.TeacherHomeActivity;
import com.example.productreevity.classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class TeacherLogInActivity extends AppCompatActivity {


    private static final String TAG = "Teacher Login Activity";
    AppCompatActivity mActivty = this;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef = mRootRef.child("users");

    private Button teacher_login_button; //teacher log-in button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_log_in);

        teacher_login_button = (Button) findViewById(R.id.teacher_login_button);
        teacher_login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }
    public void openTeachLogInStudHome() {
        Intent intent = new Intent(this, TeacherHomeActivity.class);
        startActivity(intent);
    }
    public void login(View view) { // called by button
        final EditText name = (EditText) findViewById(R.id.teacher_name);
        final EditText username = (EditText) findViewById(R.id.teacher_username);
        final EditText password = (EditText) findViewById(R.id.teacher_password);
        final EditText passwordConfirm = (EditText) findViewById(R.id.teacher_confirm_password);
        final EditText teacherID = (EditText) findViewById(R.id.class_id);

        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.sharedPrefFileName), Context.MODE_PRIVATE);
        String uid = sharedPref.getString(getString(R.string.uid_key), "");
        Log.e(TAG, "my uid: " + uid);

        if(username.toString().equals("username") || username.getText().equals("")) {
            Log.e(TAG, "login needs to change");
        } else {
//            verifyNotExisting();
//            checkPasswordMatch();

            User user = new User(teacherID.getText().toString(), name.getText().toString(), username.getText().toString(),
                    "null@null.null", password.getText().toString(), "teacher", teacherID.getText().toString());
            user.changeSeeds(1);
            Map<String, Object> userData = new HashMap<>();
            userData.put(uid, user);
            mUsersRef.updateChildren(userData);

            Intent i = new Intent(mActivty, TeacherHomeActivity.class);
            startActivity(i);

        }
    }
    private ValueEventListener postUserReadListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                Intent i = new Intent(mActivty, TeacherHomeActivity.class);
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
//                User user = new User(mUser.getUid(), "Varij", "jhaveriv", "jhaveriv@uci.edu", "qwerty", "teacher", "7894562");
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