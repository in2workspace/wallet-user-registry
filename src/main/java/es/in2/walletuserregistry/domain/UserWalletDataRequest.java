package es.in2.walletuserregistry.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record UserWalletDataRequest (
    @JsonProperty("userId") String userId,
    @JsonProperty("username") String username,
    @JsonProperty("email")String email) {
}
