package cz.softwarebuilders.tutorial.restweb.spring.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("greetings")
public class TestRestController {
    @Autowired
    private TestBean testBean;

    @RequestMapping("/hello")
    public String hello() {
        return "This is hello method of test rest controller calling testBean with result: " + testBean.whowAmI();
    }

    @RequestMapping("/nazdar")
    public String nazdar() {
        return "nazdarek";
    }
}
