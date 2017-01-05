package sei.bean;

import sei.tool.CharacterType;
import sei.tool.WordsTopCalculator;

import java.util.List;
import java.util.Map;

/**
 * Created by dell on 2017/1/4.
 */
public class ShowCountBean {
    private Map<CharacterType,Integer> stmap;
    private List<WordsTopCalculator.WordCountBean> tplist;

    public Map<CharacterType, Integer> getStmap() {
        return stmap;
    }

    public void setStmap(Map<CharacterType, Integer> stmap) {
        this.stmap = stmap;
    }

    public List<WordsTopCalculator.WordCountBean> getTplist() {
        return tplist;
    }

    public void setTplist(List<WordsTopCalculator.WordCountBean> tplist) {
        this.tplist = tplist;
    }

}
