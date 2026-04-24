package de.pulseorchestrator.api;

/**
 * Public service status enum mirroring the orchestrator's internal states.
 */
public enum ServiceStatus {
    CREATED,
    STARTING,
    RUNNING,
    STOPPING,
    STOPPED,
    FAILED
}
