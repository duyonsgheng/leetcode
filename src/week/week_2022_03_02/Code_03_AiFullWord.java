package week.week_2022_03_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ClassName Code_03_AiFullWord
 * @Author Duys
 * @Description
 * @Date 2022/3/18 17:28
 **/
public class Code_03_AiFullWord {
// 来自字节飞书团队
// 语法补全功能，比如"as soon as possible"
// 当我们识别到"as soon as"时, 基本即可判定用户需要键入"possible"
// 设计一个统计词频的模型，用于这个功能
// 类似(prefix, next word)这样的二元组
// 比如一个上面的句子"as soon as possible"
// 有产生如下的二元组(as, soon, 1)、(as soon, as, 1)、(as soon as, possible, 1)
// 意思是这一个句子产生了如下的统计：
// 当前缀为"as"，接下来的单词是"soon"，有了1个期望点
// 当前缀为"as soon"，接下来的单词是"as"，有了1个期望点
// 当前缀为"as soon as"，接下来的单词是"possible"，有了1个期望点
// 那么如果给你很多的句子，当然就可以产生很多的期望点，同一个前缀下，同一个next word的期望点可以累加
// 现在给你n个句子，让你来建立统计
// 然后给你m个句子，作为查询
// 最后给你k，表示每个句子作为前缀的情况下，词频排在前k名的联想
// 返回m个结果，每个结果最多k个单词

    // 前缀树
    public static class TrieNode {
        public String word;
        public int times;
        public HashMap<String, TrieNode> nextNodes;
        public TreeSet<TrieNode> nextRanks;

        public TrieNode(String wo) {
            word = wo;
            times = 1;
            nextNodes = new HashMap<>();
            // 按照次数出现多的排前面，次数一样的根据字典序排列
            nextRanks = new TreeSet<>((a, b) -> a.times != b.times ? (b.times - a.times) : a.word.compareTo(b.word));
        }
    }

    public static class AI {
        public TrieNode root;
        public int topK;

        public AI(List<String> sentences, int k) {
            root = new TrieNode("");
            topK = k;
            for (String wo : sentences) {
                full(wo);
            }
        }

        public void full(String sentence) {
            TrieNode cur = root;
            TrieNode next = null;
            for (String word : sentence.split(" ")) {
                // 如果我得下级没有
                if (!cur.nextNodes.containsKey(word)) {
                    next = new TrieNode(word);
                    cur.nextNodes.put(word, next);
                    cur.nextRanks.add(next);
                } else {
                    next = cur.nextNodes.get(word);
                    cur.nextRanks.remove(next);
                    next.times++;
                    cur.nextRanks.add(next);
                }
                cur = next;
            }
        }

        // 返回前k个
        public List<String> suggest(String sentence) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (String wo : sentence.split(" ")) {
                if (!cur.nextNodes.containsKey(wo)) {
                    return ans;
                } else {
                    cur = cur.nextNodes.get(wo);
                }
            }
            for (TrieNode n : cur.nextRanks) {
                ans.add(n.word);
                if (ans.size() == topK) {
                    break;
                }
            }
            return ans;
        }
    }
}
