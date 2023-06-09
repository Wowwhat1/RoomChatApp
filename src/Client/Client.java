package Client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import Process.ProcessMsg;
import Transport.TransportMsg;

public class Client {
    private Socket socket;
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private ProcessMsg process;
    private TransportMsg transport;
    private String username;
    private boolean stopThread = false;

    public Client(Socket socket, String username) {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.username = username;
            process = new ProcessMsg();
            transport = new TransportMsg();
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    public void sendMessage() {
        try {
            bufferedWriter.write(username);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            Scanner scanner = new Scanner(System.in);
            while (socket.isConnected()) {
                String messageToSend = scanner.nextLine();
                if (messageToSend.equalsIgnoreCase("quit")) {
                    stopThread = true;
                    break;
                } else if (messageToSend.equalsIgnoreCase("undo")) {
                    if (!process.isEmpty()) {
                        String undoneMessage = process.popMessage();
                        bufferedWriter.write(username + " has undone the message: " + undoneMessage);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    } else {
                        System.out.println("There are no messages to undo.");
                    }
                } else {
                    transport.addMessage(messageToSend);
                    if (!transport.isEmpty()) {
                        bufferedWriter.write(username + ": " + transport.sendMessage());
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
            closeEverything(socket, bufferedReader, bufferedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listenToMessage() {
        new Thread(() -> {
            String messageFromGroupChat;

            while (socket.isConnected() && !stopThread) {
                try {
                    messageFromGroupChat = bufferedReader.readLine();
                    System.out.println(messageFromGroupChat);
                    process.pushMessage(messageFromGroupChat);
                } catch (IOException e) {
                    closeEverything(socket, bufferedReader, bufferedWriter);
                }
            }
        }).start();
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        try {
            socket.close();
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username please: ");
        String username = scanner.nextLine();
        Socket socket = new Socket("localhost", 1234);
        Client client = new Client(socket, username);
        client.listenToMessage();
        client.sendMessage();
    }
}
