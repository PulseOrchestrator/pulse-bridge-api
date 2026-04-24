package de.pulseorchestrator.api;

import java.util.List;
import java.util.Map;

/**
 * Immutable DTO representing a task blueprint.
 */
public record TaskDto(
        String name,
        String description,
        ServerSoftware serverSoftware,
        String serverVersion,
        int maxMemoryMB,
        int minMemoryMB,
        List<String> tags,
        List<String> templates,
        Map<String, String> environment
) {}
