package monitor;


import monitor.observer.*;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) {

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
