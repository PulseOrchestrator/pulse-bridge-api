package de.pulseorchestrator.api;

/**
 * A service of the network: one instance of a group, e.g. {@code lobby-1}.
 *
 * @param id         service id
 * @param group      group the service belongs to
 * @param status     what the process is doing
 * @param running    whether the service should run (its desired state)
 * @param state      state its plugin reports, e.g. {@code LOBBY}, or {@code null}
 * @param players    players online, {@code 0} without a Pulse plugin on the server
 * @param maxPlayers player limit, {@code 0} without a Pulse plugin on the server
 */
public record Service(
        String id,
        String group,
        ServiceStatus status,
        boolean running,
        String state,
        int players,
        int maxPlayers) {

    /** Ready for players. */
    public boolean isReady() {
        return status == ServiceStatus.RUNNING;
    }

    /** Ready and below its player limit. */
    public boolean hasRoom() {
        return isReady() && (maxPlayers == 0 || players < maxPlayers);
    }
}
