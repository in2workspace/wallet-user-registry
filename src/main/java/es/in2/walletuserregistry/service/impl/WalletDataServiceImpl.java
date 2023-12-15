package es.in2.walletuserregistry.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.in2.walletuserregistry.configuration.properties.WalletDataProperties;
import es.in2.walletuserregistry.domain.UserRegistryRequest;
import es.in2.walletuserregistry.domain.UserWalletDataRequest;
import es.in2.walletuserregistry.exception.FailedCommunicationException;
import es.in2.walletuserregistry.service.WalletDataService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.in2.walletuserregistry.utils.HttpUtils.postRequest;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletDataServiceImpl implements WalletDataService {

    private final ObjectMapper objectMapper;
    private final WalletDataProperties walletDataProperties;

    @Override
    public Mono<Void> saveUser(String processId, UserRegistryRequest userRegistryRequest, String userId) {
        try {
            // URL
            String url = walletDataProperties.url() + "/api/v2/users";
            // Headers
            List<Map.Entry<String, String>> headers = new ArrayList<>();
            headers.add(new AbstractMap.SimpleEntry<>(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE));
            // Body
            String body = objectMapper.writeValueAsString(UserWalletDataRequest.builder()
                    .userId(userId)
                    .username(userRegistryRequest.username())
                    .email(userRegistryRequest.email())
                    .build());
            return postRequest(processId, url, headers, body);
        } catch (IOException e) {
            log.error("Error saving user in Wallet Data: {}", e.getMessage());
            return Mono.error(new FailedCommunicationException("Error saving user in Wallet Data: " + e.getMessage()));
        }
    }

}
