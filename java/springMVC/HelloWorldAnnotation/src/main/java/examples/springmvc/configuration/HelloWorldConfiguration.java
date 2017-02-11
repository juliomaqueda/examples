package examples.springmvc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "examples.springmvc")
public class HelloWorldConfiguration {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
}

/**
 * @Configuration indicates that this class contains one or more bean methods annotated with @Bean producing
 * bean manageable by spring container.
 * 
 * @EnableWebMvc is equivalent to mvc:annotation-driven in XML. It enables support for @Controller-annotated
 * classes that use @RequestMapping to map incoming requests to specific method.
 * 
 * @ComponentScan is equivalent to context:component-scan base-package="..." providing with where to look for
 * spring managed beans/classes.
 */
