package sei.tool;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import sei.tool.CharacterType;

public class CounterTool {

	public static Map<CharacterType,Integer> createMap(){
		return new HashMap<CharacterType,Integer>(){{
			put(CharacterType.ENG,0);
			put(CharacterType.NUM,0);
			put(CharacterType.CHZ,0);
			put(CharacterType.PUNCT,0);
			put(CharacterType.OTHR,0);
		}};
	}
	public static CharacterType judgeType(String str){
		if(str.matches("[A-Za-z]")) return CharacterType.ENG;
		if(str.matches("[0-9]"))	return CharacterType.NUM;
		if(str.matches("[\u4E00-\u9FA5]")) return CharacterType.CHZ;
		if(str.matches("[\\pP\\p{Punct}]")) return CharacterType.PUNCT;
		return CharacterType.OTHR;
	}
}
