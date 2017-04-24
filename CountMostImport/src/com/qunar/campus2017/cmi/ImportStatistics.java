package com.qunar.campus2017.cmi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leon on 2017/4/24.
 */
public class ImportStatistics {


    private HashMap<String, int[]> importMap = new HashMap<>();


    public int addImport(String strImport) {

        // an int wrapper for better performance when updating counter.
        int[] countWrapper = importMap.get(strImport);

        if (countWrapper == null) {
            importMap.put(strImport, new int[] { 1 });
            return 1;
        } else {
            return ++countWrapper[0];
        }
    }

    public int getImportCount(String strImport) {
        int[] countWrapper = importMap.get(strImport);
        return countWrapper == null ? 0 : countWrapper[0];
    }

    public ArrayList<ImportCountEntry> getMostImport(int topN) {
        FixedSizePriorityQueue<ImportCountEntry> queue = new FixedSizePriorityQueue<>(topN);
        for (Map.Entry<String, int[]> mapEntry: importMap.entrySet()) {
            ImportCountEntry newEntry = new ImportCountEntry(mapEntry.getKey(), mapEntry.getValue()[0]);
            queue.add(newEntry);
        }
        return queue.sortedList();
    }

}
