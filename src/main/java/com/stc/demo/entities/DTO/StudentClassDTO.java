package com.stc.demo.entities.DTO;

public class StudentClassDTO {

    private Integer class_code;

    private Integer student_id;

    public void setClass_code(Integer class_code) {
        this.class_code = class_code;
    }

    public Integer getClass_code() {
        return class_code;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public Integer getStudent_id() {
        return student_id;
    }
}