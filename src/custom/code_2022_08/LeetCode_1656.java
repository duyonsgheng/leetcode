package custom.code_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1656
 * @Author Duys
 * @Description
 * @Date 2022/8/16 10:40
 **/
public class LeetCode_1656 {
    public static void main(String[] args) {
        OrderedStream orderedStream = new OrderedStream(5);
        List<String> ccccc = orderedStream.insert(3, "ccccc");
        List<String> aaaaa = orderedStream.insert(1, "aaaaa");
        List<String> bbbbb = orderedStream.insert(2, "bbbbb");
        List<String> ddddd = orderedStream.insert(5, "eeeee");
        List<String> eeeee = orderedStream.insert(4, "eeeee");
    }

    static class OrderedStream {
        private String[] arr;
        private boolean[] visited;
        private int index;
        private int size;

        public OrderedStream(int n) {
            arr = new String[n];
            visited = new boolean[n];
            size = n;
            index = 0;
        }

        /**
         * [] [] [] [] []
         * index =0
         */
        public List<String> insert(int idKey, String value) {
            List<String> ans = new ArrayList<>();
            if (idKey > size) {
                return ans;
            }
            arr[idKey - 1] = value;
            visited[idKey - 1] = true;
            int pre = index;
            if (index == idKey - 1) {
                index++;
            }
            while (index < size && visited[index]) {
                index++;
            }
            if (pre == index) {
                return ans;
            } else {
                for (int i = pre; i < index; i++) {
                    ans.add(arr[i]);
                }
                return ans;
            }
        }
    }
}
