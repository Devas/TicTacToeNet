package io.github.devas.net;

/**
 * Groups settings for sockets in one class.
 */
class SocketSettings implements Settings {

    private int port;
    private String host;

    SocketSettings(int port) {
        this.port = port;
        this.host = "localhost";
    }

    SocketSettings(int port, String host) {
        this.port = port;
        this.host = host;
    }

    int getPort() {
        return port;
    }

    String getHost() {
        return host;
    }

}
