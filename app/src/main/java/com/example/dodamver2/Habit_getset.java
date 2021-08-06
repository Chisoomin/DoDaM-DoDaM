package com.example.dodamver2;

class Habit_getset {
    String numId, goal;
    int step;

    public Habit_getset(String numId, String goal, int step) {
        this.numId = numId;
        this.goal = goal;
        this.step = step;
    }

    public String getNumId() {
        return numId;
    }

    public void setNumId(String numId) {
        this.numId = numId;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
