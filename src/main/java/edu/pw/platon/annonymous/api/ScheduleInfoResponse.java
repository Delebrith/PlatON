package edu.pw.platon.annonymous.api;

import edu.pw.platon.studies.ClassDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ScheduleInfoResponse extends Response {

    private ClassDate[] classDates;
}
