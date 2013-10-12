// File Name Server.java

import java.net.*;
import java.io.*;

public class Server extends Thread
{
    private ServerSocket serverSocket;
    
    public Server(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }
    
    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +
                                   serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                DataInputStream in =
                new DataInputStream(server.getInputStream());
                System.out.println(in.readUTF());
                DataOutputStream out =
                new DataOutputStream(server.getOutputStream());
                
                 String ms = null;
                
                System.out.println("    ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                ms = br.readLine();
                
                out.writeUTF(""+ms);
                server.close();
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String [] args)
    {
        int port = Integer.parseInt(args[0]);
        try
        {
            Thread t = new Server(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}