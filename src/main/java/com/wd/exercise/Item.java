package com.wd.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

    private String name;

    @JsonProperty("full_name")
    private String fullName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
