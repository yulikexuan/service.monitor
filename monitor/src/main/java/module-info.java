import monitor.observer.ServiceObserverFactory;

module monitor {
    requires monitor.core;
    requires monitor.service.loader;
    uses ServiceObserverFactory;
}
