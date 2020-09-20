package com.example.productreevity.classes;

import java.util.Date;

public class Assignment {
    public long numSeeds;
    public String name;
    public Date dueDate;
    public Assignment(){}
    public Assignment(long numSeeds, String name, Date dueDate) {
        this.numSeeds = numSeeds;
        this.name = name;
        this.dueDate = dueDate;
    }
    public long getNumSeeds() {
        return numSeeds;
    }
}
