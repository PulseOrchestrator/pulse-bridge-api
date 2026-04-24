package de.pulseorchestrator.api;

/**
 * Current operational status of the proxy managed by the orchestrator.
 */
public record ProxyStatusDto(
        boolean running,
        Long pid,
        String state,
        int bindPort
) {}
