package org.wwh.opt.models.barrier;

import org.wwh.opt.models.common.Common;


public class Barrier {

    public static final int DOWN_KNOCK_IN_CALL = 0;
    public static final int UP_KNOCK_IN_CALL = 1;
    public static final int DOWN_KNOCK_IN_PUT = 2;
    public static final int UP_KNOCK_IN_PUT = 3;
    public static final int DOWN_KNOCK_OUT_CALL = 4;
    public static final int UP_KNOCK_OUT_CALL = 5;
    public static final int DOWN_KNOCK_OUT_PUT = 6;
    public static final int UP_KNOCK_OUT_PUT = 7;

    public static double standardBarrier(int optType, double d, double d1, double d2,
            double d3, double d4, double d5, double d6, double d7) {
        double standardBarrier = 0;
        int j = 0;
        int k = 0;
        double d8 = (d6 - (d7 * d7) / 2D) / (d7 * d7);
        double d9 = Math.sqrt(d8 * d8 + (2D * d5) / (d7 * d7));
        double d10 = Math.log(d / d1) / (d7 * Math.sqrt(d4)) + (1.0D + d8) * d7 * Math.sqrt(d4);
        double d11 = Math.log(d / d2) / (d7 * Math.sqrt(d4)) + (1.0D + d8) * d7 * Math.sqrt(d4);
        double d12 = Math.log((d2 * d2) / (d * d1)) / (d7 * Math.sqrt(d4)) + (1.0D + d8) * d7 * Math.sqrt(d4);
        double d13 = Math.log(d2 / d) / (d7 * Math.sqrt(d4)) + (1.0D + d8) * d7 * Math.sqrt(d4);
        double d14 = Math.log(d2 / d) / (d7 * Math.sqrt(d4)) + d9 * d7 * Math.sqrt(d4);
        switch (optType) {
            case DOWN_KNOCK_IN_CALL:
            case DOWN_KNOCK_OUT_CALL:
                j = 1;
                k = 1;
                break;
            case UP_KNOCK_IN_CALL:
            case UP_KNOCK_OUT_CALL:
                j = -1;
                k = 1;
                break;
            case DOWN_KNOCK_IN_PUT:
            case DOWN_KNOCK_OUT_PUT:
                j = 1;
                k = -1;
                break;
            case UP_KNOCK_IN_PUT:
            case UP_KNOCK_OUT_PUT:
                j = -1;
                k = -1;
                break;
            default:
                break;
        }
        double d15 = (double) k * d * Math.exp((d6 - d5) * d4) * Common.CND((double) k * d10) - (double) k * d1 * Math.exp(-d5 * d4) * Common.CND((double) k * d10 - (double) k * d7 * Math.sqrt(d4));
        double d16 = (double) k * d * Math.exp((d6 - d5) * d4) * Common.CND((double) k * d11) - (double) k * d1 * Math.exp(-d5 * d4) * Common.CND((double) k * d11 - (double) k * d7 * Math.sqrt(d4));
        double d17 = (double) k * d * Math.exp((d6 - d5) * d4) * Math.pow(d2 / d, 2D * (d8 + 1.0D)) * Common.CND((double) j * d12) - (double) k * d1 * Math.exp(-d5 * d4) * Math.pow(d2 / d, 2D * d8) * Common.CND((double) j * d12 - (double) j * d7 * Math.sqrt(d4));
        double d18 = (double) k * d * Math.exp((d6 - d5) * d4) * Math.pow(d2 / d, 2D * (d8 + 1.0D)) * Common.CND((double) j * d13) - (double) k * d1 * Math.exp(-d5 * d4) * Math.pow(d2 / d, 2D * d8) * Common.CND((double) j * d13 - (double) j * d7 * Math.sqrt(d4));
        double d19 = d3 * Math.exp(-d5 * d4) * (Common.CND((double) j * d11 - (double) j * d7 * Math.sqrt(d4)) - Math.pow(d2 / d, 2D * d8) * Common.CND((double) j * d13 - (double) j * d7 * Math.sqrt(d4)));
        double d20 = d3 * (Math.pow(d2 / d, d8 + d9) * Common.CND((double) j * d14) + Math.pow(d2 / d, d8 - d9) * Common.CND((double) j * d14 - (double) (2 * j) * d9 * d7 * Math.sqrt(d4)));
        if (d1 > d2) {
            switch (optType) {
                case DOWN_KNOCK_IN_CALL: // '\0'
                    standardBarrier = d17 + d19;
                    break;

                case UP_KNOCK_IN_CALL: // '\001'
                    standardBarrier = d15 + d19;
                    break;

                case DOWN_KNOCK_IN_PUT: // '\002'
                    standardBarrier = (d16 - d17) + d18 + d19;
                    break;

                case UP_KNOCK_IN_PUT: // '\003'
                    standardBarrier = (d15 - d16) + d18 + d19;
                    break;

                case DOWN_KNOCK_OUT_CALL: // '\004'
                    standardBarrier = (d15 - d17) + d20;
                    break;

                case UP_KNOCK_OUT_CALL: // '\005'
                    standardBarrier = d20;
                    break;

                case DOWN_KNOCK_OUT_PUT: // '\006'
                    standardBarrier = (((d15 - d16) + d17) - d18) + d20;
                    break;

                case UP_KNOCK_OUT_PUT: // '\007'
                    standardBarrier = (d16 - d18) + d20;
                    break;
            }
        } else if (d1 < d2) {
            switch (optType) {
                case DOWN_KNOCK_IN_CALL: // '\0'
                    standardBarrier = (d15 - d16) + d18 + d19;
                    break;

                case UP_KNOCK_IN_CALL: // '\001'
                    standardBarrier = (d16 - d17) + d18 + d19;
                    break;

                case DOWN_KNOCK_IN_PUT: // '\002'
                    standardBarrier = d15 + d19;
                    break;

                case UP_KNOCK_IN_PUT: // '\003'
                    standardBarrier = d17 + d19;
                    break;

                case DOWN_KNOCK_OUT_CALL: // '\004'
                    standardBarrier = (d16 + d20) - d18;
                    break;

                case UP_KNOCK_OUT_CALL: // '\005'
                    standardBarrier = (((d15 - d16) + d17) - d18) + d20;
                    break;

                case DOWN_KNOCK_OUT_PUT: // '\006'
                    standardBarrier = d20;
                    break;

                case UP_KNOCK_OUT_PUT: // '\007'
                    standardBarrier = (d15 - d17) + d20;
                    break;
            }
        }
        return standardBarrier;
    }

// return: price, delta, gamma, vega
    public static double[] price(
            int optType,
            double S,
            double X,
            double H,
            double K,
            double T,
            double r,
            double D,
            double v
    ) {
        double[] retVals = new double[4];
        // Price
        double price = standardBarrier(optType, S, X, H, K, T, r, r - D, v);
        double priceOther = standardBarrier(optType, S - S * 0.01D, X, H, K, T, r, r - D, v);
        retVals[0] = price;
        // Delta
        retVals[1] = (1.0D / (S * 0.01D)) * (price - priceOther);
        // Gamma
        retVals[2] = (1.0D / (S * 0.01D * (S * 0.01D))) * ((standardBarrier(optType, S + 0.01D * S, X, H, K, T, r, r - D, v) - 2D * price) + priceOther);
        // Vega
        retVals[3] = (1.0D / (v * 0.01D)) * (price - standardBarrier(optType, S, X, H, K, T, r, r - D, v - v * 0.01D));

        return retVals;
    }

}
