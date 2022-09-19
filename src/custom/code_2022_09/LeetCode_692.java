package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @ClassName LeeCode_692
 * @Author Duys
 * @Description
 * @Date 2022/9/8 15:51
 **/
// 692. 前K个高频单词
public class LeetCode_692 {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap<>();
        for (String str : words) {
            count.put(str, count.getOrDefault(str, 0) + 1);
        }
        PriorityQueue<Node> queue = new PriorityQueue<>((a, b)
                -> a.time != b.time ? b.time - a.time : a.word.compareTo(b.word));
        for (String key : count.keySet()) {
            queue.offer(new Node(key, count.get(key)));
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (!queue.isEmpty()) {
                ans.add(queue.poll().word);
            }
        }
        return ans;
    }

    class Node {
        String word;
        int time;

        public Node(String w, int t) {
            word = w;
            time = t;
        }
    }

}
