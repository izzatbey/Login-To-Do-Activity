package com.example.logintodo.data.model;

import com.example.logintodo.base.BaseModel;

public class Task extends BaseModel {
    private String id;
    private String title;
    private String description;
    private Integer isComplete = 0;

    public Task(String id, String title, String description, Integer isComplete) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public Task(String title, String description, Integer isComplete) {
        this.title = title;
        this.description = description;
        this.isComplete = isComplete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsComplete() {
        return isComplete;
    }

    public void setIsComplete(Integer isComplete) {
        this.isComplete = isComplete;
    }

}
