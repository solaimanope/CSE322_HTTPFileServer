import java.io.*;

public class ServerFileSender extends GetRequestHandler {
    private static int BUFFER_SIZE = 1;

    File file;
    ServerFileSender(File file) {
        this.file = file;
        status = "200 OK";
        contentType = "application/force-download";
        contentLength = file.length();
    }

    @Override
    public void sendData(OutputStream outputStream) throws Exception {
        PrintWriter printWriter = new PrintWriter(outputStream);
        sendHTMLIntro(printWriter);

        DataInputStream dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));

        byte[] buffer = new byte[BUFFER_SIZE];
        while (true) {
            int readBytes = dataInputStream.read(buffer);
            if (readBytes < 1) break;
            outputStream.write(buffer, 0, readBytes);
        }
        outputStream.flush();

        dataInputStream.close();
    }
}
