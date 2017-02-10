package qunar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EffectiveLines {

	public static int effectivenum = 0;

	public static void main(String[] args) throws IOException {
		String pathName = "E:\\123.java";
		File file = new File(pathName);
		computeLines(file);
		System.out.println(effectivenum);
	}

	public static void computeLines(File file) throws IOException {
		BufferedReader bf = null;
		bf = new BufferedReader(new FileReader(file));
		String lineStr = "";
		int totallines = 0;
		int whitelines = 0;
		int commentlines = 0;
		while ((lineStr = bf.readLine()) != null) {
			totallines++;
			whitelines += whiteLines(lineStr);
			commentlines += commentLines(lineStr);
		}
		effectivenum = totallines - whitelines;
		bf.close();

	}

	public static int whiteLines(String linestr) {
		int whiteLines = 0;
		if (linestr.matches("^[\\s&&[^\\n]]*$")) {
			whiteLines++;
		}
		return whiteLines;
	}

	public static int commentLines(String lineStr) {
		int commentLines = 0;
		boolean bComment = true;
		if (lineStr.matches("\\s*/\\*{1,}.*(\\*/).*")) {

			commentLines++;
		} else if (lineStr.matches("\\s*/\\*{1,}.*[^\\*/].*")) {

			commentLines++;
			bComment = true;
		} else if (true == bComment) {
			commentLines++;
			if (lineStr.matches("\\s*[\\*/]+\\s*")) {
				bComment = false;
			}
		} else if (lineStr.matches("^[\\s]*//.*")) {

			commentLines++;
		}
		return commentLines;
	}

}
