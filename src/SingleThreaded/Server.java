package SingleThreaded;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public void run() throws IOException {
        int port = 8010;

        ServerSocket socket = new ServerSocket(port);
//        socket.setSoTimeout(10000);
        while (true){
            System.out.println("Listening on port: " + port);
            Socket acceptedConnection = socket.accept();
            System.out.println("Connection accepted client " + acceptedConnection.getRemoteSocketAddress());
            PrintWriter toClient = new PrintWriter(acceptedConnection.getOutputStream());
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(acceptedConnection.getInputStream()));
            toClient.println("Hello from server");
            toClient.close();

            fromClient.close();
            acceptedConnection.close();
        }
    }

    public static void main(String[] args){

        Server server = new Server();

        try{
            server.run();
        } catch (IOException eIO){
            eIO.printStackTrace();
        }
    }
}
