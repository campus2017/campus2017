package service;

import domain.Context;
import domain.Statistics;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by luvslu on 2017/1/28.
 */
public interface ContextService {
    Statistics statContext(Context context);
    Statistics statFile(MultipartFile file);
}
