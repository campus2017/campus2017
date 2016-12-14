package wz;

/**
 * ResultEntity 结果实体类
 *
 * @author wz
 * @date 16/11/16 22:59
 */
public class ResultEntity implements Comparable<ResultEntity> {

    /**
     * 类名
     */
    private String className;

    /**
     * 被import的次数
     */
    private int importedCount;

    public ResultEntity(String className, int importedCount) {
        this.className = className;
        this.importedCount = importedCount;
    }

    public String getClassName() {
        return className;
    }

    public int getImportedCount() {
        return importedCount;
    }

    public int compareTo(ResultEntity o) {
        if (this.importedCount == o.getImportedCount())
            return o.getClassName().compareTo(this.className);

        return this.importedCount - o.getImportedCount();
    }
}
