package monitor.observer;


import lombok.NonNull;
import java.util.Optional;


public interface ServiceObserverFactory {
	String factoryName();
	Optional<ServiceObserver> create(@NonNull String service);
}
