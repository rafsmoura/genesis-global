package global.genesis.challenge.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class CoinCapClient implements Client {
    private static final Logger LOGGER = LoggerFactory.getLogger(CoinCapClient.class);

    public static final String ASSET_URL = "https://api.coincap.io/v2/assets?search=";
    public static final String PREFIX_HISTORY_URL = "https://api.coincap.io/v2/assets/";
    public static final String SUFIX_HISTORY_URL = "/history?interval=d1&start=1617753600000&end=1617753601000";

    @Override
    public Response send(Request request, String url) {

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet httpGet = new HttpGet(url);
            if (LOGGER.isDebugEnabled()) LOGGER.debug("Called URI: {}", httpGet.getURI());
            return getApiResponse(client.execute(httpGet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response getApiResponse(CloseableHttpResponse execute) {
        Response response = new Response();
       try {
           if (LOGGER.isDebugEnabled()) LOGGER.debug(execute.getStatusLine().toString());
           StringBuilder sb = new StringBuilder();
           BufferedReader r = new BufferedReader(new InputStreamReader(execute.getEntity().getContent()));
           for (String line = r.readLine(); line != null; line = r.readLine()){
               sb.append(line);
           }
           response.setJsonResponse(sb.toString());
       } catch (Exception e) {
           LOGGER.error("Error parsing the API Response");
           e.printStackTrace();
       }
       return response;
    }
}
