import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class CountMostImport {

	public static Map<String, Integer> map = new HashMap<>();

	public static void main(String[] args) {
		String path = "/home/mml/桌面/3FISH/app/src/main/java/com/android_threefishes/threefish/a3fish";
		File file = new File(path);
		File[] filelist = file.listFiles();
		for (int i = 0; i < filelist.length; i++) {
			if (filelist[i].isFile()) {
				readFile(filelist[i]);
			}
			if (filelist[i].isDirectory()) {
				File[] filelist2 = filelist[i].listFiles();
				for (int j = 0; j < filelist2.length; j++) {
					readFile(filelist2[i]);
				}
			}
		}
		getResult(map);
	}

	private static void readFile(File file) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("import")) {
					line = line.replace("import", "").trim();
					storeValue(line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void storeValue(String line) {
		int value = 0;
		if (map.get(line) == null) {
			map.put(line, 1);
		} else {
			value = map.get(line) + 1;
			map.put(line, value);
		}
	}

	/**
	 * 将存储后map转为list按value进行排序
	 */
	private static void getResult(Map<String, Integer> map) {
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {			
				return o2.getValue() - o1.getValue();
			}
		});
		Map.Entry<String, Integer> entry = null;
		for (int i = 0; i < 10; i++) {
			entry = list.get(i);
			System.out.println(entry.getKey() + "------" + entry.getValue()+"次");
		}
	}

}
