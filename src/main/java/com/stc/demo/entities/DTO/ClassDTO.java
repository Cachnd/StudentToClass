package com.stc.demo.entities.DTO;

import com.stc.demo.entities.Class;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ClassDTO {

    private Integer class_code;

    private String title;

    private String description;

    public ClassDTO(Class classroom){
        this.class_code = classroom.getClass_code();
        this.title = classroom.getTitle();
        this.description = classroom.getDescription();
    }

}