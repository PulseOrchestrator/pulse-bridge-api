package de.pulseorchestrator.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Bridge API exposed by the Paper plugin. This interface is the long-lived,
 * public surface other plugins use to interact with PulseOrchestrator.
 * The implementation is provided by {@code plugin-paper} at runtime through
 * Bukkit's {@code ServicesManager}.
 */
public interface OrchestratorBridgeApi {

    /** Version of this API interface. Matches the pulse-bridge-api artifact version. */
    String API_VERSION = "1.0.0-beta.3";

    // -------------------- System --------------------

    SystemStatusDto getSystemStatus();

    // -------------------- Service discovery --------------------

    List<ServiceDto> listServices();

    List<ServiceDto> listServices(ServiceStatus status);

    List<ServiceDto> listServicesForTask(String taskName);

    Optional<ServiceDto> getService(String serviceId);

    // -------------------- Tasks --------------------

    List<TaskDto> listTasks();

    Optional<TaskDto> getTask(String name);

    ActionResult<TaskDto> createTask(TaskDto task);

    ActionResult<TaskDto> updateTask(String name, TaskDto task);

    ActionResult<Void> deleteTask(String name);

    // -------------------- Service lifecycle --------------------

    ActionResult<ServiceDto> createService(String taskName);

    ActionResult<ServiceDto> createService(String taskName, String name);

    default CompletableFuture<ActionResult<ServiceDto>> createServiceAsync(String taskName) {
        return CompletableFuture.supplyAsync(() -> createService(taskName));
    }

    default CompletableFuture<ActionResult<ServiceDto>> createServiceAsync(String taskName, String name) {
        return CompletableFuture.supplyAsync(() -> createService(taskName, name));
    }

    ActionResult<Void> deleteService(String serviceId);

    default CompletableFuture<ActionResult<Void>> deleteServiceAsync(String serviceId) {
        return CompletableFuture.supplyAsync(() -> deleteService(serviceId));
    }

    ActionResult<Void> startService(String serviceId);

    default CompletableFuture<ActionResult<Void>> startServiceAsync(String serviceId) {
        return CompletableFuture.supplyAsync(() -> startService(serviceId));
    }

    ActionResult<Void> stopService(String serviceId);

    default CompletableFuture<ActionResult<Void>> stopServiceAsync(String serviceId) {
        return CompletableFuture.supplyAsync(() -> stopService(serviceId));
    }

    ActionResult<Void> restartService(String serviceId);

    default CompletableFuture<ActionResult<Void>> restartServiceAsync(String serviceId) {
        return CompletableFuture.supplyAsync(() -> restartService(serviceId));
    }

    /**
     * Recreates the service (delete + create from the same task).
     *
     * @param serviceId  the service to recreate
     * @param startAfter whether to start the new instance immediately
     */
    ActionResult<ServiceDto> recreateService(String serviceId, boolean startAfter);

    default CompletableFuture<ActionResult<ServiceDto>> recreateServiceAsync(String serviceId, boolean startAfter) {
        return CompletableFuture.supplyAsync(() -> recreateService(serviceId, startAfter));
    }

    ActionResult<ServiceDto> setServiceMaintenanceMode(String serviceId, boolean enabled);

    default CompletableFuture<ActionResult<ServiceDto>> setServiceMaintenanceModeAsync(String serviceId, boolean enabled) {
        return CompletableFuture.supplyAsync(() -> setServiceMaintenanceMode(serviceId, enabled));
    }

    // -------------------- Console & commands --------------------

    ActionResult<Void> sendCommand(String serviceId, String command);

    List<String> getConsole(String serviceId, int lines);

    // -------------------- Logs --------------------

    void addLogListener(UUID subscriberId, LogLevel level, LogListener listener);

    void removeLogListener(UUID subscriberId);

    boolean isSubscribed(UUID subscriberId);

    // -------------------- Player transfer --------------------

    ActionResult<Void> connectPlayerToServer(UUID playerUuid, String serverId);

    // -------------------- Service events --------------------

    void addServiceEventListener(ServiceEventListener listener);

    void removeServiceEventListener(ServiceEventListener listener);

    // -------------------- Backups --------------------

    List<BackupDto> listBackups(String serviceId);

    ActionResult<BackupDto> createBackup(String serviceId);

    ActionResult<ServiceDto> rollbackBackup(String serviceId, int slot);

    ActionResult<Void> deleteBackup(String serviceId, int slot);

    // -------------------- Proxy --------------------

    ProxyStatusDto getProxyStatus();

    ProxyConfigDto getProxyConfig();

    ActionResult<ProxyConfigDto> setProxyMaintenanceMode(boolean enabled);
}
