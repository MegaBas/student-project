import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        ServerSocket server = null;
        Socket clientSocket = null;
        ObjectInputStream in = null;
        ObjectOutputStream out = null;
        Message msg;

        try {
            server = new ServerSocket(1234);
            System.out.println("Сервер запущен...");
        } catch (Exception e){
            e.printStackTrace();
        }

        while (true) {

            try {
                clientSocket = server.accept();
                try {
                    in = new ObjectInputStream(clientSocket.getInputStream());
                    out = new ObjectOutputStream(clientSocket.getOutputStream());
                    msg = (Message) in.readObject();
                    System.out.println(msg.getName() + ": " + msg.getMsg());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            /*Connection con = new Connection(clientSocket);
            Thread th = new Thread(con);
            th.start();
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

        }

    }

}
