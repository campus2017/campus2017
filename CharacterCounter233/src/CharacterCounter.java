import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by admin on 2017/2/5.
 */

@Controller
public class CharacterCounter {
    @RequestMapping("/")
    public String Page() {
        return "index";
    }
}
