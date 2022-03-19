package siit.utils;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

import static siit.common.Constants.baseUri;
import static siit.common.Constants.pdfPath;

@Service
public class PdfUtils {

    @Resource
    HttpUtils httpUtils;

    public void printPDF(String url, String destinationFile) {
        try {
            String html = httpUtils.readURLintoString(url);
//            System.out.println("html is: \n" + html + "\n");
            generatePdfFromHtml(html, destinationFile);
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private void generatePdfFromHtml(String html, String destinationFile) throws IOException {
        try (OutputStream os = new FileOutputStream(new File(pdfPath + "/" + destinationFile))) {
            String baseUrl = FileSystems.getDefault()
                    .getPath("src/main/resources/")
                    .toUri().toURL().toString();
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri(destinationFile);
            builder.toStream(os);
//            builder.withW3cDocument(new W3CDom().fromJsoup(getXHtml(html)), OrderController.class.getResource("/document.html").toExternalForm());
            builder.withW3cDocument(new W3CDom().fromJsoup(getXHtml(html)), baseUri);
            builder.run();
        }
    }

    public Document getXHtml(String html) {
        Document document = Jsoup.parse(html, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
//        System.out.println(document.toString());
        return document;
    }


}
