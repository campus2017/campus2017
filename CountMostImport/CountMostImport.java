package qunar;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class CountMostImport {

	String dir;
	HashMap<String, Integer> importclassmap;

	public static void main(String[] args) {
		CountMostImport cmi = new CountMostImport("E:/demo/algorithm/src");
	}

	public CountMostImport(String dir) {
		this.dir = dir;
		importclassmap = new HashMap<String, Integer>();
		this.statisticsClass(new File(this.dir));
	}

	public int get(String classname) {
		Integer value = importclassmap.get(classname);
		if (value == null)
			return 0;
		return value;
	}

	public void processFile(File file) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				if (line.startsWith("public") || line.startsWith("class")) {
					break;
				}
				if (line.startsWith("import")) {
					String className = line.substring(6, line.length() - 1).trim();
					Integer value = get(className);
					if (value == null) {
						importclassmap.put(className, 1);
					} else {
						importclassmap.put(className, value + 1);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void statisticsClass(File file) {
		if (!file.isDirectory()) {
			processFile(file);
		} else {
			File[] files = file.listFiles();
			for (File tmpFile : files) {
				statisticsClass(tmpFile);
			}
		}

	}

	public String getMostImportClazzName() {
		int max = Integer.MIN_VALUE;
		String classname = null;
		for (Entry item : this.importclassmap.entrySet()) {
			String key = (String) item.getKey();
			int value = (Integer) item.getValue();
			if (value > max) {
				max = value;
				classname = key;
			}
		}
		return classname;
	}

}
