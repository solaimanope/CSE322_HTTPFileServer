import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;

public class ServerFileSender extends GetRequestHandler {
    public static String CTYPE = "application/force-download";
    ServerFileSender(File file) {
        status = "200 OK";
        contentType = CTYPE;
        contentLength = file.length();
    }

    @Override
    public void sendData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        sendHTMLIntro(printWriter);
        //printWriter.write(contentData);
        printWriter.flush();
    }
}
