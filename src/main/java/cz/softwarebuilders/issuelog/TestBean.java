package cz.softwarebuilders.issuelog;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestBean {
    public String whowAmI() {
        return "Spring Test Bean " + new Date();
    }
}
