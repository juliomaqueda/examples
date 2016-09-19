package examples.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class HelloWorldController {

	@RequestMapping(method = RequestMethod.GET)
	public String sayHello(ModelMap model) {
		model.addAttribute("greeting", "Hello World from Spring 4 MVC");
		return "welcome";
	}

	@RequestMapping(value = "/helloagain", method = RequestMethod.GET)
	public String sayHelloAgain(ModelMap model) {
		model.addAttribute("greeting", "Hello World Again, from Spring 4 MVC");
		return "welcome";
	}

}

/*
 * @Controller annotation marks this class as spring bean which may handle different HTTP requests based
 * on mapping specified on class or individual controller methods.
 * 
 * @RequestMapping annotation is used for mapping web requests onto specific handler classes and/or
 * handler methods. In our case, we have applied it on class level too, which says that this class is
 * default handler for all HTTP requests of type ‘/’. @RequestMapping have several attributes
 * [value,method,params,..] which can be used to narrow down your mapping to more specific selection.
 * 
 * Attribute method is used to specify type of HTTP request this method can serve. If a mapping does not include
 * ‘method’ attribute, then that mapped controller method will server all types of request on that mapped URL.
 * 
 * ModelMap is a Map implementation, which saves you from old request.getAttribute/ request.setAttribute.
 * It provides a way to set/get attributes from/to request or session.
 */
