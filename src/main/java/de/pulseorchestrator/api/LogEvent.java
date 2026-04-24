package de.pulseorchestrator.api;

import java.time.Instant;

/**
 * Log event payload delivered to subscribers.
 */
public record LogEvent(String serviceId, LogLevel level, String category, String message, Instant timestamp) {}
