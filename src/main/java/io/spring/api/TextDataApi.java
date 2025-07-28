package io.spring.api;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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

  @RequestMapping(path = "/textdata", method = POST)
  public ResponseEntity createTextData(@Valid @RequestBody CreateTextDataParam param) {
    TextData textData = new TextData(param.getTextContent());
    textDataRepository.save(textData);
    return ResponseEntity.status(201)
        .body(textDataResponse(textData));
  }

  @RequestMapping(path = "/textdata", method = GET)
  public ResponseEntity getAllTextData() {
    List<TextData> textDataList = textDataRepository.findAll();
    return ResponseEntity.ok(textDataListResponse(textDataList));
  }

  private Map<String, Object> textDataResponse(TextData textData) {
    return new HashMap<String, Object>() {
      {
        put("textData", textData);
      }
    };
  }

  private Map<String, Object> textDataListResponse(List<TextData> textDataList) {
    return new HashMap<String, Object>() {
      {
        put("textDataList", textDataList);
      }
    };
  }
}

@Getter
@JsonRootName("textData")
@NoArgsConstructor
class CreateTextDataParam {
  @NotBlank(message = "can't be empty")
  private String textContent;
}
