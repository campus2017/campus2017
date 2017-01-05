package sei.tool;

public enum CharacterType {
	
	ENG("英文"),NUM("数字"),CHZ("中文"),PUNCT("中英文标点符号"),OTHR("其他符号");
	
	private String name;
	CharacterType(String name){
		this.name=name;
	}
	public String getName() {
		return name;
	}
}
