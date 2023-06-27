package monitor.observer.gamma;


import monitor.observer.*;
import monitor.observer.alpha.*;

import java.util.Optional;


@Alpha
public final class GammaServiceObserverFactory extends AlphaServiceObserverFactory {

	@Override
	public String factoryName() {
		return "gamma";
	}

	@Override
	public Optional<ServiceObserver> create(String service) {
		return GammaServiceObserver.of(service);
	}

}
