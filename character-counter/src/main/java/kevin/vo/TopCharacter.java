package kevin.vo;

/**
 * Created by Kevin on 2017/6/25.
 */
public class TopCharacter {
    Character character;
    Integer integer;


    public TopCharacter(Character character, Integer integer) {
        this.character = character;
        this.integer = integer;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Integer getInteger() {
        return integer;
    }

    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public String toString() {
        return "TopCharacter{" +
                "character=" + character +
                ", integer=" + integer +
                '}';
    }
}
