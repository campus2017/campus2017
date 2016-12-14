package wz.base;

import java.util.List;
import java.util.Map;

/**
 * DataProvider 数据接口
 *
 * @author wz
 * @date 2016年11月11日 8:45 PM
 */
public interface DataProvider {

    /**
     * 获取指定币种人民币汇率中间价
     * @param currencies 币种
     * @return 日期,币种,汇率中间价
     */
    Map<String, Map<String, String>> getCentralParityRate(List<String> currencies);

}
