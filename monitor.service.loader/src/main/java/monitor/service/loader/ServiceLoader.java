package monitor.service.loader;


import java.net.*;
import java.nio.file.*;


final class ServiceLoader extends URLClassLoader {

    private ServiceLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    static ServiceLoader of(final URL[] serviceURLs, final ClassLoader parent) {
        return new ServiceLoader(serviceURLs, parent);
    }

}
