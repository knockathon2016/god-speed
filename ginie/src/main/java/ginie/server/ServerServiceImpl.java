package ginie.server;

import com.google.inject.Inject;
import ginie.GinieException;
import ginie.boot.CommandOptions;
import ginie.service.AbstractLifeCycleService;
import ginie.settings.GinieSettings;
import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dhruvr
 */
public class ServerServiceImpl extends AbstractLifeCycleService<ServerService> implements ServerService {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceImpl.class);

    private final Server server;
    private final ServerServiceListener listener;

    @Inject
    public ServerServiceImpl(GinieSettings ginieSettings,
                             CommandOptions commandOptions,
                             Server server,
                             ServerServiceListener listener) {
        super(ginieSettings, commandOptions);
        this.server = server;
        this.listener = listener;
    }

    @Override
    protected void dostart() throws GinieException {
        startServer();
        addLifeCycleListener(listener);
        lifeCycle.started();
    }

    @Override
    protected void dostop() throws GinieException {
        lifeCycle.stopped();
    }

    @Override
    protected void doClose() throws GinieException {
        if (!lifeCycle.canMoveToClosed()) {
            stopServer();
        }
        lifeCycle.closed();
    }


    @Override
    public void startServer() {
        try {
            server.start();
        } catch (Exception e) {
            throw new GinieException("Problem in starting server ", e);
        }
    }

    @Override
    public void stopServer() {
        try {
            server.stop();
        } catch (Exception e) {
            throw new GinieException("Problem while closing server ", e);
        }
    }

    @Override
    public void serverGracefulStop() {
        server.setGracefulShutdown(10);
    }
}
