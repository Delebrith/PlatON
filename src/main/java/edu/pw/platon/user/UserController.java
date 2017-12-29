package edu.pw.platon.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) {
        log.info("request: {}", username + " " + password);
        User user = new User();
        user.setUsername(username);
        user.setEmail(password);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
