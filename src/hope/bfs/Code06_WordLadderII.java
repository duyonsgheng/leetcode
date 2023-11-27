package hope.bfs;

import java.util.*;

/**
 * @author Mr.Du
 * @ClassName Code06_WordLadderII
 * @date 2023年11月23日 11:56
 */
public class Code06_WordLadderII {
    //单词表
    public static HashSet<String> dist;
    public static HashSet<String> curLevel = new HashSet<>();
    public static HashSet<String> nextLevel = new HashSet<>();
    // 反向图
    public static HashMap<String, ArrayList<String>> graph = new HashMap<>();

    // 记录路径，当有一条有效路径的时候，拷贝到ans中去
    public static LinkedList<String> path = new LinkedList<>();

    public static List<List<String>> ans = new ArrayList<>();

    public static void build(List<String> wordList) {
        dist = new HashSet<>(wordList);
        graph.clear();
        ans.clear();
        curLevel.clear();
        nextLevel.clear();
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        build(wordList);
        if (!dist.contains(endWord)) {
            return ans;
        }
        if (bfs(beginWord, endWord)) {
            dfs(beginWord, endWord);
        }
        return ans;
    }

    public static boolean bfs(String begin, String end) {
        boolean find = false;
        curLevel.add(begin);
        while (!curLevel.isEmpty()) {
            dist.removeAll(curLevel);
            for (String cur : curLevel) {
                // cur中每一个位置 a-z都试一遍
                char[] ws = cur.toCharArray();
                for (int i = 0; i < ws.length; i++) {
                    char old = ws[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        ws[i] = c;
                        String n = String.valueOf(ws);
                        if (dist.contains(n) && !n.equals(cur)) {
                            if (n.equals(end)) {
                                find = true;
                            }
                            graph.putIfAbsent(n, new ArrayList<>());
                            graph.get(n).add(cur);
                            nextLevel.add(n);
                        }
                    }
                    ws[i] = old;
                }
            }
            if (find) {
                return true;
            } else {
                HashSet<String> tmp = curLevel;
                curLevel = nextLevel;
                nextLevel = tmp;
                nextLevel.clear();
            }
        }
        return false;
    }

    public static void dfs(String word, String aim) {
        path.addFirst(word);
        if (word.equals(aim)) {
            ans.add(new ArrayList<>(path));
        } else if (graph.containsKey(word)) {
            for (String next : graph.get(word)) {
                dfs(next, aim);
            }
        }
        path.removeFirst();
    }
}
