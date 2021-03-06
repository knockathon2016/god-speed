package ginie.server;

import ginie.service.LifeCycleListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by dhruvr
 */
public class ServerServiceListener implements LifeCycleListener {


    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceListener.class);

    @Override
    public void beforeStart() {

    }

    @Override
    public void afterStart() {
        LOGGER.info("\t\t Server service started");
    }

    @Override
    public void beforeStop() {

    }

    @Override
    public void afterStop() {
        LOGGER.info("\t\t Server service stopped gracefully ");
    }

    @Override
    public void beforeClose() {

    }

    @Override
    public void afterClose() {
        LOGGER.info("\t\t Server service closed");
    }
}
