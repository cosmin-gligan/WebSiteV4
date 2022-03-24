package siit.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import static siit.common.Constants.baseUri;

@Service
public class HttpUtils {

    public String readURLintoString(String url){
        String response = null;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(baseUri + url).openConnection();

            try(InputStream inputStream = connection.getInputStream();){
                response =  new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
            }
        }catch (IOException e)  {
            throw new RuntimeException("Eror while reading URL into String: " + e.getMessage());
        }

        if ( response == null)
            throw new RuntimeException("Read HMTL is NULL 4 URL: " + url);

        return response;
    }

}
