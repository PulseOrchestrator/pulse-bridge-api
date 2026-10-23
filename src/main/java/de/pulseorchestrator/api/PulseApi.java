package de.pulseorchestrator.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Access to the Pulse network from a server plugin.
 *
 * <p>The Pulse plugin on the server ({@code pulse-paper}) provides the
 * implementation. On Paper, Purpur and Folia get it from Bukkit's
 * {@code ServicesManager}:
 *
 * <pre>{@code
 * PulseApi pulse = Bukkit.getServicesManager().load(PulseApi.class);
 * pulse.setState("INGAME");
 * pulse.services().thenAccept(services -> ...);
 * }</pre>
 *
 * <p>Every call that talks to the Pulse daemon returns a
 * {@link CompletableFuture} and never blocks. Futures complete on a background
 * thread; switch to the server thread before touching the world or players.
 * Failed calls complete exceptionally with a {@link PulseApiException}.
 */
public interface PulseApi {

    /** Version of this interface. */
    String API_VERSION = "2.0.0";

    /** Id of the service this server runs as, e.g. {@code bedwars-2}. */
    String serviceId();

    /** All services of the network. */
    CompletableFuture<List<Service>> services();

    /** One service, or empty when no such service exists. */
    CompletableFuture<Optional<Service>> service(String id);

    /**
     * Starts a service and keeps it running. The future completes once Pulse
     * accepted the request, usually before the server is ready; listen for
     * {@link ServiceStatus#RUNNING} to know when players can join.
     */
    CompletableFuture<Service> startService(String id);

    /** Stops a service and keeps it stopped. */
    CompletableFuture<Service> stopService(String id);

    /**
     * Moves an online player to another server through the proxy. The target
     * must be running and reachable through the proxy.
     */
    CompletableFuture<Void> sendPlayer(UUID player, String serviceId);

    /** The state this server reports, or {@code null}. */
    String state();

    /**
     * Sets the state this server reports, e.g. {@code LOBBY} or {@code INGAME},
     * for matchmaking in other plugins. {@code null} clears it. It reaches the
     * daemon with the next report, within a few seconds.
     */
    void setState(String state);

    /** Called when any service changes its status or state. */
    void addListener(ServiceListener listener);

    void removeListener(ServiceListener listener);
}
