import java.io.*;
import java.util.Scanner;


public class EffectiveLines {

	private static File file;

	public static void main(String[] args) {
		System.out.println("请输入文件路径");
		Scanner in = new Scanner(System.in);
		String filePath = in.nextLine();
		in.close();
		file = judgeforFile(filePath);
		if (file != null) {
			System.out.println(getLines(file));
		} else {
			System.out.println("读取失败!");
		}
	}

	/**
	 * 对输入的路径,文件存在性,可读性进行判断
	 */
	private static File judgeforFile(String filePath) {
		if (filePath != null && filePath.length() != 0) {
			file = new File(filePath);
			if (file.exists() && file.canRead()) {
				return file;
			} else {
				System.out.println("文件不存在或不可读!");
			}
		} else {
			System.out.println("文件路径有误!");
		}
		return null;
	}

	/**
	 * 读取文件,并去除空行,package,import和注释行,计算有效行数
	 */
	private static String getLines(File myFile) {
		int lineCount = 0;
		String line = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(myFile));
			while ((line = br.readLine()) != null) {
				if (line.trim().equals("") || line.startsWith("package") || line.startsWith("import")
						|| line.startsWith("//")) {
					continue;
				} else {
					lineCount = lineCount + 1;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "文件的有效行数为:" + lineCount;
	}

}
