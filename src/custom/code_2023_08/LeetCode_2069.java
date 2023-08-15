package custom.code_2023_08;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2069
 * @date 2023年08月08日
 */
// 2069. 模拟行走机器人 II
// https://leetcode.cn/problems/walking-robot-simulation-ii/
public class LeetCode_2069 {
    public class Robot {
        private final static int EAST = 0;
        private final static int NORTH = 1;
        private final static int WEST = 2;
        private final static int SOUTH = 3;
        int dir, x, y, width, height, s;

        public Robot(int width, int height) {
            dir = EAST;
            x = 0;
            y = 0;
            this.width = width;
            this.height = height;
            this.s = 2 * width + 2 * height - 4;
        }

        public void step(int num) {
            if (num >= s) {
                num %= s; // 还余下num 步数需要走，并且来到0点的时候，方向是向下的
                //注意再次回到元点时，方向是向南
                if (x == 0 && y == 0) {
                    dir = SOUTH;
                }
            }
            while (num > 0) {
                if (dir == EAST && x < width - 1) {
                    x++;
                    --num;
                } else if (dir == NORTH && y < height - 1) {
                    y++;
                    --num;
                } else if (dir == WEST && x > 0) {
                    x--;
                    --num;
                } else if (dir == SOUTH && y > 0) {
                    y--;
                    --num;
                } else {
                    dir = (dir + 1) % 4;
                }
            }
        }

        public int[] getPos() {
            return new int[]{x, y};
        }

        public String getDir() {
            switch (dir) {
                case 0:
                    return "East";
                case 1:
                    return "North";
                case 2:
                    return "West";
                case 3:
                    return "South";
            }
            return "East";
        }
    }
}
