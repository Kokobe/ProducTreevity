package com.example.productreevity.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.productreevity.R;
import com.example.productreevity.classes.Assignment;
import com.example.productreevity.classes.AssignmentItem;
import com.example.productreevity.classes.User;
import com.example.productreevity.startAssignment.TimerMainActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.xwray.groupie.GroupAdapter;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;
import com.xwray.groupie.OnItemClickListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentHomeActivity extends AppCompatActivity {
    private static final String TAG = "StudentHomeActivity";
    private ImageView button4; //timer button
    private ImageView button6; //student stats button
    private ImageView imageView8; //student shop button

    AppCompatActivity mActivty = this;
    DatabaseReference mRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mUsersRef = mRootRef.child("users");
    FirebaseUser mUser;
    String studentID;


//    Intent intent = new Intent(getBaseContext(), SignoutActivity.class);
//intent.putExtra("EXTRA_SESSION_ID", sessionId);
//    startActivity(intent);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        button4 = (ImageView) findViewById(R.id.start_assignment_btn);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openTimer("Trigonometry");
            }
        });
        button6 = (ImageView) findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStats();
            }
        });
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openStudShop();
            }
        });
        addAssignmentsToFirebase();
        getAssignmentsFromFirebase();

    }

    void initSpinner(List<Assignment> assignments) {
        RecyclerView recyclerView = findViewById(R.id.todo_recycler);
        GroupAdapter<GroupieViewHolder> adapter = new GroupAdapter<GroupieViewHolder>();
        RecyclerView.LayoutManager recyce = new
                LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(recyce);
        recyclerView.setAdapter(adapter);

        for (Assignment s : assignments)
        {
            adapter.add(new AssignmentItem(s.name));
        }

        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull Item item, @NonNull View view) {
                openTimer(((AssignmentItem) item).text);
            }
        });

    }

    public void getAssignmentsFromFirebase() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.sharedPrefFileName), Context.MODE_PRIVATE);
        String uid = sharedPref.getString(getString(R.string.uid_key), "");

        DatabaseReference userRef = mUsersRef.child(uid);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<Assignment> assignments = new ArrayList<Assignment>();
                for (DataSnapshot p: dataSnapshot.child("assignments").getChildren())
                {
                    assignments.add(p.getValue(Assignment.class));
                }
                initSpinner(assignments);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void addAssignmentsToFirebase() {
        SharedPreferences sharedPref = getSharedPreferences(
                getString(R.string.sharedPrefFileName), Context.MODE_PRIVATE);
        String uid = sharedPref.getString(getString(R.string.uid_key), "");

        DatabaseReference userRef = mUsersRef.child(uid);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                List<Assignment> l = new ArrayList<Assignment>();
                l.add(new Assignment(5, "ICS 33", new Date()));
                l.add(new Assignment(5, "Bio Chapter 1", new Date()));
                l.add(new Assignment(5, "Hum Core Article", new Date()));
                l.add(new Assignment(5, "ICS 46", new Date()));
                l.add(new Assignment(5, "Psychology Chap 2", new Date()));
                l.add(new Assignment(5, "Algebra", new Date()));
                l.add(new Assignment(5, "Sociology 3A", new Date()));
                l.add(new Assignment(5, "Writing 3C", new Date()));

                mUsersRef.child(uid).child("assignments").setValue(l).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.e("YES", "successfully pushed");
                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.e("NOOO", "failure in writing");
                            }
                        });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void openTimer(String caller) {
        Log.e("Custom1", caller);
        Intent intent = new Intent(getBaseContext(), TimerMainActivity.class);
        intent.putExtra("CALLER", caller);
        startActivity(intent);
    }
    public void openStats() {
        Intent intent = new Intent(this, StudentStatsActivity.class);
        startActivity(intent);
    }
    public void openStudShop() {
        Intent intent = new Intent(this, StudentShopActivity.class);
        startActivity(intent);
    }

}

