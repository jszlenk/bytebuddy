package com.kodilla.bytebuddy;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JavaAgentController {

    @GetMapping("/agent-test")
    public String testAgent() {
        User user = new User();
        return user.doSomething();
    }
}
