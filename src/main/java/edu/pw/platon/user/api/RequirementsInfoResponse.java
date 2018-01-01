package edu.pw.platon.user.api;

import edu.pw.platon.studies.Subject;
import lombok.Data;

@Data
public class RequirementsInfoResponse extends Response {

    @Data
    public static class RequirementsInfo {
        private int semestrNo;
        private Subject[] subjects;
    }

    private String course;
    private RequirementsInfo[] requirementsInfo;
}
