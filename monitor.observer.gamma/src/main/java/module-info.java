import monitor.observer.ServiceObserverFactory;
import monitor.observer.gamma.GammaServiceObserverFactory;

module monitor.observer.gamma {
    requires monitor.observer.alpha;
    provides ServiceObserverFactory with GammaServiceObserverFactory;
}
