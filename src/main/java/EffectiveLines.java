import javax.swing.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by zdl on 2017/1/10.
 */
public class EffectiveLines extends IOException {
    public static void main(String[] args) {

    }
    EffectiveLines() {
        this.file = null;
        this.frame = new InnerFrame();
    }
    EffectiveLines(String file) {
        this.file = file;
        this.frame = new InnerFrame();
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int count() {
        if (file == null)
            return 0;

        return 0;
    }
    private boolean isValidLine() {
        return false;
    }

    private String file;
    private InnerFrame frame;
}

class InnerFrame extends JFrame {
    InnerFrame() {
        init();
        this.setLayout(null);
        this.setDefaultCloseOperation(1);
        this.isVisible();
    }
    private void init(){

    }
}
