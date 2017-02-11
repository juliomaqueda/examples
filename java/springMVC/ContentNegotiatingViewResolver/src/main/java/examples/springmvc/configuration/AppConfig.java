package examples.springmvc.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import examples.springmvc.model.Pizza;
import examples.springmvc.viewresolver.ExcelViewResolver;
import examples.springmvc.viewresolver.JsonViewResolver;
import examples.springmvc.viewresolver.Jaxb2MarshallingXmlViewResolver;
import examples.springmvc.viewresolver.PdfViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "examples.springmvc")
public class AppConfig extends WebMvcConfigurerAdapter {

	/**
	 * First step is to create the ContentNegotiationManager which is used to determine the requested media types
	 * of a request by delegating to a list of ContentNegotiationStrategy instances.
	 * By default PathExtensionContentNegotiationStrategy is consulted (which uses the URL extension e.g. .xls, .pdf,.json..),
	 * followed by ParameterContentNegotiationStrategy (which uses the request parameter �format=xls� e.g.), followed by
	 * HeaderContentNegotiationStrategy (which uses HTTP Accept Headers).
	 * 
	 * We will be using the URL extension to help determine the media types. Also, we have set the default media type
	 * to TEXT_HTML in absence of file extension or when the filetype is unknown, that means JSP view resolver will be
	 * used when no [known] URL extension found.
	 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML);
	}

	/**
	 * We need to set the ContentNegotiationManager which will be injected by Spring, and different resolvers for each
	 * possible output format our application might produce.
	 */
	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);

		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();

		resolvers.add(jaxb2MarshallingXmlViewResolver());
		resolvers.add(jsonViewResolver());
		resolvers.add(jspViewResolver());
		resolvers.add(pdfViewResolver());
		resolvers.add(excelViewResolver());

		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	/**
	 * Configure View resolver to provide XML output Uses JAXB2 marshaller to
	 * marshall/unmarshall POJO's (with JAXB annotations) to XML.
	 */
	@Bean
	public ViewResolver jaxb2MarshallingXmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(Pizza.class);

		return new Jaxb2MarshallingXmlViewResolver(marshaller);
	}

	/**
	 * Configure View resolver to provide JSON output using JACKSON library to
	 * convert object in JSON format.
	 */
	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	/**
	 * Configure View resolver to provide PDF output using lowagie pdf library to
	 * generate PDF output for an object content
	 */
	@Bean
	public ViewResolver pdfViewResolver() {
		return new PdfViewResolver();
	}

	/**
	 * Configure View resolver to provide XLS output using Apache POI library to
	 * generate XLS output for an object content.
	 */
	@Bean
	public ViewResolver excelViewResolver() {
		return new ExcelViewResolver();
	}

	/**
	 * Configure View resolver to provide HTML output This is the default format
	 * in absence of any type suffix.
	 */
	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");

		return viewResolver;
	}
}
