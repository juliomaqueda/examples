package examples.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class HelloWorldInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { HelloWorldConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}

/**
 * The content above resembles the content of web.xml from previous tutorial as we are using the front-controller
 * DispatherServler, assigning the mapping (url-pattern in xml) and instead of providing the path to spring
 * configuration file (spring-servlet.xml), here we are registering the Configuration Class. Overall, we are doing
 * the same thing, just the approach is different.
 */

