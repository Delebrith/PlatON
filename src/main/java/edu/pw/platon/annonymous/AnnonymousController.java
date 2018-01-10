package edu.pw.platon.annonymous;

import edu.pw.platon.annonymous.api.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class AnnonymousController {

    private final AnnonymousService annonymousService;

    public AnnonymousController(@Autowired AnnonymousService annonymousService) {
        this.annonymousService = annonymousService;
    }

    @GetMapping("/subjectInfo")
    public ResponseEntity<Response> getSubjectInfo(@RequestParam String subjectCode) {
        return new ResponseEntity<>(annonymousService.getSubjectInfo(subjectCode), HttpStatus.OK);
    }

    @GetMapping("/scheduleInfo")
    public ResponseEntity<Response> getScheduleInfo(@RequestParam String semesterCode, @RequestParam String subjectName){
        return new ResponseEntity<>(annonymousService.getScheduleInfo(semesterCode, subjectName), HttpStatus.OK);
    }

    @GetMapping("/requirementsInfo")
    public ResponseEntity<Response> getRequirementsInfo(@RequestParam String courseCode){
        return new ResponseEntity<>(annonymousService.getRequirementsInfo(courseCode), HttpStatus.OK);
    }


}
