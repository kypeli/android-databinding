package com.github.kypeli.databindingexample;

public class TimeModel {
    private long totalTimeCenti = 0;

    public void addTotalTimeCenti(long centi) {
        this.totalTimeCenti += centi;
    }

    public int getMinutes() {
        return (int)(((totalTimeCenti / 100) / 60) % 60);
    }

    public int getSeconds() {
        return (int)((totalTimeCenti / 100) % 60);
    }

    public int getCentiSecond() {
        return (int)(totalTimeCenti % 100);
    }
}
