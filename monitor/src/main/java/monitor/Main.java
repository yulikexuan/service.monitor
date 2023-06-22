package monitor;


import monitor.observer.*;

import java.util.List;
import java.util.Optional;
import java.util.ServiceLoader;
import java.util.ServiceLoader.Provider;

import static java.util.stream.Collectors.toList;


public class Main {

    public static void main(String[] args) {

        List<String> factoryNames =
                ServiceLoader.load(ServiceObserverFactory.class)
                        .stream()
                        .map(Provider::get)
                        .map(ServiceObserverFactory::factoryName)
                        .collect(toList());

        System.out.println(">>> ServiceObserverFactory Names: %s"
                .formatted(factoryNames));
    }

}
