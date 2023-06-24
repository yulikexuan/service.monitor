package monitor;


import monitor.observer.*;

import java.util.*;
import java.util.ServiceLoader.Provider;
import java.net.*;
import java.nio.file.*;


import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) throws Exception {

        Path serviceProviderPath = Paths.get("services");

        System.out.println(">>> Service Provider Path Exist? %b"
                .formatted(Files.exists(serviceProviderPath)));

        Collection<URL> urlList = new ArrayList<>();
        try (DirectoryStream<Path> jars = Files.newDirectoryStream(
                serviceProviderPath, "*.jar")) {

            for (Path jar : jars) {
                urlList.add(jar.toUri().toURL());
                System.out.println(">>> Adding jar %s".formatted(
                        jar.toUri().toURL().toString()));
            }
        }
        URL[] urls = urlList.toArray(new URL[urlList.size()]);

        // URL serviceProviderURL = serviceProviderPath.toUri().toURL();
        // URL[] urls = { serviceProviderURL };
        // System.out.println(serviceProviderURL);

        ClassLoader parentClassLoader = ClassLoader.getSystemClassLoader();
        ServiceClassLoader serviceClassLoader = new ServiceClassLoader(
                urls, parentClassLoader);

        Thread.currentThread().setContextClassLoader(serviceClassLoader);

        List<ServiceObserverFactory> factories =
                ServiceLoader.load(ServiceObserverFactory.class)
                        .stream()
                        .map(Provider::get)
                        .toList();

        new DataCollector().collect(factories).forEach(System.out::println);
    }

}

class DataCollector {

    static final List<String> SITE_NAMES = List.of(
        "0-patient",
        "alpha-1",
        "alpha-2",
        "beta-1",
        "alpha-3",
        "beta-2");

    List<DiagnosticDataPoint> collect(
            final List<ServiceObserverFactory> observerFactories) {

        return SITE_NAMES.stream()
            .map(n -> this.createObserver(observerFactories, n))
            .flatMap(Optional::stream)
            .map(ServiceObserver::dataFromService)
            .toList();
    }

    private Optional<ServiceObserver> createObserver(
			final List<ServiceObserverFactory> observerFactories,
            final String serviceName) {

		return observerFactories.stream()
				.flatMap(factory -> factory.create(serviceName).stream())
				.findFirst();
	}

}
