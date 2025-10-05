import java.io.*;
import java.net.*;

public class FileServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                handleClient(clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String fileName = reader.readLine();
            System.out.println("File requested: " + fileName);

            File file = new File(fileName);
            if (file.exists()) {
                BufferedReader contentReader = new BufferedReader(new FileReader(file));
                String line;
                    while ((line = contentReader.readLine()) != null) {
                        writer.println(line);
                    }
            } else {
                writer.println("File not found");
            }

            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
