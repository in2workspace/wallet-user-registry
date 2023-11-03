package es.in2.wallet.user.registry.api.util;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.net.URI;

@Component
@Slf4j
public class ApplicationUtils {
    public String postRequest(String url, List<Map.Entry<String, String>> headers, String body) throws FailedCommunicationException, IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = httpRequestBuilder(url, headers)
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logCRUD(request, response, body);
        checkPostResponseStatus(response.statusCode());
        return response.body();
    }



    private HttpRequest.Builder httpRequestBuilder(String url, List<Map.Entry<String, String>> headers) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder().uri(URI.create(url));
        headers.forEach(entry -> requestBuilder.header(entry.getKey(), entry.getValue()));
        return requestBuilder;
    }

    private void logCRUD(HttpRequest request, HttpResponse<String> response, String body) {
        if (log.isDebugEnabled()) {
            log.debug("********************************************************************************");
            log.debug(">>> URI: {}", request.uri());
            log.debug(">>> HEADERS: {}", request.headers());
            log.debug(">>> BODY: {}", body);
            log.debug("<<< STATUS CODE: {}", response.statusCode());
            log.debug("<<< HEADERS: {}", response.headers());
            log.debug("<<< BODY: {}", response.body());
            log.debug("********************************************************************************");
        }
    }

    private void checkPostResponseStatus(int statusCode) throws FailedCommunicationException {
        if (statusCode >= 200 && statusCode <= 201) {
            log.info("POST OK");
        } else if (statusCode == 422) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Entity already exists");
        } else {
            throw new FailedCommunicationException("HttpStatus response " + statusCode);
        }
    }
}
