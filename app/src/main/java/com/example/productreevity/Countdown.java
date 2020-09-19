/**
 * Countdown timer - outputs string in correct format.
 * Two constructor: h-m-s and m-s
 * Four decrementers: h-m-s, m-s, s, and default (one second less)
 */

package com.example.productreevity;

public class Countdown {
    private int secs;
    public Countdown(int minutes, int seconds) {
        this.secs = minutes*60 + seconds;
    }
    public Countdown(int hours, int minutes, int seconds) {
        this.secs = hours*3600 + minutes*60 + seconds;
    }
    public int[] breakdown() {
        int[] time = new int[3];
        int secs = this.secs; // doesn't change original
        time[0] = secs / 3600;
        secs = secs % 3600;
        time[1] = secs / 60;
        secs = secs % 60;
        time[2] = secs;
        return time;
    }
    public String toString() {
        String time = "";
        int[] breakdown = breakdown();
        if(breakdown[0] > 0) {
            time = String.format("%02d", breakdown[0]) + ":";
        }
        time = time + String.format("%02d", breakdown[1]) + ":";
        time = time + String.format("%02d", breakdown[2]);
        return time;
    }
    public void decrease() {
        this.secs--;
    }
    public void decrease(int seconds) {
        this.secs -= seconds;
    }
    public void decrease(int minutes, int seconds) {
        this.secs -= (minutes*60 + seconds);
    }
    public void decrease(int hours, int minutes, int seconds) {
        this.secs -= (hours*3600 + minutes*60 + seconds);
    }
    public int getSecs() {
        return secs;
    }
}