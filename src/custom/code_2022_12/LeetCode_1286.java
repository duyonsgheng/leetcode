package custom.code_2022_12;

import java.util.PriorityQueue;

/**
 * @ClassName LeetCode_1286
 * @Author Duys
 * @Description
 * @Date 2022/12/1 16:30
 **/
// 1286. 字母组合迭代器
public class LeetCode_1286 {
     class CombinationIterator {
        // abcdef  -2
        // ab ac bc
        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> a.compareTo(b));
        StringBuffer buffer = new StringBuffer();

        public CombinationIterator(String characters, int combinationLength) {
            build(characters, combinationLength, 0);
        }

        public String next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return queue.isEmpty();
        }

        private void build(String str, int limit, int index) {
            if (buffer.length() == limit) {
                queue.add(buffer.toString());
            }
            for (int i = index; i < str.length(); i++) {
                buffer.append(str.charAt(i));
                build(str, limit, i + 1);
                buffer.deleteCharAt(buffer.length() - 1);
            }
        }
    }

}
