package monitor.service.loader;


import java.net.*;
import java.util.*;
import java.nio.file.*;


sealed interface ServiceLocator permits ServiceLocatorImpl {

    String SERVICE_FILE_PATTERN = "*.jar";

    URL[] locate();

    static ServiceLocator of(final String path) {
        return ServiceLocatorImpl.of(path);
    }
}

final class ServiceLocatorImpl implements ServiceLocator {

    private final String path;

    private ServiceLocatorImpl(String path) {
        this.path = path;
    }

    static ServiceLocatorImpl of(final String path) {
        return new ServiceLocatorImpl(path);
    }

    @Override
    public URL[] locate() {

        Path servicePath = Paths.get(path);

        System.out.println("%n>>> Service Provider Path Exists? %b%n"
                .formatted(Files.exists(servicePath)));

        Collection<URL> urlList = new ArrayList<>();
        try (DirectoryStream<Path> jarPaths = Files.newDirectoryStream(
                servicePath, SERVICE_FILE_PATTERN)) {
            jarPaths.forEach(jarPath -> urlList.add(toURL(jarPath)));
        } catch (Exception e) {
            System.out.println(">>> Failed to locate services!");
        }

        return urlList.toArray(new URL[urlList.size()]);
    }

    URL toURL(final Path jar) {
        try {
            return jar.toUri().toURL();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
