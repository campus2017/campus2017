package lnu.controller;

import lnu.Util.CountCharacter;
import lnu.pojo.Counter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("counter")
public class CounterController {

	/**
	 * 以文件上传方式统计字符
	 * @param file
	 * @param request
	 * @return
	 */
	@RequestMapping("/counterByTypeOfFileUpload")
	@ResponseBody
	public Map<String,Object> counterByTypeOfFileUpload(@RequestParam("file")CommonsMultipartFile file,
														HttpServletRequest request,
														Model model) throws IOException{
		long starttime = System.currentTimeMillis();
		String fileName = file.getOriginalFilename();	//获取文件名
		@SuppressWarnings("deprecation")
		String path = request.getRealPath("/upload");
		CountCharacter countCharacter = new CountCharacter();
		Map<String,Object> map = new HashMap<String,Object>();
		StringBuilder stringBuilder = new StringBuilder();
		//BufferedReader方式
		//BufferedReader reader = null;
		//BufferedWriter writer = null;
		RandomAccessFile fis = null;
		FileChannel channel = null;
		MappedByteBuffer mappedByteBuffer = null;
		try {



		//	File filedes = new File("/upload/dest");
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
			mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
			int allocate = 1024;
			byte[] bytes = new byte[allocate];
			long cycle = size / allocate;
			int mode = (int)(size % allocate);
			for (int i = 0; i < cycle; i++) {
				mappedByteBuffer.get(bytes);
				stringBuilder.append(new String(bytes));
			}
			if(mode > 0) {
				bytes = new byte[mode];
				mappedByteBuffer.get(bytes);
				stringBuilder.append(new String(bytes));
			}

			//BufferedReader方式
			/*reader = new BufferedReader(new InputStreamReader(file.getInputStream()),5*1024*1024);
			File filedest = new File(path,fileName);
			if(filedest.exists()){
				filedest.delete();
			}
			filedest.createNewFile();
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
			}*/

			System.out.println("读写文件时间:"+(System.currentTimeMillis()-starttime)+"毫秒");
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
				//reader.close();
				//writer.close();
				channel.close();
				fis.close();
				mappedByteBuffer = null;
				System.gc();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("运行时间:"+(System.currentTimeMillis()-starttime)+"毫秒");
		return map;
	}
	
	/**
	 * 以文本输入方式统计字符
	 * @param request
	 * @return
	 */
	@RequestMapping("/counterByTypeOfTxtInput")
	@ResponseBody
	public Map<String,Object> counterByTypeOfTxtInput(HttpServletRequest request,Model model){
		CountCharacter countCharacter = new CountCharacter();
		Map<String,Object> map = new HashMap<String,Object>();
		String param = request.getParameter("txtContent");//获得前台输入,进行字符计算
		System.out.println(param);

		CharacterExtendCompute characterExtendCompute =  countCharacter.count(param);
		Counter counter = characterExtendCompute.getCounter();


		map.put("character",counter.getCharacter());
		map.put("digital",counter.getDigital());
		map.put("chinese",counter.getChinese());
		map.put("punctuation",counter.getPunctuation());
		map.put("topklist",characterExtendCompute.getList());
		return map;
	}
}
