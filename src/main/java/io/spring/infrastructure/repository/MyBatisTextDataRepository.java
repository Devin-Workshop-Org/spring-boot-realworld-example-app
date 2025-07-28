package io.spring.infrastructure.repository;

import io.spring.core.textdata.TextData;
import io.spring.core.textdata.TextDataRepository;
import io.spring.infrastructure.mybatis.mapper.TextDataMapper;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MyBatisTextDataRepository implements TextDataRepository {
  private final TextDataMapper textDataMapper;

  @Autowired
  public MyBatisTextDataRepository(TextDataMapper textDataMapper) {
    this.textDataMapper = textDataMapper;
  }

  @Override
  public void save(TextData textData) {
    if (textDataMapper.findById(textData.getId()) == null) {
      textDataMapper.insert(textData);
    } else {
      textDataMapper.update(textData);
    }
  }

  @Override
  public Optional<TextData> findById(String id) {
    return Optional.ofNullable(textDataMapper.findById(id));
  }

  @Override
  public List<TextData> findAll() {
    return textDataMapper.findAll();
  }

  @Override
  public void deleteById(String id) {
    textDataMapper.deleteById(id);
  }
}
