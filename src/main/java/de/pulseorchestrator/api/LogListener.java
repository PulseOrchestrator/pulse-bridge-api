package de.pulseorchestrator.api;

/**
 * Listener for log events.
 */
public interface LogListener {
    void onLog(LogEvent event);
}
