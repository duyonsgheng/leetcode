package custom.code_2022_11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1233
 * @Author Duys
 * @Description
 * @Date 2022/11/28 15:43
 **/
public class LeetCode_1233 {

    public List<String> removeSubfolders(String[] folder) {
        Node root = new Node();
        for (String word : folder) {
            build(root, word);
        }
        // 从root nexts开始遍历
        List<String> ans = new ArrayList<>();
        process(root, ans);
        return ans;
    }

    public void process(Node root, List<String> ans) {
        if (root == null) {
            return;
        }
        for (Node next : root.map.values()) {
            if (next == null) {
                continue;
            }
            if (next.end) {
                ans.add(next.str);
                continue;
            } else {
                process(next, ans);
            }
        }
    }

    public void build(Node root, String word) {
        String[] split = word.split("/");
        Node cur = root;
        for (String c : split) {
            if (!cur.map.containsKey(c)) {
                cur.map.put(c, new Node());
            }
            cur = cur.map.get(c);
        }
        cur.end = true;
        cur.str = word;
    }

    class Node {
        public Map<String, Node> map;
        public boolean end;
        public String str;

        public Node() {
            map = new HashMap<>();
            end = false;
        }
    }

    public static void main(String[] args) {
        String[] folder = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
        LeetCode_1233 lc = new LeetCode_1233();
        List<String> list = lc.removeSubfolders(folder);
        System.out.println(list);
    }
}
