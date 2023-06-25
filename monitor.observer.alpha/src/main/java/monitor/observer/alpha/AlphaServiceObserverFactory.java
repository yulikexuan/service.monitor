package monitor.observer.alpha;


import monitor.observer.*;

import java.util.Optional;


@Alpha
public final class AlphaServiceObserverFactory implements ServiceObserverFactory {

	@Override
	public String factoryName() {
		return "alpha";
	}

	@Override
	public Optional<ServiceObserver> create(String service) {
		return AlphaServiceObserver.of(service);
	}

}
