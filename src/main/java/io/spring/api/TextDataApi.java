package io.spring.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.annotation.JsonRootName;
import io.spring.core.textdata.TextData;
import io.spring.core.textdata.TextDataRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TextDataApi {
  private TextDataRepository textDataRepository;

  @PostMapping("/textdata")
  public ResponseEntity<Map<String, Object>> createTextData(@Valid @RequestBody CreateTextDataParam param) {
    TextData textData = new TextData(param.getTextContent());
    textDataRepository.save(textData);
    return ResponseEntity.status(201)
        .body(textDataResponse(textData));
  }

  @GetMapping("/textdata")
  public ResponseEntity<Map<String, Object>> getAllTextData() {
    List<TextData> textDataList = textDataRepository.findAll();
    return ResponseEntity.ok(textDataListResponse(textDataList));
  }

  private Map<String, Object> textDataResponse(TextData textData) {
    Map<String, Object> response = new HashMap<>();
    response.put("textData", textData);
    return response;
  }

  private Map<String, Object> textDataListResponse(List<TextData> textDataList) {
    Map<String, Object> response = new HashMap<>();
    response.put("textDataList", textDataList);
    return response;
  }
}

@Getter
@JsonRootName("textData")
@NoArgsConstructor
class CreateTextDataParam {
  @NotBlank(message = "can't be empty")
  private String textContent;
}
