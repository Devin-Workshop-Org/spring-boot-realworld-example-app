package io.spring.core.message;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.joda.time.DateTime;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Message {
  private String id;
  private String content;
  private DateTime createdAt;

  public Message(String content) {
    this.id = UUID.randomUUID().toString();
    this.content = content;
    this.createdAt = new DateTime();
  }
}
