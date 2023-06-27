import monitor.observer.ServiceObserverFactory;
import monitor.observer.alpha.AlphaServiceObserverFactory;

module monitor.observer.alpha {
    requires monitor.core;
    provides ServiceObserverFactory with AlphaServiceObserverFactory;
}
