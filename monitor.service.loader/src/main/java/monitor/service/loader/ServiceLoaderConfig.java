package monitor.service.loader;


import java.util.*;
import java.net.*;
import java.nio.file.*;
import java.lang.module.*;


public sealed interface ServiceLoaderConfig {

    void config();

    static void config(final String path, final ClassLoader parent) {
        new ServiceLoaderConfigImpl(ServiceLocator.of(path), parent).config();
    }

    static Configuration configServiceLayer(final String path) {

        System.out.println(">>> Configuraing service layer ...\n");

        ModuleFinder emptyBefore = ModuleFinder.of();
        ModuleFinder modulePath = ModuleFinder.of(Paths.get(path));
        Configuration bootGraph = ModuleLayer.boot().configuration();

        return bootGraph.resolveAndBind(emptyBefore, modulePath, List.of());
    }

    static ModuleLayer createLayer(final String path) {

        ModuleLayer thisLayer = ServiceLoaderConfig.class.getModule().getLayer();
        Configuration layerConfig = configServiceLayer(path);
        ClassLoader clazzLoader = ServiceLoaderConfig.class.getClassLoader();

        return thisLayer.defineModulesWithOneLoader(layerConfig, clazzLoader);
    }
}

final class ServiceLoaderConfigImpl implements ServiceLoaderConfig {

    private final ServiceLocator serviceLocator;
    private final ClassLoader parent;

    ServiceLoaderConfigImpl(
            ServiceLocator serviceLocator, ClassLoader parent) {

        this.serviceLocator = serviceLocator;
        this.parent = parent;
    }

    @Override
    public void config() {
        URL[] serviceURLs = this.serviceLocator.locate();
        var serviceLoader = ServiceLoader.of(serviceURLs, parent);
        Thread.currentThread().setContextClassLoader(serviceLoader);
    }

}
