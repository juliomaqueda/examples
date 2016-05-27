package examples.springBoot.server;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import examples.springBoot.server.domain.Person;


@RestController
public class PersonController {

    private static final String template = "Hello, %s!";

    private final AtomicLong counter = new AtomicLong();


    @RequestMapping("/person")
    public Person person(@RequestParam(value="name", defaultValue="invitado") String name) {

        return new Person(counter.incrementAndGet(), String.format(template, name));
    }
}
