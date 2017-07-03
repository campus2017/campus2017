package utils;

import domain.Context;
import domain.Statistics;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Wang Yishu on 2017/6/28.
 */
public interface ContextService {
    Statistics statContext(Context context);
    Statistics statFile(MultipartFile file);
}
