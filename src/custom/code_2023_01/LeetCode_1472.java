package custom.code_2023_01;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1472
 * @Author Duys
 * @Description
 * @Date 2023/1/31 17:54
 **/
// 1472. 设计浏览器历史记录
public class LeetCode_1472 {
    static class BrowserHistory {
        String[] urls;
        int cur; // 当前来到的位置
        int p; //

        public BrowserHistory(String homepage) {
            urls = new String[5000];
            cur = -1;
            visit(homepage);
        }

        public void visit(String url) {
            urls[++cur] = url;
            p = cur;
        }

        public String back(int steps) {
            cur = Math.max(0, cur - steps);
            return urls[cur];
        }

        public String forward(int steps) {
            cur = Math.min(p, cur + steps);
            return urls[cur];
        }
    }

    public static void main(String[] args) {
        BrowserHistory browserHistory = new BrowserHistory("leetcode.com");
        browserHistory.visit("google.com");       // 你原本在浏览 "leetcode.com" 。访问 "google.com"
        browserHistory.visit("facebook.com");     // 你原本在浏览 "google.com" 。访问 "facebook.com"
        browserHistory.visit("youtube.com");      // 你原本在浏览 "facebook.com" 。访问 "youtube.com"
        System.out.println(browserHistory.back(1));                  // 你原本在浏览 "youtube.com" ，后退到 "facebook.com" 并返回 "facebook.com"
        System.out.println(browserHistory.back(1));                   // 你原本在浏览 "facebook.com" ，后退到 "google.com" 并返回 "google.com"
        System.out.println(browserHistory.forward(1));                // 你原本在浏览 "google.com" ，前进到 "facebook.com" 并返回 "facebook.com"
        browserHistory.visit("linkedin.com");     // 你原本在浏览 "facebook.com" 。 访问 "linkedin.com"
        System.out.println(browserHistory.forward(2));                // 你原本在浏览 "linkedin.com" ，你无法前进任何步数。
        System.out.println(browserHistory.back(2));                  // 你原本在浏览 "linkedin.com" ，后退两步依次先到 "facebook.com" ，然后到 "google.com" ，并返回 "google.com"
        System.out.println(browserHistory.back(7));                  // 你原本在浏览 "google.com"， 你只能后退一步到 "leetcode.com" ，并返回 "leetcode.com"

    }

}
