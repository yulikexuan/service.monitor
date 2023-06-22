package monitor.observer;


import java.util.Optional;


public interface ServiceObserverFactory {
	String factoryName();
	Optional<ServiceObserver> create(String service);
}
