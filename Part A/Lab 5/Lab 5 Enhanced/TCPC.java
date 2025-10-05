// CLient side

import java.net.*;
import java.io.*; 
  public class TCPC
    {
       public static void main(String[] args) throws Exception
       {
         Socket socket=new Socket("127.0.01", 4000);
          System.out.println("Enter the filename");

         BufferedReader keyRead=new BufferedReader(new InputStreamReader(System.in));
         String fname=keyRead.readLine();

         OutputStream ostream=socket.getOutputStream();
         PrintWriter pwrite=new PrintWriter(ostream, true);
         pwrite.println(fname);

         InputStream istream=socket.getInputStream();
         BufferedReader socketRead=new BufferedReader(new InputStreamReader(istream));

         String str;

         while((str=socketRead.readLine())!=null)
           {
              System.out.println(str);
           }

           //Cleaning up resources
         pwrite.close();
         socketRead.close();
         keyRead.close();
       }
    }       