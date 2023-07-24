package custom.code_2023_07;

/**
 * @author Mr.Du
 * @ClassName LeetCode_1980
 * @date 2023年07月19日
 */
// 1980. 找出不同的二进制字符串
// https://leetcode.cn/problems/find-unique-binary-string/
public class LeetCode_1980 {
    // 前缀树的思路
    public String findDifferentBinaryString(String[] nums) {
        Node root = new Node();
        build(root, nums);
        int n = nums[0].length();
        StringBuilder sb = new StringBuilder();
        dfs(root, 0, n, sb);
        return sb.reverse().toString();
    }

    public boolean dfs(Node root, int dept, int n, StringBuilder sb) {
        if (dept == n + 1) {
            return false;
        }
        // 如果没有路
        if (root == null) {
            for (int i = 0; i < n - dept; i++) {
                sb.append('0');
            }
            return true;
        }
        // 如果0位置有空的
        if (dfs(root.next[0], dept + 1, n, sb)) {
            sb.append('0');
            return true;
        }
        if (dfs(root.next[1], dept + 1, n, sb)) {
            sb.append('1');
            return true;
        }
        return false;
    }

    public void build(Node root, String[] nums) {
        Node cur = root;
        for (String num : nums) {
            for (char c : num.toCharArray()) {
                if (cur.next[c - '0'] == null) {
                    cur.next[c - '0'] = new Node();
                }
                cur = cur.next[c - '0'];
            }
            cur = root;
        }
    }

    class Node {
        Node[] next = new Node[2];
    }
}
