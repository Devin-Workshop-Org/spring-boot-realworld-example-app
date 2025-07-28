package io.spring.infrastructure.mybatis.mapper;

import io.spring.core.message.Message;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MessageMapper {
  void insert(@Param("message") Message message);

  List<Message> findAll();
}
