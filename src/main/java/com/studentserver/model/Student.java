package com.studentserver.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    private String studentId;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull @NotEmpty
    private String surname;

    @NotNull @NotEmpty
    private String phoneNumber;

    @Min(1) @Max(6)
    private int studyYear;
    @Override
    public String toString(){
        return name + " " + surname + " " + phoneNumber + " " + studyYear;
    }

}
