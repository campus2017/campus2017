package lnu.Util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import lnu.controller.CharacterExtendCompute;
import lnu.pojo.Character2;
import lnu.pojo.Counter;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import java.util.logging.Logger;

/**
 * Created by DELL on 2017/4/18.
 * 1、计算每一类字符出现的个数
 * 2、并将topk统计的出现次数最多的字符按照次数从大到小进行排序，如果出现次数相同，则按照字典序进行排序
 */
public class CountCharacter {
	//Logger logger=Logger.getLogger(CountCharacter.class);
	//Logger log=Logger.getLogger();
	public CharacterExtendCompute count(String strTxt){
		long starttime = System.nanoTime();
		Counter counter = new Counter();

		/*private int character;//英文字母
		private int digital;//数字
		private int chinese;//汉字
		private int punctuation;//标点符号*/

		int character = 0;//英文字母
		int digital = 0;//数字
		int chinese = 0;//汉字
		int punctuation = 0;//标点符号
		Map<Character,Integer> map = new HashMap<Character,Integer>();
		Multiset<Character> set = HashMultiset.create();


		//strTxt.rep


		for(int i=0;i<strTxt.length();i++){
			char c = strTxt.charAt(i);
			//!(c+"").trim().equals("")
			//!(c=='\r'||c=='\t'||c=='\n'||c==' '||c=='\u0008')
			/*Pattern p1 = Pattern.compile("\\s|\\t|\\r|\\n");
			Matcher m1 = p1.matcher(c+"");
			if (m1.find()){
				//System.out.println("打印c:"+c);
				continue;
			}*/
			//set.add(c);!(c+"").trim().equals("")
			if((!(c+"").trim().equals(""))&&c!=12288){
			//if(!(c=='\u0008'||c=='\t'||c=='\r'||c=='\n'||c==12288)){
			//if(c!='\0'){
				set.add(c);
				if(c>=48 && c<57){ //数字范围是48-59
					digital ++;
				}else if((c>65 && c<90) || (c>=97 && c<122)){ //大写字母范围65-90，小写字母97-122
					character ++;
				}else if((c>=33 && c<=47)||(c>=58 && c<=64)||(c>=91 && c<=96) ||(c>=123 &&c<=126)){//英文字符
					punctuation ++;
				}
			}

		}

		String regex1 = "[\u4E00-\u9FA5]"; //匹配汉字
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(strTxt);
		while(m1.find()){
			chinese ++;
		}

		//Matcher m1 = p1.matcher()
		String regex2 = "[·~！#￥%……&*（）——+|【】{}“’：；《》，。？、]";//汉字字符
		Pattern p2 = Pattern.compile(regex2);
		Matcher m2 = p2.matcher(strTxt);
		while (m2.find()){
			punctuation ++;
		}

		System.out.println("英文字母: "+character+"个");
		System.out.println("数字    : "+digital+"个");
		System.out.println("中文汉字: "+chinese+"个");
		System.out.println("中英文标点符号: "+punctuation+"个");

		long endtime = System.nanoTime();

		counter.setCharacter(character);
		counter.setDigital(digital);
		counter.setChinese(chinese);
		counter.setPunctuation(punctuation);



		ArrayList<Character2> charList = new ArrayList<Character2>();
		for(Character c:set.elementSet()){
			Character2 character2 = new Character2();
			character2.setData(c);
			character2.setCount(set.count(c));
			charList.add(character2);
			//System.out.println(c+" 次数:"+set.count(c));
		}
		int size=charList.size();
		Character2[] array = (Character2[])charList.toArray(new Character2[size]);
		CharacterTopK characterTopK = new CharacterTopK();
		Character2[] arr = null;
		if(array.length>=3){
			arr = characterTopK.topK(array,3);
		}else{
			arr = characterTopK.topK(array,array.length);
		}


		ArrayList<Character2> characterArrayList = new ArrayList<Character2>();
		for(Character2 c:arr){
			characterArrayList.add(c);
		}
		for(Character2 c:characterArrayList){
			System.out.println(c.getData()+" 出现次数"+c.getCount());
		}

		Comparator<Character2> cmp = new ComparatorCharacter();
		Collections.sort(characterArrayList,cmp);

		for(Character2 c:characterArrayList){
			System.out.println(c.getData()+";编码:"+Integer.valueOf(c.getData())+";出现次数"+c.getCount());
		}
		//System.out.println("12344");
		CharacterExtendCompute characterExtendCompute = new CharacterExtendCompute();
		characterExtendCompute.setCounter(counter);//将统计的各类字符出现的次数信息添加进去
		characterExtendCompute.setList(characterArrayList);//将出现次数最多的字符添加进去
		System.out.println((endtime-starttime)/1000000+"毫秒");
		return  characterExtendCompute;
	}}

class ComparatorCharacter implements Comparator<Character2> {

	@Override
	public int compare(Character2 c1,Character2 c2) {
		int flag = Integer.compare(c2.getCount(),c1.getCount()); //将字符按照出现次数从大到小进行排序
		if(flag == 0){ //如果出现次数相同，则进行将字符进行字典序排序
			return Character.toLowerCase(c1.getData()) - Character.toLowerCase(c2.getData());
		}
		return flag;
	}
}