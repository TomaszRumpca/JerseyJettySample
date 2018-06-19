package pl.tr;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.glassfish.jersey.jetty.JettyHttpContainer;
import org.glassfish.jersey.server.ContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App {

    public static void main(String[] args) {

        ResourceConfig resourceConfig = new ResourceConfig().register(Foo.class);

        final JettyHttpContainer jettyHttpContainerHandler =
                ContainerFactory.createContainer(JettyHttpContainer.class, resourceConfig);

        String contextPath = "/api";

        ContextHandler cHandler = new ContextHandler(contextPath);
        cHandler.setHandler(jettyHttpContainerHandler);

        int port = 7478;
        Server server = new Server(port);
        try (final ServerConnector httpServerConnector = new ServerConnector(server)) {
            httpServerConnector.setHost("localhost");
        }
        server.setHandler(cHandler);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            // ops!
            System.out.println("ops: " + e.getMessage());
        }
    }

}
