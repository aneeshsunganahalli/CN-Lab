// Write a program on datagram socket for client/server to display the 
// messages on client side, typed at the server side. 

// Server side

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPS {
    public static void main(String[] args) {
        final int PORT = 12345;

        try (DatagramSocket serverSocket = new DatagramSocket(PORT)) {
            System.out.println("UDP Server is running on port " + PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData());
                System.out.println("Client: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// public class UDPS {
//     public static void main(String args[]) throws Exception {
//     DatagramSocket serverSocket=null;
//     try{
//         serverSocket = new DatagramSocket(9884);
//         System.out.println("Server is Ready for the client");
//         byte[] receiveData = new byte[1024];
//         byte[] sendData = new byte[1024];
        
//             while (true) {
            
//                 DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
//                 serverSocket.receive(receivePacket);
//                 String sentence = new String(receivePacket.getData());
//                 System.out.println("RECEIVED: " + sentence);
//                 InetAddress IPAddress = receivePacket.getAddress();
//                 int port = receivePacket.getPort();
//                 String capitalizedSentence = sentence.toUpperCase();
//                 sendData = capitalizedSentence.getBytes();
//                 DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
//                 serverSocket.send(sendPacket);
//             }           
//     }catch (Exception e) {
//                     e.printStackTrace();
//                 }finally{
//                 serverSocket.close();              
         
//       }
//   }
// }
