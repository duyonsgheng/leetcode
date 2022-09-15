package custom.code_2022_08;

import java.util.Random;

/**
 * @ClassName LeetCode_478
 * @Author Duys
 * @Description
 * @Date 2022/8/15 13:00
 **/
// 478. 在圆内随机生成点
// 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
public class LeetCode_478 {

    class Solution {

        private double r;
        private double x;
        private double y;
        private Random random;

        public Solution(double radius, double x_center, double y_center) {
            r = radius;
            x = x_center;
            y = y_center;
            random = new Random();
        }

        public double[] randPoint() {
          /*  double next = random.nextDouble();
            double newU = random.nextDouble() * 2 * Math.PI;
            double newR = Math.sqrt(next);
            return new double[]{x + newR * Math.cos(newU) * r, y + newR * Math.sin(newU) * r};*/
            while (true) {
                double newX = random.nextDouble() * 2 * r - r;
                double newY = random.nextDouble() * 2 * r - r;
                if (newX * newX + newY * newY <= r * r) {
                    return new double[]{x + newX, y + newY};
                }
            }
        }
    }
}
