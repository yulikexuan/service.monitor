package monitor.observer.gamma;


import monitor.observer.*;
import monitor.observer.alpha.*;

import java.util.Optional;
import java.time.ZonedDateTime;
import java.util.concurrent.ThreadLocalRandom;


final class GammaServiceObserver extends AlphaServiceObserver {

    GammaServiceObserver(String serviceName) {
        super(serviceName);
    }
}
