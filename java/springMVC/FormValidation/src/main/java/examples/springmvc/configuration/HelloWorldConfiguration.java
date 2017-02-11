package examples.springmvc.configuration;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "examples.springmvc")
public class HelloWorldConfiguration extends WebMvcConfigurerAdapter {

	/**
	 * Configure View Resolver
	 */
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}

	/**
	 * Configure ResourceHandlers to serve static resources like css, javascript etc...
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	/**
	 * Configure MessageSource to provide internationalized messages
	 */
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");

		return messageSource;
	}
}

/**
 * @Configuration indicates that this class contains one or more bean methods annotated with @Bean producing bean
 * manageable by spring container.@EnableWebMvc is equivalent to mvc:annotation-driven in XML. It enables support
 * for @Controller-annotated classes that use @RequestMapping to map incoming requests to specific method.
 * @ComponentScan is equivalent to context:component-scan base-package="..." providing with where to look for spring
 * managed beans/classes.
 * 
 * Method viewResolver configures a view resolver to identify the real view. Method addResourceHandlers configures
 * the ResourceHandler for static resources. CSS, JavaScript, images etc are static resources your pages needs.
 * Above configuration says that all resource requests starting with /static/ will be served from /static/ folder
 * under webapp. In this example, we will put all the css files under /static/css inside webapp directory.
 * Note that this method is defined in WebMvcConfigurerAdapter so we needed to extend this class to override this
 * method in order to register our static resources.
 * 
 * Method messageSource configures a Message bundle to support [internationalized] messages from properties file.
 * Notice the parameter provided (messages) to baseName method. Spring will search for a file named messages.properties
 * in application class path.
 */
