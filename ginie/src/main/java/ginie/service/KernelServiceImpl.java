package ginie.service;

import com.google.inject.Injector;
import ginie.GinieException;
import ginie.boot.CommandOptions;
import ginie.boot.ControlServletModule;
import ginie.httpclient.HttpClientModule;
import ginie.mongo.MongoModule;
import ginie.rest.RestModule;
import ginie.scheduler.SchedulerModule;
import ginie.scheduler.SchedulerService;
import ginie.server.ServerModule;
import ginie.server.ServerService;
import ginie.settings.GinieSettings;

/**
 * Created by dhruvr
 */
public class KernelServiceImpl extends AbstractLifeCycleService<KernelService> implements KernelService {

    private Injector injector;

    public KernelServiceImpl(GinieSettings ginieSettings, CommandOptions commandOptions) {
        super(ginieSettings, commandOptions);
        initialize();
    }

    @Override
    protected void dostart() throws GinieException {
        try {

            if (lifeCycle.canMoveToStarted()) {
                injector.getInstance(ServerService.class).start();
                injector.getInstance(SchedulerService.class).start();
            }

            lifeCycle.moveToStarted();

        } catch (Exception e) {
            throw new GinieException("Problem in Kernel Service ", e);
        }
    }

    @Override
    protected void dostop() throws GinieException {
        injector.getInstance(SchedulerService.class).stop();
        injector.getInstance(ServerService.class).stop();
    }

    @Override
    protected void doClose() throws GinieException {
        injector.getInstance(SchedulerService.class).close();
        injector.getInstance(ServerService.class).close();
    }

    @Override
    public void initialize() {

        ServiceBuilder serviceBuilder = new ServiceBuilder();
        serviceBuilder.addModule(binder -> {
            binder.bind(GinieSettings.class).toInstance(ginieSettings);
            binder.bind(CommandOptions.class).toInstance(commandOptions);
        });
        serviceBuilder.addModule(new ServerModule());
        serviceBuilder.addModule(new ControlServletModule());
        serviceBuilder.addModule(new RestModule());
        serviceBuilder.addModule(new ServiceModule());
        serviceBuilder.addModule(new MongoModule());
        serviceBuilder.addModule(new HttpClientModule());
        serviceBuilder.addModule(new SchedulerModule());

        injector = serviceBuilder.createInjector();
    }
}
