package io.spring.core.textdata;

import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class TextData {
  private String id;
  private String textContent;

  public TextData(String textContent) {
    this.id = UUID.randomUUID().toString();
    this.textContent = textContent;
  }

  public void updateContent(String textContent) {
    if (textContent != null && !textContent.trim().isEmpty()) {
      this.textContent = textContent;
    }
  }
}
