package edu.pw.platon.annonymous.api;

import edu.pw.platon.studies.Realisation;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class SubjectInfoResponse extends Response {

    private String code;
    private String name;
    private int ects;
    private String passMethod;
    private String subjectDescription;
    private Realisation[] realisations;
}
