package com.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountMostImport {

	/**
	 * @param args
	 */
	String path = new String();
	List<File> allFiles = new ArrayList<File>();
	private static String CUSTOM_PATH = "./";
	private static String STATIC_PROCESS_STRING = "import";
	private static int INITIALIZE_NUM = 1;
	private static int IMPORT_MAXNUM = 0;
	private String maxImport = new String();
	List<String> content = new ArrayList<String>();
	String importNumMax = new String();
	public CountMostImport(String path) {
		this.path = path;
	}

	public CountMostImport() {
		this.path = CUSTOM_PATH;// 默认当前文件夹
	}

	public void processContent(List<String> waitForProcessContent) {
		record myRecord = new record();
		String[] record = StringListToArray(waitForProcessContent);
		// String regex=" (\b)*;";
		Pattern p = Pattern.compile("(?<= ).*(?=;)");
		for (int i = 0; i < record.length; i++) {
			if (record[i].contains(STATIC_PROCESS_STRING)) {
				// System.out.println(record[i]);
				Matcher m = p.matcher(record[i]);
				while (m.find()) {
					if (myRecord.importNumMax.get(m.group()) == null) {
						myRecord.importNumMax.put(m.group(), INITIALIZE_NUM);
					} else {
						int num = myRecord.importNumMax.get(m.group()) + 1;
						if (num > IMPORT_MAXNUM) {
							maxImport = m.group();
						}
						myRecord.importNumMax.put(m.group(), num);
						System.out.println("统计:"+num);
						System.out.println(m.group());
					}
					// int s=myRecord.importNumMax.get(m.group());
					// myRecord.importNumMax.put((m.group(),importNumMax.hashCode()++);
					// System.out.println(m.group());
					// System.out.println(myRecord.importNumMax.get(m.group()));
				}
			}
		}
	}

	public void processContent() {
		processContent(this.content);

	}

	public void getContentFromFile(File... files) throws Exception {
		File singleFile;
		for (int i = 0; i < files.length; i++) {
			singleFile = files[i];
			getContentFromFile(singleFile);
		}
	}

	public void getContentFromFile(File file) throws Exception {
		InputStream in = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		// List<String> filecontent =new ArrayList<String>();
		String line = "";
		while ((line = br.readLine()) != null) {// 从文本中读取文件
		// filecontent.add(line);
			this.content.add(line);
			// System.out.print(line);
			// System.out.print("\r\n");
		}

		if (br != null) {
			br.close();
		}

	}

	public void getContentFromFile(List<File> listfiles) throws Exception {
		File singleFile;
		for (int i = 0; i < listfiles.size(); i++) {
			singleFile = listfiles.get(i);
			getContentFromFile(singleFile);
		}
	}

	public void getContentFromFile() throws Exception {
		// File []files=fileListToArray(this.allFiles);
		getContentFromFile(this.allFiles);
	}

	public File[] fileListToArray(List<File> listFiles) {
		// File[] aFiles=(File[])T.allFiles.toArray(new
		// File[T.allFiles.size()]);

		File[] arrayFiles = new File[listFiles.size()];
		for (int i = 0; i < listFiles.size(); i++) {
			arrayFiles[i] = listFiles.get(i);
		}
		return arrayFiles;
	}

	public String[] StringListToArray(List<String> listStings) {
		String[] arrayFiles = new String[listStings.size()];
		for (int i = 0; i < listStings.size(); i++) {
			arrayFiles[i] = listStings.get(i);
		}
		return arrayFiles;
	}

	public void getFilesFromPath(String path) {
		File f = new File(path);
		File[] files = f.listFiles();
		// List<File> listfiles=new ArrayList<File>();
		for (int i = 0; i < files.length; i++) {
			if (!files[i].isDirectory()) {
				this.allFiles.add(files[i]);
			} else {
				getFilesFromPath(files[i].toString());
			}
			// System.out.print(files[i]);
			// System.out.print("\n");
		}
	}

	public void getFilesFromPath() {
		getFilesFromPath(this.path);
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		CountMostImport T = new CountMostImport();
		// String fileName="test.txt";
		// String path = "d:/tr/rt";
		// File f = new File(path);
		// File file = new File(f,fileName);
		T.getFilesFromPath();
		// T.content=T.getContentFromFile(T.fileListToArray(T.allFiles));
		T.getContentFromFile();
		// for(int i=0;i<T.allFiles.size();i++){
		// System.out.println(T.allFiles.get(i));
		// }
		// for(int i=0;i<T.content.size();i++){
		// System.out.println(T.content.get(i));
		// }

		T.processContent();
		System.out.print(T.maxImport);
	}

}