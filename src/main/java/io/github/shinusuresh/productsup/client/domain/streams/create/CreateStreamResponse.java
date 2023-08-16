package io.github.shinusuresh.productsup.client.domain.streams.create;

import io.github.shinusuresh.productsup.client.domain.streams.Stream;
import io.github.shinusuresh.productsup.client.domain.streams.StreamError;
import lombok.Data;

import java.util.List;

@Data
public class CreateStreamResponse {

    private List<StreamError> errors;

    private Stream data;
}
