package org.wwh.opt.models.common;

import static org.wwh.opt.models.common.Common.CND;
import static org.wwh.opt.models.common.Common.ND;
import static org.wwh.opt.models.common.Common.phi;

public class ShekOption {
    public static final int EUROPEAN_CALL = 0;
    public static final int EUROPEAN_PUT  = 1;
    public static double stubPeriod(double d, double d1, int i) {
        double d2;
        double d3 = roundDown((d1 - d) * (double) i);
        double d4 = (d1 - d) * (double) i;
        if (d4 - d3 < 0.0055555555555555558D) {
            d2 = 1.0D;
        } else {
            d2 = (d4 - d3) / (double) i;
        }
        return d2;
    }

    public static double criticalValueOptionsOnOptions(int s, double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = d;
        double d7 = blackPrice(s, d6, d, d3, d4, d5, d2);
        double d8 = blackDelta(s, d6, d, d3, d4, d5, d2);
        for (double d9 = 9.9999999999999995E-007D; Math.abs(d7 - d1) > d9;) {
            d6 -= (d7 - d1) / d8;
            d7 = blackPrice(s, d6, d, d3, d4, d5, d2);
            d8 = blackDelta(s, d6, d, d3, d4, d5, d2);
        }

        return d6;
    }

    public static double roundDown(double d) {
        double d1 = Math.round(d);
        if (d1 > d) {
            d1--;
        }
        return d1;
    }

    public static double blackPriceEuropeanCall(double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = 0.0D;
        double d7 = (Math.log(d / d1) + ((d2 - d3) + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        double d8 = d7 - d4 * Math.sqrt(d5);
        d6 = d * Math.exp(-d3 * d5) * CND(d7) - d1 * Math.exp(-d2 * d5) * CND(d8);
        return d6;
    }
    
    public static double blackPriceEuropeanPut(double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = 0.0D;
        double d7 = (Math.log(d / d1) + ((d2 - d3) + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        double d8 = d7 - d4 * Math.sqrt(d5);
            d6 = -(d * Math.exp(-d3 * d5) * CND(-d7) - d1 * Math.exp(-d2 * d5) * CND(-d8));
        return d6;
    }
    
    public static double blackPrice(int optType, double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = 0.0D;
        double d7 = (Math.log(d / d1) + ((d2 - d3) + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        double d8 = d7 - d4 * Math.sqrt(d5);
        if (optType == EUROPEAN_CALL) {
            d6 = d * Math.exp(-d3 * d5) * CND(d7) - d1 * Math.exp(-d2 * d5) * CND(d8);
        } else if (optType == EUROPEAN_PUT) {
            d6 = -(d * Math.exp(-d3 * d5) * CND(-d7) - d1 * Math.exp(-d2 * d5) * CND(-d8));
        }
        return d6;
    }

    public static double blackDeltaEuropeanCall(double d, double d1, double d2, double d3, double d4, double d5) {
        double d7 = d2 - d3;
        double d8 = 0.0D;
        double d6 = (Math.log(d / d1) + (d7 + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
//        if (s == EUROPEAN_CALL) {
            d8 = Math.exp((d7 - d2) * d5) * CND(d6);
//        } else if (s == EUROPEAN_PUT) {
//            d8 = Math.exp((d7 - d2) * d5) * (CND(d6) - 1.0D);
//        }
        return d8;
    }
    
    public static double blackDeltaEuropeanPut(double d, double d1, double d2, double d3, double d4, double d5) {
        double d7 = d2 - d3;
        double d8 = 0.0D;
        double d6 = (Math.log(d / d1) + (d7 + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
//        if (s == EUROPEAN_CALL) {
//            d8 = Math.exp((d7 - d2) * d5) * CND(d6);
//        } else if (s == EUROPEAN_PUT) {
            d8 = Math.exp((d7 - d2) * d5) * (CND(d6) - 1.0D);
//        }
        return d8;
    }    

    public static double blackDelta(int s, double d, double d1, double d2, double d3, double d4, double d5) {
        double d7 = d2 - d3;
        double d8 = 0.0D;
        double d6 = (Math.log(d / d1) + (d7 + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        if (s == EUROPEAN_CALL) {
            d8 = Math.exp((d7 - d2) * d5) * CND(d6);
        } else if (s == EUROPEAN_PUT) {
            d8 = Math.exp((d7 - d2) * d5) * (CND(d6) - 1.0D);
        }
        return d8;
    }

 public static double Kc(double d, double d1, double d2, double d3,
            double d4) {
        double d5 = (2D * d3) / (d4 * d4);
        double d6 = (2D * d2) / (d4 * d4);
        double d14 = (-(d5 - 1.0D) + Math.sqrt((d5 - 1.0D) * (d5 - 1.0D) + 4D * d6)) / 2D;
        double d7 = d / (1.0D - 1.0D / d14);
        double d9 = (-(d3 * d1 + 2D * d4 * Math.sqrt(d1)) * d) / (d7 - d);
        double d8 = d + (d7 - d) * (1.0D - Math.exp(d9));
        double d10 = (2D * d2) / (d4 * d4 * (1.0D - Math.exp(-d2 * d1)));
        double d11 = (Math.log(d8 / d) + (d3 + (d4 * d4) / 2D) * d1) / (d4 * Math.sqrt(d1));
        double d13 = (-(d5 - 1.0D) + Math.sqrt((d5 - 1.0D) * (d5 - 1.0D) + 4D * d10)) / 2D;
        double d15 = d8 - d;
        double d16 = blackPriceEuropeanCall(d8, d, d2, d2 - d3, d4, d1) + ((1.0D - Math.exp((d3 - d2) * d1) * CND(d11)) * d8) / d13;
        double d17 = Math.exp((d3 - d2) * d1) * CND(d11) * (1.0D - 1.0D / d13) + (1.0D - (Math.exp((d3 - d2) * d1) * CND(d11)) / (d4 * Math.sqrt(d1))) / d13;
        for (double d18 = 9.9999999999999995E-007D; Math.abs(d15 - d16) / d > d18;) {
            d8 = ((d + d16) - d17 * d8) / (1.0D - d17);
            double d12 = (Math.log(d8 / d) + (d3 + (d4 * d4) / 2D) * d1) / (d4 * Math.sqrt(d1));
            d15 = d8 - d;
            d16 = blackPriceEuropeanCall(d8, d, d2, d2 - d3, d4, d1) + ((1.0D - Math.exp((d3 - d2) * d1) * CND(d12)) * d8) / d13;
            d17 = Math.exp((d3 - d2) * d1) * CND(d12) * (1.0D - 1.0D / d13) + (1.0D - (Math.exp((d3 - d2) * d1) * ND(d12)) / (d4 * Math.sqrt(d1))) / d13;
        }

        double d19 = d8;
        return d19;
    }

    public static double coupons(double d, double d1, int i) {
        int j;
        double d2 = roundDown((d1 - d) * (double) i);
        double d3 = (d1 - d) * (double) i;
        if (d3 - d2 < 0.0055555555555555558D) {
            j = (int) d2;
        } else {
            j = (int) d2 + 1;
        }
        return (double) j;
    }



    public static double BAWAmericanCallApprox(double d, double d1, double d2, double d3,
            double d4, double d5) {
        double d12 = 0.0D;
        if (d4 >= d3) {
            d12 = blackPriceEuropeanCall(d, d1, d3, d4, d5, d2);
        } else {
            double d6 = Kc(d1, d2, d3, d4, d5);
            double d7 = (2D * d4) / (d5 * d5);
            double d8 = (2D * d3) / (d5 * d5 * (1.0D - Math.exp(-d3 * d2)));
            double d9 = (Math.log(d6 / d1) + (d4 + (d5 * d5) / 2D) * d2) / (d5 * Math.sqrt(d2));
            double d10 = (-(d7 - 1.0D) + Math.sqrt((d7 - 1.0D) * (d7 - 1.0D) + 4D * d8)) / 2D;
            double d11 = (d6 / d10) * (1.0D - Math.exp((d4 - d3) * d2) * CND(d9));
            if (d < d6) {
                d12 = blackPriceEuropeanCall(d, d1, d3, d3 - d4, d5, d2) + d11 * Math.pow(d / d6, d10);
            } else {
                d12 = d - d1;
            }
        }
        return d12;
    }

    public static double RoundUp(double d) {
        double d1 = Math.round(d);
        if (d1 < d) {
            d1++;
        }
        return d1;
    }

    public static double BAWAmericanPutApprox(double d, double d1, double d2, double d3,
            double d4, double d5) {
        double d12;
        double d6 = Kp(d1, d2, d3, d4, d5);
        double d7 = (2D * d4) / (d5 * d5);
        double d8 = (2D * d3) / (d5 * d5 * (1.0D - Math.exp(-d3 * d2)));
        double d9 = (Math.log(d6 / d1) + (d4 + (d5 * d5) / 2D) * d2) / (d5 * Math.sqrt(d2));
        double d10 = (-(d7 - 1.0D) - Math.sqrt((d7 - 1.0D) * (d7 - 1.0D) + 4D * d8)) / 2D;
        double d11 = -(d6 / d10) * (1.0D - Math.exp((d4 - d3) * d2) * CND(-d9));
        if (d > d6) {
            d12 = blackPriceEuropeanPut(d, d1, d3, d3 - d4, d5, d2) + d11 * Math.pow(d / d6, d10);
        } else {
            d12 = d1 - d;
        }
        return d12;
    }

    public static double BSAmericanCallApprox(double d, double d1, double d2, double d3,
            double d4, double d5) {
        double d9 = 0.0D;
        double d10 = 0.0D;
        double d11 = 0.0D;
        double d12;
        if (d4 >= d3) {
            d12 = blackPriceEuropeanCall(d, d1, d3, d3 - d4, d5, d2);
        } else {
            d11 = (0.5D - d4 / (d5 * d5)) + Math.sqrt(Math.pow(d4 / (d5 * d5) - 0.5D, 2D) + (2D * d3) / (d5 * d5));
            double d6 = (d11 / (d11 - 1.0D)) * d1;
            double d7 = Math.max(d1, (d3 / (d3 - d4)) * d1);
            double d8 = (-(d4 * d2 + 2D * d5 * Math.sqrt(d2)) * d7) / (d6 - d7);
            d9 = d7 + (d6 - d7) * (1.0D - Math.exp(d8));
            d10 = (d9 - d1) * Math.pow(d9, -d11);
        }
        if (d >= d9) {
            d12 = d - d1;
        } else {
            d12 = (((d10 * Math.pow(d, d11) - d10 * Common.phi(d, d2, d11, d9, d9, d3, d4, d5)) + phi(d, d2, 1.0D, d9, d9, d3, d4, d5)) - Common.phi(d, d2, 1.0D, d1, d9, d3, d4, d5) - d1 * Common.phi(d, d2, 0.0D, d9, d9, d3, d4, d5)) + d1 * Common.phi(d, d2, 0.0D, d1, d9, d3, d4, d5);
        }
        return d12;
    }

    public static double BSAmericanPutApprox(double d, double d1, double d2, double d3,
            double d4, double d5) {
        return BSAmericanCallApprox(d1, d, d2, d3 - d4, -d4, d5);
    }

    public static double Kp(double d, double d1, double d2, double d3,
            double d4) {
        double d5 = (2D * d3) / (d4 * d4);
        double d6 = (2D * d2) / (d4 * d4);
        double d14 = (-(d5 - 1.0D) - Math.sqrt((d5 - 1.0D) * (d5 - 1.0D) + 4D * d6)) / 2D;
        double d7 = d / (1.0D - 1.0D / d14);
        double d9 = ((d3 * d1 - 2D * d4 * Math.sqrt(d1)) * d) / (d - d7);
        double d8 = d7 + (d - d7) * Math.exp(d9);
        double d10 = (2D * d2) / (d4 * d4 * (1.0D - Math.exp(-d2 * d1)));
        double d11 = (Math.log(d8 / d) + (d3 + (d4 * d4) / 2D) * d1) / (d4 * Math.sqrt(d1));
        double d13 = (-(d5 - 1.0D) - Math.sqrt((d5 - 1.0D) * (d5 - 1.0D) + 4D * d10)) / 2D;
        double d15 = d - d8;
        double d16 = blackPriceEuropeanPut(d8, d, d2, d2 - d3, d4, d1) - ((1.0D - Math.exp((d3 - d2) * d1) * CND(-d11)) * d8) / d13;
        double d17 = -Math.exp((d3 - d2) * d1) * CND(-d11) * (1.0D - 1.0D / d13) - (1.0D + (Math.exp((d3 - d2) * d1) * ND(-d11)) / (d4 * Math.sqrt(d1))) / d13;
        for (double d18 = 9.9999999999999995E-007D; Math.abs(d15 - d16) / d > d18;) {
            d8 = ((d - d16) + d17 * d8) / (1.0D + d17);
            double d12 = (Math.log(d8 / d) + (d3 + (d4 * d4) / 2D) * d1) / (d4 * Math.sqrt(d1));
            d15 = d - d8;
            d16 = blackPriceEuropeanPut(d8, d, d2, d2 - d3, d4, d1) - ((1.0D - Math.exp((d3 - d2) * d1) * CND(-d12)) * d8) / d13;
            d17 = -Math.exp((d3 - d2) * d1) * CND(-d12) * (1.0D - 1.0D / d13) - (1.0D + (Math.exp((d3 - d2) * d1) * CND(-d12)) / (d4 * Math.sqrt(d1))) / d13;
        }

        double d19 = d8;
        return d19;
    }
}
