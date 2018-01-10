package edu.pw.platon.annonymous.api;

import edu.pw.platon.studies.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class RequirementsInfoResponse extends Response {

    @Data
    public static class RequirementsInfo {
        private int semestrNo;
        private Subject[] subjects;
    }

    private String course;
    private RequirementsInfo[] requirementsInfo;
}
