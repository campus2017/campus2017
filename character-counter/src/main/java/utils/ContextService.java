package utils;

import domain.Context;
import domain.Statistics;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by manlixin on 2017/7/1.
 */
public interface ContextService {
    Statistics statContext(Context context);
    Statistics statFile(MultipartFile file);
}
