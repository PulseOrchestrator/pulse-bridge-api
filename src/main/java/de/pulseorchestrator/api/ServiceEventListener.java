package de.pulseorchestrator.api;

/**
 * Listener interface for service lifecycle events.
 */
public interface ServiceEventListener {
    void onServiceEvent(ServiceEvent event);
}
