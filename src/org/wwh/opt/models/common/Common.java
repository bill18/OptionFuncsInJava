/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wwh.opt.models.common;

import java.util.Date;

/**
 *
 * @author wenhua
 */
public class Common {

    public static double max(double d, double d1) {
        double d2 = d1;
        if (d >= d1) {
            d2 = d;
        } else {
            d2 = d1;
        }
        return d2;
    }
    
    public static double discreteAdjustedBarrier(double d, double d1, double d2, double d3) {
        double d4 = d1;
        if (d1 > d) {
            d4 = d1 * Math.exp(0.58260000000000001D * d2 * Math.sqrt(d3));
        } else if (d1 < d) {
            d4 = d1 * Math.exp(-0.58260000000000001D * d2 * Math.sqrt(d3));
        }
        return d4;
    }
    
    public static double yearFaction(String s, String s1, int i) {
        double d = 0.0D;
//        Date date = new Date(s);
//        Date date1 = new Date(s1);
        double d1 = Date.parse(s);
        double d2 = Date.parse(s1);
        if (i == 0 || i == 3 || i == 2) {
            d = (d2 - d1) / 86400000D / 365D;
        } else if (i == 1) {
            d = (d2 - d1) / 86400000D / 360D;
        }
        return d;
    }

    public static double phi(double d, double d1, double d2, double d3,
            double d4, double d5, double d6, double d7) {
        double d8 = (-d5 + d2 * d6 + 0.5D * d2 * (d2 - 1.0D) * d7 * d7) * d1;
        double d10 = -(Math.log(d / d3) + (d6 + (d2 - 0.5D) * d7 * d7) * d1) / (d7 * Math.sqrt(d1));
        double d9 = (2D * d6) / (d7 * d7) + (2D * d2 - 1.0D);
        double d11 = Math.exp(d8) * Math.pow(d, d2) * (CND(d10) - Math.pow(d4 / d, d9) * CND(d10 - (2D * Math.log(d4 / d)) / (d7 * Math.sqrt(d1))));
        return d11;
    }

    public static double sgn(double d) {
        double d1 = 0.0D;
        if (d > 0.0D) {
            d1 = 1.0D;
        } else if (d == 0.0D) {
            d1 = 0.0D;
        } else if (d < 0.0D) {
            d1 = -1D;
        }
        return d1;
    }

    public static double spline(double d, double ad[], double ad1[], int i) {
        double ad2[] = new double[i];
        double ad3[] = new double[i];
        double ad4[] = new double[i];
        int j = i - 1;
        ad4[1] = ad[2] - ad[1];
        ad3[2] = (ad1[2] - ad1[1]) / ad4[1];
        for (int l = 2; l <= j; l++) {
            ad4[l] = ad[l + 1] - ad[l];
            ad2[l] = 2D * (ad4[l - 1] + ad4[l]);
            ad3[l + 1] = (ad1[l + 1] - ad1[l]) / ad4[l];
            ad3[l] = ad3[l + 1] - ad3[l];
        }

        ad2[1] = -ad4[1];
        ad2[i] = -ad4[i - 1];
        ad3[1] = 0.0D;
        ad3[i] = 0.0D;
        if (i != 3) {
            ad3[1] = ad3[3] / (ad[4] - ad[2]) - ad3[2] / (ad[3] - ad[1]);
            ad3[i] = ad3[i - 1] / (ad[i] - ad[i - 2]) - ad3[i - 2] / (ad[i - 1] - ad[i - 3]);
            ad3[1] = (ad3[1] * Math.pow(ad4[1], 2D)) / (ad[4] - ad[1]);
            ad3[i] = (-ad3[i] * Math.pow(ad4[i - 1], 2D)) / (ad[i] - ad[i - 3]);
        }
        for (int i1 = 2; i1 <= i; i1++) {
            double d1 = ad4[i1 - 1] / ad2[i1 - 1];
            ad2[i1] = ad2[i1] - d1 * ad4[i1 - 1];
            ad3[i1] = ad3[i1] - d1 * ad3[i1 - 1];
        }

        ad3[i] = ad3[i] / ad2[i];
        for (int k = 1; k <= j; k++) {
            int j1 = i - k;
            ad3[j1] = (ad3[j1] - ad4[j1] * ad3[j1 + 1]) / ad2[j1];
        }

        ad2[i] = (ad1[i] - ad1[j]) / ad4[j] + ad4[j] * (ad3[j] + 2D * ad3[i]);
        for (int k1 = 1; k1 <= j; k1++) {
            ad2[k1] = (ad1[k1 + 1] - ad1[k1]) / ad4[k1] - ad4[k1] * (ad3[k1 + 1] + 2D * ad3[k1]);
            ad4[k1] = (ad3[k1 + 1] - ad3[k1]) / ad4[k1];
            ad3[k1] = 3D * ad3[k1];
        }

        ad3[i] = 3D * ad3[i];
        ad4[i] = ad4[i - 1];
        int l1 = 1;
        if (l1 >= i) {
            l1 = 1;
        }
        if (d >= ad[l1] && d <= ad[l1 + 1]) {
            double d2 = d - ad[l1];
            return ad1[l1] + d2 * (ad2[l1] + d2 * (ad3[l1] + d2 * ad4[l1]));
        }
        l1 = 1;
        int i2 = i + 1;
        do {
            int j2 = (l1 + i2) / 2;
            if (d < ad[j2]) {
                i2 = j2;
            } else {
                l1 = j2;
            }
        } while (i2 > l1 + 1);
        double d3 = d - ad[l1];
        return ad1[l1] + d3 * (ad2[l1] + d3 * (ad3[l1] + d3 * ad4[l1]));
    }

    public static double CBND(double d, double d1, double d2) {
        double ad[] = {
            0.24840614999999999D, 0.39233107D, 0.21141819000000001D, 0.033246659999999997D, 0.00082485334000000001D
        };
        double ad1[] = {
            0.10024215D, 0.48281396999999998D, 1.0609497999999999D, 1.7797293999999999D, 2.6697603999999999D
        };
        double d9 = 0.0D;
        double d6 = d / Math.sqrt(2D * (1.0D - d2 * d2));
        double d7 = d1 / Math.sqrt(2D * (1.0D - d2 * d2));
        if ((d <= 0.0D) & (d1 <= 0.0D) & (d2 <= 0.0D)) {
            double d8 = 0.0D;
            int i = 0;
            do {
                int j = 0;
                do {
                    d8 += ad[i] * ad[j] * Math.exp(d6 * (2D * ad1[i] - d6) + d7 * (2D * ad1[j] - d7) + 2D * d2 * (ad1[i] - d6) * (ad1[j] - d7));
                } while (++j <= 4);
            } while (++i <= 4);
            d9 = (Math.sqrt(1.0D - d2 * d2) / 3.1415926535897931D) * d8;
        } else if ((d <= 0.0D) & (d1 >= 0.0D) & (d2 >= 0.0D)) {
            d9 = CND(d) - CBND(d, -d1, -d2);
        } else if ((d >= 0.0D) & (d1 <= 0.0D) & (d2 >= 0.0D)) {
            d9 = CND(d1) - CBND(-d, d1, -d2);
        } else if ((d >= 0.0D) & (d1 >= 0.0D) & (d2 <= 0.0D)) {
            d9 = ((CND(d) + CND(d1)) - 1.0D) + CBND(-d, -d1, d2);
        } else if (d * d1 * d2 > 0.0D) {
            double d3 = ((d2 * d - d1) * sgn(d)) / Math.sqrt((d * d - 2D * d2 * d * d1) + d1 * d1);
            double d4 = ((d2 * d1 - d) * sgn(d1)) / Math.sqrt((d * d - 2D * d2 * d * d1) + d1 * d1);
            double d5 = (1.0D - sgn(d) * sgn(d1)) / 4D;
            d9 = (CBND(d, 0.0D, d3) + CBND(d1, 0.0D, d4)) - d5;
        }
        return d9;
    }

    public static double CND(double d) {
        double d2 = 0.31938153000000002D;
        double d3 = -0.356563782D;
        double d4 = 1.781477937D;
        double d5 = -1.8212559779999999D;
        double d6 = 1.3302744289999999D;
        double d7 = Math.abs(d);
        double d8 = 1.0D / (1.0D + 0.23164190000000001D * d7);
        double d1 = 1.0D - (1.0D / Math.sqrt(6.2831853071795862D)) * Math.exp(-Math.pow(d7, 2D) / 2D) * (d2 * d8 + d3 * Math.pow(d8, 2D) + d4 * Math.pow(d8, 3D) + d5 * Math.pow(d8, 4D) + d6 * Math.pow(d8, 5D));
        if (d < 0.0D) {
            d1 = 1.0D - d1;
        }
        return d1;
    }

    public static double ND(double d) {
        double d1 = (1.0D / Math.sqrt(6.2831853071795862D)) * Math.exp((-d * d) / 2D);
        return d1;
    }

    public static String sDate(double d) {
        Date date = new Date();
        Date date1 = new Date(1970, 1, 1);
        int i;
        int j;
        int k;
        if (d < 0.0D) {
            i = date.getDate() + 2;
            j = date.getMonth();
            k = date.getYear() + 1900;
        } else {
            date1.setTime((long) d);
            i = date1.getDate();
            j = date1.getMonth();
            k = date1.getYear() + 1900;
        }
        String s = "Jan";
        switch (j) {
            case 0: // '\0'
                s = "Jan";
                break;

            case 1: // '\001'
                s = "Feb";
                break;

            case 2: // '\002'
                s = "Mar";
                break;

            case 3: // '\003'
                s = "Apr";
                break;

            case 4: // '\004'
                s = "May";
                break;

            case 5: // '\005'
                s = "Jun";
                break;

            case 6: // '\006'
                s = "Jul";
                break;

            case 7: // '\007'
                s = "Aug";
                break;

            case 8: // '\b'
                s = "Sep";
                break;

            case 9: // '\t'
                s = "Oct";
                break;

            case 10: // '\n'
                s = "Nov";
                break;

            case 11: // '\013'
                s = "Dec";
                break;
        }
        return i + "-" + s + "-" + k;
    }

    public static double min(double d, double d1) {
        return (d <= d1)?d:d1;
    }
}
