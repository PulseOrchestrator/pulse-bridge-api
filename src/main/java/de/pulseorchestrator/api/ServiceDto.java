package de.pulseorchestrator.api;

import java.time.Instant;

/**
 * Immutable DTO representing a service instance.
 * {@link #metrics} is null when the service is not running or has not yet
 * reported metrics to the orchestrator.
 */
public record ServiceDto(
        String id,
        String taskName,
        int port,
        boolean persistent,
        boolean maintenanceMode,
        ServiceStatus status,
        Long pid,
        Integer exitCode,
        String failReason,
        Instant createdAt,
        Instant lastStartedAt,
        Instant lastStoppedAt,
        ServiceMetrics metrics
) {
    public boolean isRunning() {
        return status == ServiceStatus.RUNNING;
    }
}
