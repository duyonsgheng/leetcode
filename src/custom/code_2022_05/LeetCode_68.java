package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_68
 * @Author Duys
 * @Description
 * @Date 2022/5/10 11:14
 **/
// 68. 文本左右对齐
// 给定一个单词数组words 和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
//
//你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
//要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
//文本的最后一行应为左对齐，且单词之间不插入额外的空格。
//链接：https://leetcode.cn/problems/text-justification
public class LeetCode_68 {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int j = i;
            StringBuilder sb = new StringBuilder();
            int slen = 0;
            int len = 0;
            int count = 0;
            // 去选择单词
            while (j < words.length) {
                if (count == 0) {
                    slen += words[j].length();
                    len += words[j].length();
                    count++;
                } else {
                    // 不是这一行的第一个单词，那么需要再单词后面放空格
                    slen += words[j].length();
                    len += words[j].length() + 1;
                    count++;
                }
                // 校验
                if (len > maxWidth) {
                    count--;
                    slen -= words[j].length();
                    len -= words[i].length() + 1;
                    break;
                } else {
                    j++;
                }
            }
            // 没办法，只有一个单词了，后面需要补空格
            if (count == 1) {
                sb.append(words[i]);
                fill(sb, maxWidth);
            } else {
                // 当前行已有的空格数
                int spaceNum = count - 1;
                // 需要补的总共空格数
                int spaceLen = maxWidth - slen;
                int base = spaceLen / spaceNum;
                int left = spaceLen % spaceNum;
                if (j == words.length) { // 最后一个单词了
                    for (int index = i; index < j; index++) {
                        if (index < j - 1) {
                            // 如果还没到当前行的最后位置。补空格
                            sb.append(words[index]).append(" ");
                        } else {
                            // 当前行的最后位置，不需要补空格
                            sb.append(words[index]);
                        }
                    }
                    fill(sb, maxWidth);
                } else {
                    // 没有到最后的单词
                    for (int index = i; index < j; index++) {
                        if (left-- > 0) { // 如果左边已经有这么多空格了
                            sb.append(words[index]).append(getSpace(base + 1));
                        } else {
                            //细节2 最后一个单词后面无添加空格
                            sb.append(words[index]).append(index == j - 1 ? "" : getSpace(base));
                        }
                    }
                }
            }
            ans.add(sb.toString());
            i = j - 1; // 下一次从已经算完的位置继续
        }
        return ans;
    }

    public static String getSpace(int v) {
        StringBuilder res = new StringBuilder();
        while (v-- > 0) res.append(" ");
        return res.toString();
    }

    public static void fill(StringBuilder builder, int len) {
        while (builder.length() < len) builder.append(" ");
    }

}
