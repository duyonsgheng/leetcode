package newClass;

/**
 * @ClassName TowSpiltModel
 * @Author Duys
 * @Description 二分 局部最小问题，相邻不相等
 * @Date 2020/12/23 20:35
 **/
public class TowSpiltModel {

    public int oneMini(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int n = arr.length;
        if (n == 1) {
            return 0;
        }
        if (arr[0] < arr[1]) {
            return 0;
        }
        if (arr[n - 1] < arr[n - 2]) {
            return n - 1;
        }
        int l = 0;
        int r = n - 1;
        // 防止越界
        while (l < r - 1) {
            int mind = (l + r) / 2;
            if (arr[mind] < arr[mind + 1] && arr[mind] < arr[mind - 1]) {
                return mind;
            } else {
                // 如果比左边大，去右边
                if (arr[mind] > arr[mind - 1]) {
                    r = mind - 1;
                } else {
                    l = mind + 1;
                }
                /*if (arr[mind] > arr[mind + 1]) {
                    l = mind + 1;
                }*/
            }
        }
        return arr[l] < arr[r] ? l : r;
    }
}
