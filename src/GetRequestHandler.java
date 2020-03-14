import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;

public abstract class GetRequestHandler {
    protected String status;
    protected String contentType;
    protected long contentLength;

    protected void sendHTMLIntro(PrintWriter printWriter) {
        printWriter.write("HTTP/1.1 " + status + "\r\n");
        printWriter.write("Server: Java HTTP Server: 1.0\r\n");
        printWriter.write("Date: " + new Date() + "\r\n");
        printWriter.write("Content-Type: " + contentType + "\r\n");
        printWriter.write("Content-Length: " + contentLength + "\r\n");
        printWriter.write("\r\n");
        printWriter.flush();
    }

    public abstract void sendData(OutputStream outputStream) throws Exception;
}