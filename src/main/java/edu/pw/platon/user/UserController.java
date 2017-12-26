package edu.pw.platon.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String login, @RequestParam String password) {
        return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
    }
}
