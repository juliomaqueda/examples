package examples.springBoot.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping("/")
	@ResponseBody
	String index() {

		return "Hello world!";
	}


	@RequestMapping(value="/home/{nombre}", method = RequestMethod.GET)
	@ResponseBody
	String friendlyUrl(@PathVariable("nombre") String nombre) {

		return "home de " + nombre;
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	@ResponseBody
	String uglyUrl(@RequestParam("nombre") String nombre) {

		return "home de " + nombre;
	}


	public static void main(String[] args) throws Exception {

		SpringApplication.run(SampleController.class, args);
	}
}
