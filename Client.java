// File Name GClient.java

import java.net.*;
import java.io.*;

public class Client
{
    public static void main(String [] args)
    {
        String serverName = args[0];
        int port = Integer.parseInt(args[1]);
        try
        {
            //System.out.println("Connecting to " + serverName
            //                   + " on port " + port + "\n");
            Socket client = new Socket(serverName, port);
            OutputStream outToServer = client.getOutputStream();
            DataOutputStream out =
            new DataOutputStream(outToServer);
            
             String ms = null;
            
            System.out.println("    ");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            ms = br.readLine();
            
            out.writeUTF(" "+ms);
            
            InputStream inFromServer = client.getInputStream();
            DataInputStream in =
            new DataInputStream(inFromServer);
            System.out.println("Server says " + in.readUTF());
            client.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}