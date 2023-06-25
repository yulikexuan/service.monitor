package monitor.service.loader;


import java.net.*;
import java.nio.file.*;


public sealed interface ServiceLoaderConfig {

    void config();

    static void config(final String path, final ClassLoader parent) {
        new ServiceLoaderConfigImpl(ServiceLocator.of(path), parent).config();
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
