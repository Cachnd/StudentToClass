package com.stc.demo.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

public class ClassDTO {

    private Integer class_code;

    private String title;

    private String description;

    public ClassDTO() {
    }

    public ClassDTO(Class classroom){
        this.class_code = classroom.getCode();
        this.title = classroom.getTitle();
        this.description = classroom.getDescription();
    }

    public Integer getCode() {
        return class_code;
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

}