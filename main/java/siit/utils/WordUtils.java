package siit.utils;

import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.NumberingDefinitionsPart;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

import java.io.File;

import static siit.common.Constants.docxPath;
import static siit.common.Constants.pdfPath;

@Service
public class WordUtils {

    public void generateDocxFile(String html, String fileName) {

        try {

            File file = new File(pdfPath + "/" + fileName);
            if ( file.exists()) file.delete();

            String unescapedHtml = org.jsoup.parser.Parser.unescapeEntities(html, true);
//            System.out.println("\n" + unescapedHtml + "\n");
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

            NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
            wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
            ndp.unmarshalDefaultNumbering();

            XHTMLImporterImpl xHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xHTMLImporter.convert(unescapedHtml, null));

            wordMLPackage.save(file);
        } catch (JAXBException | Docx4JException e) {
            throw new RuntimeException("Eroare la generare docx: " + e.getCause() + " " +  e.getMessage());
        }
    }

    public void generateDocxFileWithURL(String url, String fileName) {

        try {

            File file = new File(pdfPath + "/" + fileName);
            if ( file.exists()) file.delete();

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

            NumberingDefinitionsPart ndp = new NumberingDefinitionsPart();
            wordMLPackage.getMainDocumentPart().addTargetPart(ndp);
            ndp.unmarshalDefaultNumbering();

            XHTMLImporterImpl xHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
            wordMLPackage.getMainDocumentPart().getContent().addAll(
                    xHTMLImporter.convert(url, "http://localhost:8080/"));

            wordMLPackage.save(file);
        } catch (JAXBException | Docx4JException e) {
            throw new RuntimeException("Eroare la generare docx v2: " + e.getCause() + " " +  e.getMessage());
        }
    }

}
