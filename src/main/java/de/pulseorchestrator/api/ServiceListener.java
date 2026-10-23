package de.pulseorchestrator.api;

/**
 * Receives service changes. Called on a background thread.
 */
@FunctionalInterface
public interface ServiceListener {

    /** A service changed its status, desired state or reported state. */
    void onServiceChanged(Service service);
}
