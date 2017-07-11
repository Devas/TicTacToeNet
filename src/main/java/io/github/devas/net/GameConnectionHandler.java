package io.github.devas.net;

import io.github.devas.util.TimeStamp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

class GameConnectionHandler implements ConnectionHandler, RichConnectionHandler {

    private Socket socket;
    private int id;
    private boolean finished = false;

    GameConnectionHandler(Socket socket, int id) {
        this.socket = socket;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            do {
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                String messageFromClient = fromClient.readLine();
                System.out.println(TimeStamp.getTimeStamp() + "Message received from client ID=" + id + " | Message: " + messageFromClient);
                // Do something with received message
                handleGameMessagesFromClient(messageFromClient);



                // Send whatever message you want to client
                String messageToClient = messageFromClient.toUpperCase() + '\n';
                System.out.println(TimeStamp.getTimeStamp() + "Sending message to client ID=" + id + " | Message: " + messageToClient);
                DataOutputStream toClient = new DataOutputStream(socket.getOutputStream());
                toClient.writeBytes(messageToClient);
                toClient.flush();
            } while (!finished);
        } catch (IOException e) {
            System.out.println(TimeStamp.getTimeStamp() + "Connection handler error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(TimeStamp.getTimeStamp() + "Closing resources error in connection handler");
            }
        }
    }

    private void handleGameMessagesFromClient(String message) {
        //  ....
        executeServerSideGame();
    }

    private void executeServerSideGame() {
        // TODO adapter + plug any game
    }

    @Override
    public Object call() throws Exception {
        return null;
    }

}
