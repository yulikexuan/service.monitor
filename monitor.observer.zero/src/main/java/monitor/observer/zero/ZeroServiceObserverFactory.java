package monitor.observer.zero;


import monitor.observer.*;

import java.util.Optional;


public final class ZeroServiceObserverFactory implements ServiceObserverFactory {

	@Override
	public String factoryName() {
		return "zero";
	}

	@Override
	public Optional<ServiceObserver> create(String service) {
		return ZeroServiceObserver.of(service);
	}

}
