package ginie.rest;

import com.google.inject.AbstractModule;
import org.eclipse.jetty.servlet.DefaultServlet;

/**
 * Created by dhruvr
 */
public class RestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(DefaultServlet.class).asEagerSingleton();
        bind(EndPoints.class).asEagerSingleton();
        bind(CORSFilter.class).asEagerSingleton();
        /*bind(SecurityFilter.class).asEagerSingleton();*/
    }
}
