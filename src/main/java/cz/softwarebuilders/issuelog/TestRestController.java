package cz.softwarebuilders.issuelog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("greetings")
public class TestRestController {
    @Autowired
    private TestBean testBean;

    //Call it with ../greetings/hello
    @RequestMapping("/hello")
    public String hello() {
        return "This is hello method of test rest controller calling testBean with result: " + testBean.whowAmI();
    }

    //Call it with ../greetings/getName/Jon/Doe
    @RequestMapping(value = "/getName/{name}/{surname}")
    public String getName(@PathVariable String name, @PathVariable String surname) {
        return name + " " + surname;
    }

    //Call it with ../greetings/helloWithParams?jmeno=X&prijmeni=Y&vek=100
    @RequestMapping(value = "/helloWithParams")
    public String helloWithParams(@RequestParam("jmeno") String name,
                                  @RequestParam("prijmeni") String surname,
                                  @RequestParam("vek") String age) {
        return name + " " + surname + " " + age;
    }

    @RequestMapping(value = "/getPerson", method = RequestMethod.GET, produces = "application/json")
    public Person getPerson(@RequestParam("name") String name) {
        return new Person(name, "Doe", 33);
    }

    //Call it with ../greetings/hey
    @RequestMapping("/hey")
    public String hey() {
        return "hey";
    }

    //Call it with ../greetings
    @RequestMapping
    public String defaultGreeting() {
        return "hi";
    }
}
