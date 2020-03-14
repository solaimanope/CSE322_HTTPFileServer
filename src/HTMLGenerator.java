import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;

public class HTMLGenerator extends  GetRequestHandler {
    private String contentData;
    HTMLGenerator(File file) {
        contentData =   "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "\t\t<link rel=\"icon\" href=\"data:,\">\n"+
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h1> Welcome to CSE 322 Offline 1</h1>\n";

        contentData += "\t\t<ul>\n";
        for (String fileName : file.list()) {
            File child = new File(file.getPath()+"/"+fileName);
            String link = child.getPath().substring(4);
            contentData += "\t\t\t<li><a href=\"" + link + "\">";

            if (child.isDirectory()) {
                contentData += "<b>" + fileName + "</b>";
            } else {
                contentData += fileName;
            }

            contentData += "</a></li>" + "<br>" + "\n";
        }
        contentData += "\t\t</ul>\n";
        contentData += "\t</body>\n";
        contentData += "</html>";

        status = "200 OK";
        contentType = "text/html";
        contentLength = contentData.length();
    }

    HTMLGenerator() {
        contentData =   "<html>\n" +
                "\t<head>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "\t\t<link rel=\"icon\" href=\"data:,\">\n"+
                "\t</head>\n" +
                "\t<body>\n" +
                "\t\t<h1> Welcome to CSE 322 Offline 1</h1>\n"+
                "\t\t<h2> ERROR 404: Not found </h2>\n"+
                "\t</body>\n"+
                "</html>";

        status = "404 Not Found";
        contentType = "text/html";
        contentLength = contentData.length();
    }

    @Override
    public void sendData(OutputStream outputStream) {
        PrintWriter printWriter = new PrintWriter(outputStream);
        sendHTMLIntro(printWriter);
        printWriter.write(contentData);
        printWriter.flush();
    }
}
