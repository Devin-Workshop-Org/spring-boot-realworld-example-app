package io.spring.core.textdata;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface TextDataRepository {
  void save(TextData textData);
  
  Optional<TextData> findById(String id);
  
  List<TextData> findAll();
  
  void deleteById(String id);
}
