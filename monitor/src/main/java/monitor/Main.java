package monitor;


import java.time.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ServiceLoader.Provider;
import java.net.*;
import java.nio.file.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.module.*;

import monitor.observer.*;
import monitor.annotation.*;
import monitor.service.loader.ServiceLoaderConfig;


import static java.util.stream.Collectors.toList;


public class Main {

    static final Map<String, Class<? extends Annotation>> SERVICE_ANNOTATIONSS =
            Map.of("alpha", Alpha.class, "beta", Beta.class, "zero", Zero.class);

    static final String SERVICE_PATH = "services";

    public static void main(String[] args) throws Exception {

        // Start to Test Class Reflection
        final var typeName = "monitor.observer.DiagnosticDataPoint";
        boolean cons = constructable(Class.forName(typeName));
        System.out.println(">>> Able to condtruct %s? %b".formatted(typeName, cons));
        // End of Testing Class Reflection

        List<Module> modules = List.of(
                Main.class.getModule(), ServiceLoaderConfig.class.getModule());

        describeModules(modules);
        describeModuleLayers(modules);

        System.out.println(">>> Describe Requires of Module monitor ... \n");
        describeRequires(Main.class.getModule());

        final String serviceName =  (args.length > 0) ? args[0] : "all";

        // ServiceLoaderConfig.config(SERVICE_PATH, ClassLoader.getSystemClassLoader());
        // ServiceLoaderConfig.configServiceLayer();
        System.out.println(">>> Adding Service Layer ... \n");
        ModuleLayer serviceLayer = ServiceLoaderConfig.createLayer(SERVICE_PATH);

        System.out.println(">>> Loading Service Factories ... \n");
        List<ServiceObserverFactory> factories =
                ServiceLoader.load(serviceLayer, ServiceObserverFactory.class)
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

    static boolean constructable(Class<?> type) throws Exception {
        Constructor<?> constructor = type.getConstructor(
                String.class, ZonedDateTime.class, Boolean.TYPE);
        boolean accessable = constructor.trySetAccessible();
        System.out.println(">>> Is type %s accessible? %b "
                .formatted(type.getSimpleName(), accessable));
        return accessable;
    }

    static void describeModules(final List<Module> modules) {

        modules.stream()
                .map(Module::getDescriptor)
                .forEach(Main::printModuleDescriptor);

        System.out.println();
    }

    static void describeRequires(final Module module) {

        module.getDescriptor().requires().stream()
                .map(r -> "    -> %s @ %s".formatted(
                        r.name(),
                        r.rawCompiledVersion().orElse("unknown version")))
                .forEach(System.out::println);

        System.out.println();
    }

    static void describeModuleLayers(final List<Module> modules) {
        long layerNum = modules.stream()
                .map(Module::getLayer)
                .distinct()
                .count();
        System.out.println("%n>>> How many module layers? %d%n".formatted(layerNum));
    }

    static void printModuleDescriptor(final ModuleDescriptor desc) {
        String version = desc.rawVersion().orElse("unknown version");
        System.out.println("%n%s module %s @ %s".formatted(
                desc.modifiers(), desc.name(), version));
        System.out.println("\t requires: %s".formatted(desc.requires()));
        System.out.println("\t exports: %s".formatted(desc.exports()));
        System.out.println("\t opens: %s".formatted(desc.opens()));
        System.out.println("\t contains packages: %s".formatted(desc.packages()));
        System.out.println("\t main class: %s".formatted(desc.mainClass()));
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
