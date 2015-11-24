package examples.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.xml.XmlConfiguration;

public class ServerDemo extends AbstractHandler {

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		baseRequest.setHandled(true);

		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		response.getWriter().println("<h4>Not found</h1>");
	}

	private Server xmlBasedServer() throws Exception {

		Resource serverXml = Resource.newSystemResource("server.xml");
		XmlConfiguration configuration = new XmlConfiguration(serverXml.getInputStream());

		return (Server) configuration.configure();
	}

	private Server selfConfiguredServer() throws Exception {

		ResourceHandler resourceHandler = new ResourceHandler();

		resourceHandler.setDirectoriesListed(false);
		resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
		resourceHandler.setResourceBase(".");


		HandlerList handlers = new HandlerList();
		handlers.setHandlers(new Handler[] { resourceHandler, new ServerDemo() });


		Server server = new Server(8080);

		server.setHandler(handlers);

		return server;
	}


	public static void main(String[] args) throws Exception {

		ServerDemo demo = new ServerDemo();

		Server server = demo.xmlBasedServer();
//		Server server = demo.selfConfiguredServer();

		server.start();
		server.join();
	}
}
