import monitor.observer.ServiceObserverFactory;
import monitor.observer.alpha.AlphaServiceObserverFactory;

module monitor.observer.alpha {
    requires transitive monitor.observer;
    exports monitor.observer.alpha;
    provides ServiceObserverFactory with AlphaServiceObserverFactory;
}
