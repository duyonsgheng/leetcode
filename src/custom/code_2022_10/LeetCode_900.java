package custom.code_2022_10;

/**
 * @ClassName LeetCode_900
 * @Author Duys
 * @Description
 * @Date 2022/10/12 13:32
 **/
public class LeetCode_900 {

    public static void main(String[] args) {
        int[] arr = {3, 8, 0, 9, 2, 5};
        RLEIterator rleIterator = new RLEIterator(arr);
        System.out.println(rleIterator.next(2));
    }

    static class RLEIterator {

        int[] arr;
        int index;
        int q;

        public RLEIterator(int[] encoding) {
            arr = encoding;
            index = q = 0;
        }

        public int next(int n) {
            while (index < arr.length) {
                // 如果当前元素的次数和要被删除的次数大于了实际的次数
                if (q + n > arr[index]) { // 不够删除的了
                    n -= arr[index] - q; // 耗费的n减去，然后取下一个位置继续
                    q = 0;
                    index += 2;
                } else {
                    q += n;
                    return arr[index + 1];
                }
            }
            return -1;
        }
    }
}
