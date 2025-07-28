package io.spring.api;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.spring.core.message.Message;
import io.spring.core.message.MessageRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@AllArgsConstructor
public class MessagesApi {

  private final MessageRepository messageRepository;

  @PostMapping
  public ResponseEntity<Map<String, Object>> createMessage(
      @Valid @RequestBody MessageParam messageParam) {
    String content = messageParam.getContent();
    if (content == null || content.trim().isEmpty()) {
      Map<String, Object> errorResponse = new HashMap<>();
      errorResponse.put("error", "Content is required");
      return ResponseEntity.badRequest().body(errorResponse);
    }

    Message message = new Message(content.trim());
    messageRepository.save(message);

    Map<String, Object> response = new HashMap<>();
    response.put("message", Map.of(
        "id", message.getId(),
        "content", message.getContent(),
        "createdAt", message.getCreatedAt().toString()
    ));

    return ResponseEntity.ok(response);
  }

  @GetMapping
  public ResponseEntity<Map<String, Object>> getMessages() {
    List<Message> messages = messageRepository.findAll();

    Map<String, Object> response = new HashMap<>();
    response.put("messages", messages);
    response.put("count", messages.size());

    return ResponseEntity.ok(response);
  }
}

@Getter
@JsonRootName("message")
@NoArgsConstructor
class MessageParam {
  @NotBlank(message = "can't be empty")
  private String content;
}
