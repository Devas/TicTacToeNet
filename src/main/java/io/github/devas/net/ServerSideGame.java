package io.github.devas.net;

import java.util.ArrayList;
import java.util.List;

/**
 * Every game has own id, settings and clients.
 */
class ServerSideGame implements Game {

    private int gameId;
    private Settings settings;
    private List<ClientInfo> clients;

    ServerSideGame(int gameId) {
        this.gameId = gameId;
        this.settings = new GameSettings(2);
        this.clients = new ArrayList<>(settings.getMaxNumberOfPlayers());
    }

    int getGameId() {
        return gameId;
    }

    void addClient(ClientInfo client) {
        clients.add(client);
    }

    void removeClient(ClientInfo client) {
        clients.remove(client);
    }

    void removeAllClients() {
        clients.clear();
    }

    boolean canClientJoinGame() {
        return clients.size() < settings.getMaxNumberOfPlayers();
    }

}
