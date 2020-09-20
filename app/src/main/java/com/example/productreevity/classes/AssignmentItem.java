package com.example.productreevity.classes;

import android.annotation.SuppressLint;
import android.view.Gravity;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.productreevity.R;
import com.xwray.groupie.GroupieViewHolder;
import com.xwray.groupie.Item;

public class AssignmentItem extends Item<GroupieViewHolder> {

    public String text;

    public AssignmentItem(String s) {
        this.text = s;
    }

    @Override
    public void bind(@NonNull GroupieViewHolder viewHolder, int position) {
        ((TextView) viewHolder.itemView.findViewById(R.id.assignment_name)).setText(text);
        //((TextView) viewHolder.itemView.findViewById(R.id.assignment_name)).setGravity(Gravity.LEFT);
    }

    @SuppressLint("ResourceType")
    @Override
    public int getLayout() {
        return R.layout.assignment_item;
    }
}
