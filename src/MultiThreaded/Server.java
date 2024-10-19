package MultiThreaded;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.lang.Thread;
import java.util.function.Consumer;

public class Server {

    public Consumer<Socket> getConsumer(){

//        return new Consumer<Socket>() {
//            @Override
//            public void accept(Socket clientSocket) {
//                try{
//                    PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
//                    toClient.println("Hello from server");
//
//                    toClient.close();
//                    clientSocket.close();
//
//                }catch (IOException e){
//                    e.printStackTrace();
//                }
//            }
//        };

        return (clientSocket) -> {
            try{
                PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream());
                toClient.println("Hello from server");

                toClient.close();
                clientSocket.close();

            }catch (IOException e){
                e.printStackTrace();
            }
        };
    }

    public static void main (String[] args){

        int port = 8010;
        Server server = new Server();


        try{
            ServerSocket serverSocket = new ServerSocket(port);
            serverSocket.setSoTimeout(10000);
            System.out.println("Listening on "+port);

            while (true){
                Socket acceptedSocket = serverSocket.accept();
                Thread thread = new Thread(
                        () -> server.getConsumer().accept(acceptedSocket)
                );
            }
        } catch (IOException e){
            System.out.println("SOCKET TIMEOUT");
        }
    }
}
