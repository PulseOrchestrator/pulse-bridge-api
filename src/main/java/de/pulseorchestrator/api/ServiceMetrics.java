package de.pulseorchestrator.api;

import java.time.Instant;

/**
 * Live metrics for a running service instance.
 * Process-level fields (ram, cpu) are collected by the orchestrator directly.
 * In-game fields (players, tps, mspt, chunks, entities) are reported by the
 * managed server via the orchestrator metrics endpoint.
 * All fields are nullable — null indicates the data has not been reported yet.
 * Check {@link #updatedAt} to detect stale metrics.
 */
public record ServiceMetrics(
        Long ramUsageMb,
        Double cpuPercent,
        Integer onlinePlayers,
        Integer maxPlayers,
        Double tps,
        Double mspt,
        Integer loadedChunks,
        Integer loadedEntities,
        Instant updatedAt
) {}
