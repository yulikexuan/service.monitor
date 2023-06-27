package monitor.observer.beta;


import monitor.observer.*;
import monitor.annotation.Beta;

import java.util.Optional;


@Beta
public final class BetaServiceObserverFactory implements ServiceObserverFactory {

	@Override
	public String factoryName() {
		return "beta";
	}

	@Override
	public Optional<ServiceObserver> create(String service) {
		return BetaServiceObserver.of(service);
	}

}
