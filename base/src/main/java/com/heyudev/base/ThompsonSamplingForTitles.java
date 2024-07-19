package com.heyudev.base;

import java.util.Random;

public class ThompsonSamplingForTitles {
    public static void main(String[] args) {
        // ...（省略BetaDistribution类定义）...

        String[] titles = {
                "Video Title 1",
                "Video Title 2",
                "Video Title 3",
                // ... 更多标题
        };
        BetaDistribution[] distributions = new BetaDistribution[titles.length];
        for (int i = 0; i < titles.length; i++) {
            distributions[i] = new BetaDistribution(1, 1);
        }

        int totalRounds = 1000; // 总轮数
        for (int round = 0; round < totalRounds; round++) {
            double maxSample = 0;
            int chosenIndex = 0;

            for (int i = 0; i < titles.length; i++) {
                double sample = distributions[i].sample();
                if (i == 0 || sample > maxSample) {
                    maxSample = sample;
                    chosenIndex = i;
                }
            }

            // 假设获得的奖励是1，表示用户点击了标题
            int reward = 1;
            distributions[chosenIndex].update(reward);
        }

        for (int i = 0; i < titles.length; i++) {
            System.out.println("Title: " + titles[i]);
            System.out.println("Alpha: " + distributions[i].getAlpha());
            System.out.println("Beta: " + distributions[i].getBeta());
            System.out.println("Estimated Click-Through Rate (CTR): " + (distributions[i].getAlpha() / (distributions[i].getAlpha() + distributions[i].getBeta())));
        }
    }
}

class BetaDistribution {
    private final Random random = new Random();
    private double alpha; // 点击次数
    private double beta;  // 未点击次数

    public BetaDistribution(double alpha, double beta) {
        this.alpha = alpha;
        this.beta = beta;
        System.out.println("Handler A 处理请求");
//        if (next != null) {
//            next.handleRequest(request);
//        }
    }

    public double sample() {
        double u1 = random.nextDouble();
        double u2 = random.nextDouble();
        return u1 / (u1 + u2);
    }

    public void update(int reward) {
        if (reward == 1) {
            alpha += 1;
        } else {
            beta += 1;
        }
    }

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    public double getBeta() {
        return beta;
    }

    public void setBeta(double beta) {
        this.beta = beta;
    }
}