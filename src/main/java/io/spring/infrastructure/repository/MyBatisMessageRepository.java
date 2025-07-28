package io.spring.infrastructure.repository;

import io.spring.core.message.Message;
import io.spring.core.message.MessageRepository;
import io.spring.infrastructure.mybatis.mapper.MessageMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisMessageRepository implements MessageRepository {
  private final MessageMapper messageMapper;

  @Autowired
  public MyBatisMessageRepository(MessageMapper messageMapper) {
    this.messageMapper = messageMapper;
  }

  @Override
  public void save(Message message) {
    messageMapper.insert(message);
  }

  @Override
  public List<Message> findAll() {
    return messageMapper.findAll();
  }
}
