import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int PORT = 5012;

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("Server ready to accept requests");

        while (true) {
            Socket socket = serverSocket.accept();
            new RequestHandler(socket).start();
        }
    }

}
