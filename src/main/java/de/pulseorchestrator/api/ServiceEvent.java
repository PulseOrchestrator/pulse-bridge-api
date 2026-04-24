package de.pulseorchestrator.api;

import java.time.Instant;

/**
 * Represents a service lifecycle event.
 */
public record ServiceEvent(String serviceId, ServiceStatus oldStatus, ServiceStatus newStatus, Instant timestamp) {}
