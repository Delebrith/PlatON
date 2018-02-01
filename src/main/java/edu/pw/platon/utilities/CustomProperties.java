package edu.pw.platon.utilities;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "custom")
public class CustomProperties {

    private String enrollments;

    public String getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(String enrollments) {
        this.enrollments = enrollments;
    }
}
