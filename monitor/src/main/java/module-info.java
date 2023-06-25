import monitor.observer.ServiceObserverFactory;

module monitor {
    requires monitor.service.loader;
    requires monitor.observer;
    uses ServiceObserverFactory;
}
