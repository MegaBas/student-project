import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Connection implements Runnable {
    ObjectInputStream in = null;
    ObjectOutputStream out = null;
    Socket socket;
    Message msg;

    public Connection(Socket socket){
        this.socket = socket;

        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            //msg = (Message) in.readObject();
            //System.out.println(msg.getName() + ": " + msg.getMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Потоки запущены...");

    }


    @Override
    public void run() {
        try {
            msg = (Message) in.readObject();
            System.out.println(msg.getName() + ": " + msg.getMsg());

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
