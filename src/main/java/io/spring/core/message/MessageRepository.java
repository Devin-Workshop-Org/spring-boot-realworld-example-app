package io.spring.core.message;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository {
  void save(Message message);

  List<Message> findAll();
}
