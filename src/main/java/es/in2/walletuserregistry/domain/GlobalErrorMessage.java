package es.in2.walletuserregistry.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@Builder
public record GlobalErrorMessage(String title, String message, String path) {

}