package io.spring.infrastructure.mybatis.mapper;

import io.spring.core.textdata.TextData;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TextDataMapper {
  void insert(@Param("textData") TextData textData);
  
  void update(@Param("textData") TextData textData);
  
  TextData findById(@Param("id") String id);
  
  List<TextData> findAll();
  
  void deleteById(@Param("id") String id);
}
