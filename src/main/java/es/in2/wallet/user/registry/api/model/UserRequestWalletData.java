package es.in2.wallet.user.registry.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserRequestWalletData {
    @JsonProperty("userId")
    private String userId;

    @JsonProperty("username")
    private String username;

    @JsonProperty("email")
    private String email;
}
