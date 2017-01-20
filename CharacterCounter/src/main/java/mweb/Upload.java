package mweb;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.Charset;

@RestController
public class Upload {
  @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = "application/json")
  public Count upload(MultipartFile file) {
    try {
      byte[] bytes = file.getBytes();
      Charset set = Tools.getCharsets(bytes);
      return Tools.getCount(new String(bytes, set));
    } catch (IOException e) {
      e.printStackTrace();
      throw new RuntimeException(e);
    }
  }

  @RequestMapping(value = "/uploadStr", method = RequestMethod.POST, produces = "application/json")
  public Count upload(@RequestBody String json) {
    return Tools.getCount(json);
  }
}
