// Sever_23365943_23365676.java
import java.io.*;
import java.net.*;
import java.util.*;

public class Sever_23365943_23365676 {
    private static HashMap<String, String> schedule = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Server is running...");
            while (true) {
                Socket socket = serverSocket.accept();
                new ClientHandler(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class ClientHandler extends Thread {
        private Socket socket;

        ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                String request;
                while ((request = input.readLine()) != null) {
                    if (request.equals("STOP")) {
                        output.println("TERMINATE");
                        break;
                    }
                    try {
                        String response = processRequest(request);
                        output.println(response);
                    } catch (IncorrectActionException e) {
                        output.println("Error: " + e.getMessage());
                    }
                }
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String processRequest(String request) throws IncorrectActionException {
            String[] parts = request.split(",");
            if (parts.length < 2) {
                throw new IncorrectActionException("Invalid Request Format");
            }
            String action = parts[0];
            String details = parts[1];
            switch (action) {
                case "Add":
                    if (schedule.containsKey(details)) {
                        return "Clash Detected!";
                    }
                    schedule.put(details, "Booked");
                    return "Lecture Added";
                case "Remove":
                    if (schedule.containsKey(details)) {
                        schedule.remove(details);
                        return "Lecture Removed";
                    }
                    return "Lecture Not Found";
                case "Display":
                    return schedule.toString();
                default:
                    throw new IncorrectActionException("Invalid Action: " + action);
            }
        }
    }
}