package monitor.observer.zero;


import monitor.observer.*;
import monitor.annotation.Zero;

import java.util.Optional;


@Zero
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
