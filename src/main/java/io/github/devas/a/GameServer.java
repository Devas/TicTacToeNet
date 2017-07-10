package io.github.devas.a;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Optional;

import static io.github.devas.a.TimeStamp.getTimeStamp;

class GameServer implements Server {

    private int port = 6789;
    private int connections;
    private HashMap<Integer, ServerSideGame> games = new HashMap<>(); // maps games with unique id
    private boolean finished = false;

    private GameServer() {
//        TimeStamp.disableTimeStamp();
    }

    public static void main(String[] args) {
        GameServer gameServer = new GameServer();
        gameServer.startServer();
    }

    private void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println(getTimeStamp() + "Server initialized and running");
            do {
                System.out.println("\n" + getTimeStamp() + "Connections alive: " + connections + " | Listening for a connection ...");
                Socket socket = serverSocket.accept();
                // New connection ...

                int gameId = socket.getPort();
                System.out.println(getTimeStamp() + "New connection ID=" + gameId);
                menageConnection(gameId);

                Runnable connectionHandler = new GameConnectionHandler(socket, gameId);
                Thread connectionThread = new Thread(connectionHandler);
                connectionThread.start();
            } while (!finished);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Server error");
        }
    }

    private void menageConnection(int id) {
        if (hasNewClientArrived(id)) {
            connections++;
            getAvailableGame(id).addClient(new ClientInfo(id));
        } else {
            reconnectClient(id);
        }
    }

    private boolean hasNewClientArrived(int id) {
        return !games.containsKey(id);
    }

    private void reconnectClient(int id) {

    }

    /**
     * Returns existing game or creates new game and returns it.
     */
    private ServerSideGame getAvailableGame(int id) {
        Optional<ServerSideGame> gameToJoin = findGameToJoin();
        if(gameToJoin.isPresent()) {
            return gameToJoin.get();
        } else {
            ServerSideGame newGame = new ServerSideGame(id);
            addGame(id, newGame);
            return newGame;
        }
    }

    /**
     * Checks games if any can be joined and returns it if found, otherwise returns empty Optional.
     */
    private Optional<ServerSideGame> findGameToJoin() {
        for (ServerSideGame serverSideGame : games.values()) {
            if (serverSideGame.canClientJoinGame()) {
                return Optional.of(serverSideGame);
            }
        }
        return Optional.empty();
    }

    private void addGame(int id, ServerSideGame game) {
        games.put(id, game);
    }

    private void removeGame(int id) {
        games.remove(id);
//        connections -= 1; connections -= 2;
    }

    private void removeAllGames() {
        games.clear();
        connections = 0;
    }

}
