package com.jeev.kedSpringbootApp.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "student")
public class Student {
    @Id
    private String id;
    @Field("student_id")
    private String studentId;
    @Field("first_name")
    private String firstName;
    @Field("last_name")
    private String lastName;
    private String branch;
}
