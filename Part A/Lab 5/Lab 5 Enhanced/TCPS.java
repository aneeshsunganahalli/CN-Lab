/* Using TCP/IP sockets, write a client – server program to make the 
client send the file name and to make the server send back the contents 
of the requested file if present*/


/*Server side*/

import java.net.*; 
import java.io.*; 
 public class TCPS
   {
      public static void main(String[] args) throws Exception
        {
           ServerSocket socket=new ServerSocket(4000);
           
           System.out.println("Server ready for connection");
           Socket clientSocket=socket.accept();

           System.out.println("Connection Is successful and waiting for the client request");
           InputStream istream=clientSocket.getInputStream();
           BufferedReader fileRead=new BufferedReader(new InputStreamReader(istream));

          String fname=fileRead.readLine();
          BufferedReader ContentRead=new BufferedReader(new FileReader (fname));
          OutputStream ostream=clientSocket.getOutputStream();

           PrintWriter pwrite=new PrintWriter(ostream, true);

          String str;

          while((str=ContentRead.readLine())!=null){
           pwrite.println(str);
            }

        //Cleaning up resources
         clientSocket.close();
         socket.close();
          pwrite.close();
         fileRead.close();
          ContentRead.close();
        }
    }
