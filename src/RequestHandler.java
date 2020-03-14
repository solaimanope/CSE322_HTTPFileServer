import java.io.*;
import java.net.Socket;

public class RequestHandler extends Thread {
    Socket socket;
    OutputStream socketOutputStream;
    InputStream socketInputStream;
    //PrintWriter printWriter;
    BufferedReader bufferedReader;

    public RequestHandler(Socket socket) throws Exception {
        this.socket = socket;
        socketOutputStream = socket.getOutputStream();
        socketInputStream = socket.getInputStream();
        //printWriter = new PrintWriter(socketOutputStream);
        bufferedReader = new BufferedReader(new InputStreamReader(socketInputStream));
    }

    @Override
    public void run() {
        try {
            String request = bufferedReader.readLine();
            System.out.println("Thread started for request: " + request);
            if (request == null || request.length() == 0) return;

            if (request.startsWith("GET")) {
                String path = extractPath(request);
                System.out.println("Requested path: " + path);

                File file = new File(path);
                GetRequestHandler getRequestHandler;

                if (file != null && file.isDirectory()) {
                    getRequestHandler = new HTMLGenerator(file);
                }  else if (file != null && file.isFile()) {
                    getRequestHandler = new ServerFileSender(file);
                } else {
                    getRequestHandler = new HTMLGenerator();
                }

                getRequestHandler.sendData(socketOutputStream);
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    String extractPath(String request) {
        return "root" + request.substring(4, request.length()-9).replaceAll("%20", " ");
    }
}
