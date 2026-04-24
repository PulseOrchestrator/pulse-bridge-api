package de.pulseorchestrator.api;

import java.time.Instant;

/**
 * Information about a single service backup slot.
 */
public record BackupDto(
        int slot,
        Instant createdAt,
        long sizeBytes,
        String taskName,
        int port
) {}
