import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuyin on 16-11-23.
 */
public class FindKthLargest {

    public static List<Integer> findKthNum(Integer[] nums,int k){
        int num = findKthLargest(nums,k);
        List<Integer> list = new ArrayList();
        for(int i:nums){
            if(i>=num)
                list.add(i);
        }
        return list;
    }
    private static int findKthLargest(Integer[] nums, int k) {
        int start = 0, end = nums.length - 1, index = nums.length - k;
        while (start < end) {
            int pivot = partion(nums, start, end);
            if (pivot < index)
                start = pivot + 1;
            else if (pivot > index)
                end = pivot - 1;
            else
                return nums[pivot];
        }
        return nums[start];
    }

    private static int partion(Integer[] nums, int start, int end) {
        int pivot = start, temp;
        while (start <= end) {
            while (start <= end && nums[start] <= nums[pivot])
                start++;
            while (start <= end && nums[end] > nums[pivot])
                end--;
            if (start > end)
                break;
            temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
        }
        temp = nums[end];
        nums[end] = nums[pivot];
        nums[pivot] = temp;
        return end;
    }
}
