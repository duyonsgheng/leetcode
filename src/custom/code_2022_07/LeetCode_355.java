package custom.code_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_355
 * @Author Duys
 * @Description
 * @Date 2022/7/20 9:30
 **/
// 355. 设计推特
// 设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近 10 条推文。
public class LeetCode_355 {

    // 哈希表 + 链表
    class Twitter {
        // 推文的限制
        private int max;
        // 推文发送的时间
        private int time;
        // 推文发送的时间
        private Map<Integer, Integer> tweeTimes;
        // 用户信息
        private Map<Integer, Node> users;

        public Twitter() {
            time = 0;
            max = 10;
            tweeTimes = new HashMap<>();
            users = new HashMap<>();
        }

        public void init(int userId) {
            users.put(userId, new Node());
        }

        public void postTweet(int userId, int tweetId) {
            if (!users.containsKey(userId)) {
                users.put(userId, new Node());
            }
            // 是否达到上限了,要剔除最老的文章
            if (users.get(userId).tweetIds.size() == max) {
                users.get(userId).tweetIds.remove(max - 1);
            }
            // 把最新的加到最前面
            users.get(userId).tweetIds.addFirst(tweetId);
            tweeTimes.put(tweetId, ++time);
        }

        public List<Integer> getNewsFeed(int userId) {
            LinkedList<Integer> ans = new LinkedList<>();
            for (int i : users.getOrDefault(userId, new Node()).tweetIds) {
                ans.addLast(i);// 把自己的先放进去
            }
            // 关注的人的推文
            for (int foll : users.getOrDefault(userId, new Node()).followeeIds) {
                if (foll == userId) {
                    continue;
                }
                LinkedList<Integer> res = new LinkedList<>();
                int size = users.get(foll).tweetIds.size();
                int i = 0;
                int j = 0;
                int cur = -1;
                Iterator<Integer> iterator = users.get(foll).tweetIds.iterator();
                if (j < size) {
                    cur = iterator.next();
                    while (i < ans.size() && j < size) {
                        // 如果关注的人有最新的消息
                        if (tweeTimes.get(cur) > tweeTimes.get(ans.get(i))) {
                            res.addLast(cur);
                            j++;
                            if (iterator.hasNext()) {
                                cur = iterator.next();
                            }
                        } else {
                            res.addLast(ans.get(i));
                            i++;
                        }
                        if (res.size() == max) {
                            break;
                        }
                    }
                }
                // 把剩下的搞定
                for (; i < ans.size() && res.size() < max; i++) {
                    res.addLast(ans.get(i));
                }
                // 如果自己的没有了，但是好友的还有
                if (j < size && res.size() < max) {
                    res.addLast(cur);
                    for (; iterator.hasNext() && res.size() < max; ) {
                        res.addLast(iterator.next());
                    }
                }
                ans = new LinkedList<>(res);
            }
            return ans;
        }

        public void follow(int followerId, int followeeId) {
            if (!users.containsKey(followerId)) {
                init(followerId);
            }
            if (!users.containsKey(followeeId)) {
                init(followeeId);
            }
            users.get(followerId).followeeIds.add(followeeId);
        }

        public void unfollow(int followerId, int followeeId) {
            users.getOrDefault(followerId, new Node()).followeeIds.remove(followeeId);
        }

        private class Node {
            // 自己关注的人
            private Set<Integer> followeeIds;
            // 存储自己的推文
            private LinkedList<Integer> tweetIds;

            public Node() {
                followeeIds = new HashSet<>();
                tweetIds = new LinkedList<>();
            }
        }
    }

    // 这个设计有一个缺陷，就是每个用户添加的文章不能控制，可以无限的添加下去，如果调用次数多了，内存会炸
    class Twitter_1 {
        // 用户关系
        private Map<Integer, Set<Integer>> follower;
        // 不管谁发的文章，都让最新的再最前面
        private List<int[]> news;

        public Twitter_1() {
            news = new ArrayList<>();
            follower = new HashMap<>();
        }

        public void postTweet(int userId, int tweetId) {
            HashMap<Integer, Integer> twitter = new HashMap<>();
            twitter.put(userId, tweetId);
            // 每次添加最新得都在这儿了，遍历得时候也很方便
            news.add(0, new int[]{userId, tweetId});
        }

        public List<Integer> getNewsFeed(int userId) {
            Set<Integer> followers = follower.getOrDefault(userId, new HashSet<>());
            followers.add(userId);
            ArrayList<Integer> res = new ArrayList<>();
            int number = 0;
            for (int[] new1 : news) {
                if (followers.contains(new1[0])) {
                    res.add(new1[1]);
                    number++;
                }
                if (number == 10) {
                    break;
                }
            }
            return res;
        }

        public void follow(int followerId, int followeeId) {
            Set<Integer> myFollowers = follower.getOrDefault(followerId, new HashSet<>());
            myFollowers.add(followeeId);
            follower.put(followerId, myFollowers);
        }

        public void unfollow(int followerId, int followeeId) {
            Set<Integer> myFollowers = follower.getOrDefault(followerId, new HashSet<>());
            myFollowers.remove(followeeId);
            follower.put(followerId, myFollowers);
        }
    }
}
