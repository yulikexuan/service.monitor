import monitor.observer.ServiceObserverFactory;
import monitor.observer.beta.BetaServiceObserverFactory;

module monitor.observer.beta {
    requires monitor.observer;
    provides ServiceObserverFactory with BetaServiceObserverFactory;
}
