package de.pulseorchestrator.api;

/**
 * A request to the Pulse daemon failed.
 */
public class PulseApiException extends RuntimeException {

    private final String kind;

    /**
     * @param kind machine-readable reason from the daemon, e.g. {@code not_found},
     *             {@code not_running} or {@code unavailable} when the daemon
     *             could not be reached
     */
    public PulseApiException(String kind, String message, Throwable cause) {
        super(message, cause);
        this.kind = kind;
    }

    public PulseApiException(String kind, String message) {
        this(kind, message, null);
    }

    public String kind() {
        return kind;
    }
}
