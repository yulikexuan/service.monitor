package monitor.observer;


import java.time.ZonedDateTime;


public record DiagnosticDataPoint(
        String service, ZonedDateTime timestamp, boolean alive) {
}
