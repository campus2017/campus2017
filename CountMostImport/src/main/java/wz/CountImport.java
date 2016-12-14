package wz;

import java.io.*;
import java.util.*;
import java.util.concurrent.Callable;

/**
 * CountImport
 *
 * 统计所有被import的次数排前k个的类
 * 若次数相同，则按字典序
 *
 * @author wz
 * @date 16/11/16 20:54
 */
public class CountImport implements Callable<List<ResultEntity>> {


    /**
     * 被包含的类出现的次数
     */
    private HashMap<String, Integer> countMap;

    /**
     * 被import次数前k个类
     */
    private int k;
    private volatile boolean stop;
    private List<File> javaFiles;


    public CountImport(List<File> javaFiles, int k) {
        this.k = k;
        this.stop = false;
        this.javaFiles = javaFiles;
        this.countMap = new HashMap<String, Integer>();
    }

    public List<ResultEntity> call() throws Exception {
        File file;
        while (!stop || javaFiles.size() > 0) {
            if (javaFiles.size() > 0) {
                synchronized (javaFiles) {
                    while (javaFiles.size() > 0) {
                        file = javaFiles.remove(0);
                        parseFile(file);
                    }
                }
            }
        }
        List<ResultEntity> res = topK();
        heapSort(res);
        Collections.reverse(res);
        return res;
    }

    /**
     * 解析java文件，统计被包含类出现次数
     *
     * @param file 待解析文件
     */
    private void parseFile(File file) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));
            String line, importName;

            // System.err.println("正在解析"+file.getAbsolutePath());

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("import ")) {
                    importName = line.split(" +")[1].replace(";", "");
                    if (importName.endsWith("*")) {
                        Set<String> set = PackageUtils.getClassName(importName.replace(".*", ""), false);
                        for (String className : set) {
                            if (countMap.get(className) == null)
                                countMap.put(className, 1);
                            else
                                countMap.put(className, countMap.get(className) + 1);
                        }
                        continue;
                    }
                    if (countMap.get(importName) == null)
                        countMap.put(importName, 1);
                    else
                        countMap.put(importName, countMap.get(importName) + 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 求被import次数 top K 的类,次数相同则按字典序
     *
     * @return top K 类
     */
    private List<ResultEntity> topK() {
        List<ResultEntity> heap = new ArrayList<ResultEntity>(k);

        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            ResultEntity resultEntity = new ResultEntity(entry.getKey(), entry.getValue());
            if (heap.size() < k) {
                heap.add(resultEntity);
                //建小顶堆
                if (heap.size() == k)
                    for (int i = k/2 -1;i >= 0;i--)
                        heapAdjust(heap, i,k-1);
                continue;
            }
            if (resultEntity.compareTo(heap.get(0)) > 0) {
                //替换堆顶元素，并调整堆
                heap.set(0, resultEntity);
                heapAdjust(heap, 0, k-1);
            }
        }
        return heap;
    }

    /**
     * 调整堆为小顶堆
     * @param heap 堆
     * @param start 起始位置
     * @param end 结束位置(包含)
     */
    public void heapAdjust(List<ResultEntity> heap, int start, int end) {
        ResultEntity temp = heap.get(start);
        int index = start;
        for (int i = (start + 1) * 2 - 1; i <= end; i = (i + 1) * 2 - 1) {
            if (i + 1 <= end &&  heap.get(i).compareTo(heap.get(i+1)) < 0)
                i++;
            if (temp.compareTo(heap.get(i)) > 0)
                break;
            heap.set(index, heap.get(i));
            index = i;
        }
        heap.set(index, temp);
    }

    /**
     * 堆排序
     * @param array 待排序列
     */
    public void heapSort(List<ResultEntity> array){
        for (int i = array.size()/2 -1;i >= 0;i--)
            heapAdjust(array, i,array.size()-1);

        for (int i = array.size()-1;i>=0;i--){
            swap(array, 0, i);
            heapAdjust(array , 0,i-1);
        }
    }

    private void swap(List<ResultEntity> array, int i, int j) {
        ResultEntity temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }


    public void stop() {
        stop = true;
    }


}

