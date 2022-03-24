package siit.web;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.MalformedURLException;
import java.net.URLConnection;
import java.nio.file.Path;
import java.nio.file.Paths;

import static siit.common.Constants.reportsPath;

@Controller
@RequestMapping("")
public class ReportsController {

    @GetMapping(value = "/reports/{fileName}")
    public ResponseEntity downloadReports(@PathVariable String fileName) {

        Path path = Paths.get(reportsPath + fileName);
        UrlResource resource = null;
        try {
            resource = new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error while downloading docx file: " + e.getMessage());
        }
        String mimeType = URLConnection.guessContentTypeFromName(((UrlResource) resource).getFilename());

        if (mimeType == null) {
            //unknown mimetype so set the mimetype to application/octet-stream
            mimeType = "application/octet-stream";
        }
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(mimeType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + ((UrlResource) resource).getFilename() + "\"")
                .body(resource);
    }


}
