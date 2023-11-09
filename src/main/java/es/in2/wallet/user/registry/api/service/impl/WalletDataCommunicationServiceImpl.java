package es.in2.wallet.user.registry.api.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.model.UserRequest;
import es.in2.wallet.user.registry.api.model.UserRequestWalletData;
import es.in2.wallet.user.registry.api.service.WalletDataCommunicationService;
import es.in2.wallet.user.registry.api.util.ApplicationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static es.in2.wallet.user.registry.api.util.ApiUtils.CONTENT_TYPE;
import static es.in2.wallet.user.registry.api.util.ApiUtils.CONTENT_TYPE_APPLICATION_JSON;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletDataCommunicationServiceImpl implements WalletDataCommunicationService {

    @Value("${wallet-data.url}")
    private String walletDataUrl;

    private final ApplicationUtils applicationUtils;

    @Override
    public void saveUserDataOnWalletData(UserRequest userRequest, String userId) throws IOException, FailedCommunicationException, InterruptedException {
        UserRequestWalletData userWalletData = new UserRequestWalletData(userId, userRequest.getUsername(), userRequest.getEmail());

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(userWalletData);
        log.info("body: {}", body);

        List<Map.Entry<String, String>> headers = new ArrayList<>();
        headers.add(new AbstractMap.SimpleEntry<>(CONTENT_TYPE, CONTENT_TYPE_APPLICATION_JSON));

        String url = walletDataUrl;
        applicationUtils.postRequest(url, headers, body);
    }
}