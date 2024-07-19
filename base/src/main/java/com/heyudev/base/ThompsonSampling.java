package com.heyudev.base;

import java.util.Random;

public class ThompsonSampling {
    private static final Random random = new Random();

    // 假设每个选项的收益服从Beta分布
    private static class BetaDistribution {
        private double alpha;
        private double beta;

        public BetaDistribution(double alpha, double beta) {
            this.alpha = alpha;
            this.beta = beta;
        }

        // 从Beta分布中抽取样本
        public double sample() {
            double u = Math.random();
            double v = Math.random();
            double sample = Math.pow(1 - u, beta - 1) * Math.pow(u, alpha - 1) /
                    (1 - Math.pow(1 - u, alpha + beta));
            return sample;
        }
    }

    public static void main(String[] args) {
        int numOptions = 5; // 假设有5个选项
        BetaDistribution[] distributions = new BetaDistribution[numOptions];
        for (int i = 0; i < numOptions; i++) {
            // 初始alpha和beta值，这里使用1.0，表示无偏的先验
            distributions[i] = new BetaDistribution(1.0, 1.0);
        }

        int[] counts = new int[numOptions]; // 记录每个选项被选择的次数
        int[] rewards = new int[numOptions]; // 记录每个选项的总收益

        int totalRounds = 1000; // 进行1000轮选择
        for (int round = 0; round < totalRounds; round++) {
            int bestIndex = 0;
            double bestSample = 0.0;

            // 选择具有最高样本值的选项
            for (int i = 0; i < numOptions; i++) {
                double sample = distributions[i].sample();
                if (i == 0 || sample > bestSample) {
                    bestSample = sample;
                    bestIndex = i;
                }
            }

            // 假设每次选择后获得1的收益，实际应用中可以根据情况修改
            int reward = 1;
            counts[bestIndex]++;
            rewards[bestIndex] += reward;

            // 更新Beta分布的参数
            distributions[bestIndex] = new BetaDistribution(
                    distributions[bestIndex].alpha + reward,
                    distributions[bestIndex].beta + (1 - reward)
            );
        }

        // 输出每个选项的被选择次数和总收益
        for (int i = 0; i < numOptions; i++) {
            System.out.println("Option " + i + " was chosen " + counts[i] + " times and earned " + rewards[i] + " rewards.");
        }
    }
}
