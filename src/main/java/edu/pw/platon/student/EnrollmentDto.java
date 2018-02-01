package edu.pw.platon.student;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentDto {
    private String subjectCode;
    private String realCode;
    private String subjectName;
    private int ects;
    private String passMethod;
    private String semCode;
    private String enrollStatus;
}
