package monitor.observer;


public interface ServiceObserver {
	DiagnosticDataPoint dataFromService();
	String serviceName();
}
