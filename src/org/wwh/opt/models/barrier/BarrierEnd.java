/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wwh.opt.models.barrier;

import org.wwh.opt.models.common.Common;

/**
 *
 * @author wenhua
 */
public final class BarrierEnd {

    public static double[] calc(
            int indexType,
            int indexPutCall,
            double S,
            double X,
            double H,
            double T,
            double r,
            double v,
            double D,
            double t) {
        double price = partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v);
        double delta = (1.0D / (S * 0.01D)) * (partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v) - partialBarrier(indexType, indexPutCall, S - S * 0.01D, X, H, t, T, r, r - D, v));
        double gamma = (1.0D / (S * 0.01D * (S * 0.01D))) * ((partialBarrier(indexType, indexPutCall, S + 0.01D * S, X, H, t, T, r, r - D, v) - 2D * partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v)) + partialBarrier(indexType, indexPutCall, S - S * 0.01D, X, H, t, T, r, r - D, v));
        double vega = (1.0D / (v * 0.01D)) * (partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v) - partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v - v * 0.01D));

        return new double[]{price, delta, gamma, vega};
    }

    public static double partialBarrier(int i, int j, double d, double d1, double d2, double d3, double d4, double d5,
            double d6, double d7) {
        double val = 0.0D;
        double d8 = (Math.log(d / d1) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d9 = d8 - d7 * Math.sqrt(d4);
        double d10 = (Math.log(d / d1) + 2D * Math.log(d2 / d) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d11 = d10 - d7 * Math.sqrt(d4);
        double d12 = (Math.log(d / d2) + (d6 + (d7 * d7) / 2D) * d3) / (d7 * Math.sqrt(d3));
        double d13 = d12 - d7 * Math.sqrt(d3);
        double d14 = d12 + (2D * Math.log(d2 / d)) / (d7 * Math.sqrt(d3));
        double d15 = d14 - d7 * Math.sqrt(d3);
        double d20 = (d6 - (d7 * d7) / 2D) / (d7 * d7);
        double d21 = Math.sqrt(d3 / d4);
        double d16 = (Math.log(d / d2) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d17 = d16 - d7 * Math.sqrt(d4);
        double d18 = d16 + (2D * Math.log(d2 / d)) / (d7 * Math.sqrt(d4));
        double d19 = d18 - d7 * Math.sqrt(d4);
        double d22 = Common.CND(d13) - Math.pow(d2 / d, 2D * d20) * Common.CND(d15);
        double d23 = Common.CND(-d13) - Math.pow(d2 / d, 2D * d20) * Common.CND(-d15);
        double d24 = Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21);
        double d25 = Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21);
        double d26 = Common.CND(d12) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CND(d14);
        double d27 = Common.CND(-d12) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CND(-d14);
        double d28 = Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21);
        double d29 = Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21);
        if ((i == 1) & (j == 1) & (d1 < d2)) {
            val = d * Math.exp((d6 - d5) * d4) * (Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21));
        } else if ((i == 1) & (j == 1) & (d1 > d2)) {
            val = partialBarrier(1, 4, d, d1, d2, d3, d4, d5, d6, d7);
        } else if ((i == 1) & (j == 0) & (d1 < d2)) {
            val = (d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21)) - d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d8, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d14, -d10, -d21))) + d1 * Math.exp(-d5 * d4) * (Common.CBND(-d9, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d15, -d11, -d21));
        } else if ((i == 1) & (j == 4) & (d1 > d2)) {
            val = d * Math.exp((d6 - d5) * d4) * (Common.CBND(d8, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d10, -d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d9, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d11, -d15, -d21));
        } else if ((i == 1) & (j == 4) & (d1 < d2)) {
            val = ((d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21)) - d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d8, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d10, d14, -d21))) + d1 * Math.exp(-d5 * d4) * (Common.CBND(-d9, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d11, d15, -d21)) + d * Math.exp((d6 - d5) * d4) * (Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21))) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21));
        } else if ((i == 1) & (j == 5)) {
            val = (((partialBarrier(1, 4, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d29) + d1 * Math.exp(-d5 * d4) * d25) - d * Math.exp((d6 - d5) * d4) * d28) + d1 * Math.exp(-d5 * d4) * d24;
        } else if ((i == 1) & (j == 3)) {
            val = (partialBarrier(1, 1, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d28) + d1 * Math.exp(-d5 * d4) * d24;
        } else if ((i == 1) & (j == 2)) {
            val = (partialBarrier(1, 0, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d29) + d1 * Math.exp(-d5 * d4) * d25;
        }
        return val;
    }
}
