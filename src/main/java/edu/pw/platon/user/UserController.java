package edu.pw.platon.user;

import edu.pw.platon.studies.DegreeCourseRepository;
import edu.pw.platon.studies.RealisationRepository;
import edu.pw.platon.studies.RequirementRepository;
import edu.pw.platon.studies.SubjectRepository;
import edu.pw.platon.user.api.LoginRequestBody;
import edu.pw.platon.user.api.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final RealisationRepository realisationRepository;
    private final DegreeCourseRepository degreeCourseRepository;
    private final RequirementRepository requirementRepository;

    public UserController(@Autowired UserRepository userRepository,
                          @Autowired SubjectRepository subjectRepository,
                          @Autowired RealisationRepository realisationRepository,
                          @Autowired DegreeCourseRepository degreeCourseRepository,
                          @Autowired RequirementRepository requirementRepository,
                          @Autowired UserService userService) {
        this.userRepository = userRepository;
        this.subjectRepository = subjectRepository;
        this.realisationRepository = realisationRepository;
        this.degreeCourseRepository = degreeCourseRepository;
        this.requirementRepository = requirementRepository;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequestBody requestBody) {
        log.info("request to /user/username: {}", requestBody);
        return new ResponseEntity<>(userService.login(requestBody.getUsername(), requestBody.getPasswordHash()),
                HttpStatus.OK);
    }

    @GetMapping("/logout")
    public ResponseEntity<Object> logout(){
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @PostMapping("/changePassword")
    public ResponseEntity<Object> changePassword(){
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping("/subjectInfo")
    public ResponseEntity<Object> getSubjectInfo(@RequestParam String subjectCode) {
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping("/scheduleInfo")
    public ResponseEntity<Object> getScheduleInfo(@RequestParam String semesterCode, @RequestParam String subjectName){
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }

    @GetMapping("/requirementsInfo")
    public ResponseEntity<Object> getRequirementsInfo(@RequestParam int semesterNo, @RequestParam String courseCode){
        return new ResponseEntity<>(new Object(), HttpStatus.OK);
    }


}
