import monitor.observer.ServiceObserverFactory;
import monitor.observer.alpha.AlphaServiceObserverFactory;

module monitor.observer.alpha {
    requires monitor.observer;
    provides ServiceObserverFactory with AlphaServiceObserverFactory;
}
