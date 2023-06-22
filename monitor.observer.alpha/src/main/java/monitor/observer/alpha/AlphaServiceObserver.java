package monitor.observer.alpha;


import monitor.observer.*;

import java.util.Optional;
import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;


public class AlphaServiceObserver implements ServiceObserver {

    private static final ThreadLocalRandom RANDOM = ThreadLocalRandom.current();

    private final String serviceName;

    AlphaServiceObserver(String serviceName) {
		this.serviceName = serviceName;
	}

    @Override
    public String serviceName() {
        return this.serviceName;
    }

    @Override
	public DiagnosticDataPoint dataFromService() {
		// this check should actually contact the serviceName
		boolean alive = RANDOM.nextInt(0, 100) >25;
		return new DiagnosticDataPoint(serviceName, ZonedDateTime.now(), alive);
	}

    static Optional<ServiceObserver> of(String service) {
		return Optional.of(service)
				// this check should do something more sensible
				.filter(s -> s.contains("alpha"))
				.map(AlphaServiceObserver::new);
	}

}
