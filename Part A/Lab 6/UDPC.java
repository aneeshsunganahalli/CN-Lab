// Client Side

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPC {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost";
        final int SERVER_PORT = 12345;

        try (DatagramSocket clientSocket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message for server (type 'exit' to quit): ");
                String message = scanner.nextLine();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
                clientSocket.send(sendPacket);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// OR:
// public class UDPC
//          {
//            public static void main(String[] args) throws Exception
//          {
//           BufferedReader inFromUser=new BufferedReader(new InputStreamReader(System.in));
//           DatagramSocket clientSocket=new DatagramSocket();
//           InetAddress IPAddress=InetAddress.getByName("localhost");
//           byte[] sendData = new byte[1024];
//           byte[] receiveData=new byte[1024];
//           System.out.println("Enter the string in lowercase so that you receive the message in Uppercase from the server");
//           String sentence=inFromUser.readLine();
//           sendData=sentence.getBytes();
//          DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 9884);
//           clientSocket.send(sendPacket);
//           DatagramPacket receivePacket=new DatagramPacket(receiveData, receiveData.length);
//           clientSocket.receive(receivePacket);
//           String modifiedSentence=new String(receivePacket.getData());
//           System.out.println("FROM SERVER: "+modifiedSentence);
//           clientSocket.close();
               
//           }
// }
