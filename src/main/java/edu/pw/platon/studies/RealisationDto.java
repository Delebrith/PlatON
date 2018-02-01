package edu.pw.platon.studies;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RealisationDto {

    private String code;
    private String realName;
    private String name;
    private int ects;
    private String passMethod;
    private String semCode;
}
