package monitor;


import java.util.*;
import java.util.ServiceLoader.Provider;
import java.net.*;
import java.nio.file.*;
import java.lang.annotation.Annotation;

import monitor.observer.*;
import monitor.annotation.*;
import monitor.service.loader.ServiceLoaderConfig;


import static java.util.stream.Collectors.toList;


public class Main {

    static final Map<String, Class<? extends Annotation>> SERVICE_ANNOTATIONSS =
            Map.of("alpha", Alpha.class, "beta", Beta.class, "zero", Zero.class);

    static final String SERVICE_PATH = "services";

    public static void main(String[] args) throws Exception {

        final String serviceName =  (args.length > 0) ? args[0] : "all";

        ServiceLoaderConfig.config(SERVICE_PATH, ClassLoader.getSystemClassLoader());

        List<ServiceObserverFactory> factories =
                ServiceLoader.load(ServiceObserverFactory.class)
                        .stream()
                        .filter(provider -> filterService(serviceName, provider))
                        .map(Provider::get)
                        .toList();

        new DataCollector().collect(factories).forEach(System.out::println);
    }

    static boolean filterService(
            final String serviceName,
            final Provider<ServiceObserverFactory> provider) {

        return serviceName.equals("all") ? true :
                provider.type().isAnnotationPresent(
                        SERVICE_ANNOTATIONSS.get(serviceName));
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
