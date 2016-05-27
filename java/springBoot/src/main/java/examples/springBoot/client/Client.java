package examples.springBoot.client;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.client.RestTemplate;

import examples.springBoot.client.domain.Person;


@SpringBootApplication
public class Client implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(Client.class);


    public static void main(String args[]) {

        Map<String, Object> props = new HashMap<>();
        props.put("server.port", 9999);

        new SpringApplicationBuilder()
            .sources(Client.class)                
            .properties(props)
            .run(args);
    }


    @Override
    public void run(String... args) throws Exception {

        RestTemplate restTemplate = new RestTemplate();

        Person person = restTemplate.getForObject("http://localhost:8080/person", Person.class);

        log.info(person.toString());
    }
}
