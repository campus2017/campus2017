package lnu.pojo;

/**
 * 字符统计器
 * @author Administrator
 *
 */
public class Counter {
	
	private int character;//英文字母
	
	private int digital;//数字
	
	private int chinese;//汉字
	
	private int punctuation;//标点符号

	public Counter() {
		super();
	}

	public Counter(int character, int digital, int chinese, int punctuation) {
		super();
		this.character = character;
		this.digital = digital;
		this.chinese = chinese;
		this.punctuation = punctuation;
	}

	public int getCharacter() {
		return character;
	}

	public void setCharacter(int character) {
		this.character = character;
	}

	public int getDigital() {
		return digital;
	}

	public void setDigital(int digital) {
		this.digital = digital;
	}

	public int getChinese() {
		return chinese;
	}

	public void setChinese(int chinese) {
		this.chinese = chinese;
	}

	public int getPunctuation() {
		return punctuation;
	}

	public void setPunctuation(int punctuation) {
		this.punctuation = punctuation;
	}

	@Override
	public String toString() {
		return "Counter [character=" + character + ", digital=" + digital
				+ ", chinese=" + chinese + ", punctuation=" + punctuation + "]";
	}

}
