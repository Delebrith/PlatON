package edu.pw.platon.user.api;

import edu.pw.platon.studies.ClassDate;
import lombok.Data;

@Data
public class ScheduleInfoResponse extends Response {

    private ClassDate[] classDates;
}
