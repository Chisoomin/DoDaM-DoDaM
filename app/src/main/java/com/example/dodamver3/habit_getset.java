package com.example.dodamver3;

class habit_getset {
    String habit_num, habit, date;

    public habit_getset(String habit_num, String habit, String date) {
        this.habit_num = habit_num;
        this.habit = habit;
        this.date = date;
    }

    public String getHabit_num() {
        return habit_num;
    }

    public void setHabit_num(String habit_num) {
        this.habit_num = habit_num;
    }

    public String getHabit() {
        return habit;
    }

    public void setHabit(String habit) {
        this.habit = habit;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}

