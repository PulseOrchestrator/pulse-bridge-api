package de.pulseorchestrator.api;

/**
 * Snapshot of the orchestrator's overall system status.
 */
public record SystemStatusDto(
        int totalServices,
        int runningServices,
        int stoppedServices,
        int failedServices,
        int totalTasks,
        long usedMemoryMb,
        long maxMemoryMb,
        ProxyStatusDto proxy
) {}
