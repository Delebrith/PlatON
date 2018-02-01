package edu.pw.platon.annonymous.api;

import edu.pw.platon.studies.ClassDate;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
public class ScheduleInfoDto extends Response {

    private ClassDate[] classDates;
}
