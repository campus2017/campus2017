package lnu.Util;/*
package lnu.Util;

import lnu.controller.CharacterExtendCompute;
import lnu.pojo.Counter;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * Created by DELL on 2017/4/24.
 *//*

public class File_Operation {
	public Map<String,Object> bufferReadAndWrtie(File file,HttpServletRequest request){
		String fileName = file.getOriginalFilename();	//获取文件名
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/upload");
		CountCharacter countCharacter = new CountCharacter();
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader reader = null;
		BufferedWriter writer = null;
		try {
			reader = new BufferedReader(new InputStreamReader(file.getInputStream()),8*1024*1024);
			writer = new BufferedWriter(new FileWriter(new File(path,fileName),true));
			// writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(path,fileName),true)));
			//os = new FileOutputStream(new File(path,fileName));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				//os.write(buffer, 0, length);
				writer.write(tempString);
				writer.newLine();
				writer.flush();
				stringBuilder.append(tempString);
			}
			CharacterExtendCompute characterExtendCompute = countCharacter.count(stringBuilder.toString());
			Counter counter = characterExtendCompute.getCounter();
			map.put("character",counter.getCharacter());
			map.put("digital",counter.getDigital());
			map.put("chinese",counter.getChinese());
			map.put("punctuation",counter.getPunctuation());
			map.put("topklist",characterExtendCompute.getList());
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
*/


/*
//File filedes = new File("/upload/dest");
			File filedes = new File(path,fileName);
					if(filedes.exists()){
					filedes.delete();
					}
					filedes.createNewFile();
					file.transferTo(filedes);
					long time1 = System.currentTimeMillis();
					System.out.println("写文件时间:"+(time1-starttime)+"毫秒");
					fis = new RandomAccessFile(filedes, "r");
					channel = fis.getChannel();
					long size = channel.size();

					// 构建一个只读的MappedByteBuffer
					MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);

					//RandomAccessFile rafo = new RandomAccessFile(new File("E:/数据/1232.txt"), "rw");
					//FileChannel fco = rafo.getChannel();
					//MappedByteBuffer mbbo = fco.map(FileChannel.MapMode.READ_WRITE, 0, size);
					// 如果文件不大,可以选择一次性读取到数组
					// byte[] all = new byte[(int)size];
					// mappedByteBuffer.get(all, 0, (int)size);
					// 打印文件内容
					// System.out.println(new String(all));

					// 如果文件内容很大,可以循环读取,计算应该读取多少次
					int allocate = 1024;
					byte[] bytes = new byte[allocate];
					long cycle = size / allocate;
					int mode = (int)(size % allocate);
					//byte[] eachBytes = new byte[allocate];
					// int j = 0;
					for (int i = 0; i < cycle; i++) {
		// 每次读取allocate个字节

		mappedByteBuffer.get(bytes);
		stringBuilder.append(new String(bytes));
		//mbbo.put(mappedByteBuffer);
		//for(int j=0;j<bytes.length;j++){
		//	mbbo.put((int) (i*allocate+j),bytes[j]);
		//}
		// 打印文件内容,关闭打印速度会很快
		// System.out.print(new String(bytes));
		}
		if(mode > 0) {
		bytes = new byte[mode];
		mappedByteBuffer.get(bytes);
		stringBuilder.append(new String(bytes));
		//mbbo.put(mappedByteBuffer);
				*/
/*for(int i=0;i<bytes.length;i++){
					mbbo.put((int) (cycle*allocate+i),bytes[i]);
				}*//*

		/*/
/* while(mappedByteBuffer.hasRemaining()){
		//mbbo.put(mappedByteBuffer.get());
		}/*/
/*
// 打印文件内容,关闭打印速度会很快
// System.out.print(new String(bytes));


// 关闭通道和文件流*/
