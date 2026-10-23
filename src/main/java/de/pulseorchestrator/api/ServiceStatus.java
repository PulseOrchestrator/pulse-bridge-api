package de.pulseorchestrator.api;

/** What a service's process is doing. */
public enum ServiceStatus {
    STOPPED,
    /** Copying templates and downloading the server. */
    PREPARING,
    /** The process runs and the server is loading. */
    STARTING,
    /** Ready for players. */
    RUNNING,
    STOPPING,
    /** Crashed or failed to start; Pulse retries after a delay. */
    BACKOFF
}
