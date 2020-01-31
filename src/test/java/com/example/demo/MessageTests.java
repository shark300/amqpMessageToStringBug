package com.example.demo;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

class MessageTests {

  private static final String UTF8_STRING = "ÁRVÍZTŰRŐ TÜKÖRFÚRÓGÉP";

  @ParameterizedTest
  @ValueSource(
      strings = {
        MessageProperties.CONTENT_TYPE_JSON,
        MessageProperties.CONTENT_TYPE_JSON_ALT,
        MessageProperties.CONTENT_TYPE_XML,
        MessageProperties.CONTENT_TYPE_TEXT_PLAIN
      })
  void toString_should_return_with_correct_value(String contentType) {
    // given
    var messageProperties = new MessageProperties();
    messageProperties.setContentType(contentType);
    messageProperties.setContentEncoding(UTF_8.displayName());

    var message =
        new Message(("{\"key\": \"" + UTF8_STRING + "\"").getBytes(UTF_8), messageProperties);

    // when
    var actual = message.toString();

    // then
    assertThat(actual).contains(UTF8_STRING);
  }
}
