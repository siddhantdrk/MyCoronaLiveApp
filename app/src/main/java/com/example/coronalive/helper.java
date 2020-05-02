package com.example.coronalive;

public class helper {
    private String state, confirm, recover, death;

    public helper() {

    }

    public helper(String state, String confirm, String recover, String death) {
        this.state = state;
        this.confirm = confirm;
        this.recover = recover;
        this.death = death;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public String getRecover() {
        return recover;
    }

    public void setRecover(String recover) {
        this.recover = recover;
    }

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }
}
