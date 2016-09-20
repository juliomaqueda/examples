package examples.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
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
 * Add an initializer class implementing WebApplicationInitializer as shown below(which in this case acts as
 * replacement of any spring configuration defined in web.xml). During Servlet 3.0 Container startup, this class
 * will be loaded and instantiated and its onStartup method will be called by servlet container.
 */
