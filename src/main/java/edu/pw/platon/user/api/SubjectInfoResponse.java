package edu.pw.platon.user.api;

import edu.pw.platon.studies.Realisation;
import lombok.Data;

@Data
public class SubjectInfoResponse extends Response {

    private String code;
    private String name;
    private int ects;
    private String passMethod;
    private String description;
    private Realisation[] realisations;
}
