package es.in2.wallet.user.registry.api.service;

import es.in2.wallet.user.registry.api.exception.FailedCommunicationException;
import es.in2.wallet.user.registry.api.model.UserRequest;

import java.io.IOException;

public interface WalletDataCommunicationService {
    void saveUserDataOnWalletData(UserRequest userRequest,String userId) throws IOException, FailedCommunicationException, InterruptedException;
}
