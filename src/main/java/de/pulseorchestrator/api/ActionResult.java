package de.pulseorchestrator.api;

import java.util.Optional;

/**
 * Simple typed result for actions invoked through the bridge.
 */
public class ActionResult<T> {
    private final boolean success;
    private final T data;
    private final String message;

    public ActionResult(boolean success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public boolean success() {
        return success;
    }

    public Optional<T> data() {
        return Optional.ofNullable(data);
    }

    public String message() {
        return message;
    }

    public static <T> ActionResult<T> success(T data) {
        return new ActionResult<>(true, data, null);
    }

    public static <T> ActionResult<T> failure(String message) {
        return new ActionResult<>(false, null, message);
    }
}
