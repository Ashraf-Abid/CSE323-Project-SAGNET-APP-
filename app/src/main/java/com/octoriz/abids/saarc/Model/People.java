package com.octoriz.abids.saarc.Model;

import java.util.List;

public class People {
    private int peopleCount;
    private List<String> peopleNames = null;

    public People() {
    }

    public People(int peopleCount, List<String> peopleNames) {
        this.peopleCount = peopleCount;
        this.peopleNames = peopleNames;
    }

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public List<String> getPeopleNames() {
        return peopleNames;
    }

    public void setPeopleNames(List<String> peopleNames) {
        this.peopleNames = peopleNames;
    }
}
