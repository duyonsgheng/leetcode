package duys.class_06_08;

/**
 * @ClassName CountSort
 * @Author Duys
 * @Description TODO
 * @Date 2021/6/8 16:46
 **/
public class CountSort {

    public static void countSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        // 找出数组中最大的元素
        int max = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
        }
        // 创建一个捅
        int[] bucket = new int[max + 1];
        // 把原数组上的元素，在桶上进行计数
        for (int i = 0; i < arr.length; i++) {
            bucket[arr[i]]++;
        }
        //
        int k = 0;
        for (int i = 0; i < bucket.length; i++) {
            while (bucket[i]-- > 0) {
                arr[k++] = i;
            }
        }

    }
}
