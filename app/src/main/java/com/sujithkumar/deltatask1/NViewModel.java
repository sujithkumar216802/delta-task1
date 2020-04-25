package com.sujithkumar.deltatask1;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class NViewModel extends ViewModel {
    private boolean normaldone = false, hackerdone = false, hackerppdone = false, cancel = false;
    private Integer enteredno, correct, state = 0, hackercurrentscore = 0, hackerhighscore = 0, hackerppcurrentscore = 0, hackerpphighscore = 0, selected = 0;
    private long timeleft = 10000, threeseconddelay = 3000;
    private ArrayList<Integer> option = new ArrayList<>(3);
    private logic obj = new logic();


    long getThreeseconddelay() {
        return threeseconddelay;
    }

    void setThreeseconddelay(long threeseconddelay) {
        this.threeseconddelay = threeseconddelay;
    }

    Integer getEnteredno() {
        return enteredno;
    }

    void setEnteredno(Integer enteredno) {
        this.enteredno = enteredno;
        obj.findoptions2(this.enteredno);
        option = obj.getOption();
        correct = obj.getCorrrectans() + 1;

    }

    Integer getOption1() {
        return option.get(0);
    }

    Integer getOption2() {
        return option.get(1);
    }

    Integer getOption3() {
        return option.get(2);
    }

    Integer getState() {
        return state;
    }

    void setState(Integer state) {
        this.state = state;
    }

    Integer getCorrect() {
        return correct;
    }


    Integer getHackercurrentscore() {
        return hackercurrentscore;
    }

    void setHackercurrentscore(Integer hackercurrentscore) {
        this.hackercurrentscore = hackercurrentscore;
        if (this.hackercurrentscore > hackerhighscore) {
            hackerhighscore = this.hackercurrentscore;
        }
    }

    Integer getHackerhighscore() {
        return hackerhighscore;
    }

    void setHackerhighscore(Integer hackerhighscore) {
        this.hackerhighscore = hackerhighscore;
    }

    Integer getHackerppcurrentscore() {
        return hackerppcurrentscore;
    }

    void setHackerppcurrentscore(Integer hackerppcurrentscore) {
        this.hackerppcurrentscore = hackerppcurrentscore;
        if (this.hackerppcurrentscore > hackerpphighscore) {
            hackerpphighscore = this.hackerppcurrentscore;
        }
    }

    Integer getHackerpphighscore() {
        return hackerpphighscore;
    }

    void setHackerpphighscore(Integer hackerpphighscore) {
        this.hackerpphighscore = hackerpphighscore;
    }

    boolean isNormaldone() {
        return normaldone;
    }

    void setNormaldone(boolean normaldone) {
        this.normaldone = normaldone;
    }

    boolean isHackerdone() {
        return hackerdone;
    }

    void setHackerdone(boolean hackerdone) {
        this.hackerdone = hackerdone;
    }

    boolean isHackerppdone() {
        return hackerppdone;
    }

    void setHackerppdone(boolean hackerppdone) {
        this.hackerppdone = hackerppdone;
    }

    Integer getSelected() {
        return selected;
    }

    void setSelected(Integer selected) {
        this.selected = selected;
    }

    long getTimeleft() {
        return timeleft;
    }

    void setTimeleft(long timeleft) {
        this.timeleft = timeleft;
    }

    boolean isCancel() {
        return cancel;
    }

    void setCancel(boolean cancel) {
        this.cancel = cancel;
    }
}
