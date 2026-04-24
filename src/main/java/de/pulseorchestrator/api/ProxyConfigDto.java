package de.pulseorchestrator.api;

import java.util.List;

/**
 * Read-only view of the proxy routing configuration.
 */
public record ProxyConfigDto(
        String routingPolicy,
        List<String> fallbackTasks,
        String defaultEntryTask
) {}
