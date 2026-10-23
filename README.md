# pulse-bridge-api

Public API for plugins that work with [Pulse](https://github.com/PulseOrchestrator/PulseOrchestrator).

## Java: `PulseApi`

Server plugins on Paper, Purpur and Folia get `PulseApi` from Bukkit's `ServicesManager` once the Pulse plugin (`pulse-paper`, installed by Pulse) is loaded:

```java
PulseApi pulse = Bukkit.getServicesManager().load(PulseApi.class);
pulse.setState("INGAME");
pulse.services().thenAccept(services -> services.stream()
        .filter(service -> service.group().equals("bedwars") && "LOBBY".equals(service.state()))
        .filter(Service::hasRoom)
        .findFirst()
        .ifPresent(target -> pulse.sendPlayer(player.getUniqueId(), target.id())));
```

Add it as `compileOnly`, the Pulse plugin provides it at runtime:

```kotlin
repositories { maven("https://jitpack.io") }
dependencies { compileOnly("com.github.PulseOrchestrator:pulse-bridge-api:<version>") }
```

Declare `depend: [PulseOrchestrator]` (or `softdepend`) in your `plugin.yml`.

## HTTP: daemon API

`openapi/pulse-daemon.json` describes the local HTTP and WebSocket API of the Pulse daemon. Use it to generate a client in any language. The daemon publishes the current description at `GET /api/v1/openapi.json`.

## Versions

The API is versioned as major.minor only (`2.0`, `2.1`, ...): every change adds to or changes the API, so there are no patch releases. `PulseApi.API_VERSION` holds the version.

| Branch | API |
|---|---|
| `main` | Pulse 2 (`PulseApi`) |
| `release/1.x` | Pulse 1.x (`OrchestratorBridgeApi`) |
