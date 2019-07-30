/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.wwh.opt.models.vanilla;

import org.wwh.opt.models.common.Common;
import org.wwh.opt.models.common.ShekOption;

/**
 *
 * @author wenhua
 */
public class BlackScholes {
    public static double europeanCallPrice(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.blackPriceEuropeanCall(d, d1, d2, d3, d4, d5);
    }

    public static double europeanPutPrice(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.blackPriceEuropeanPut(d, d1, d2, d3, d4, d5);
    }

    public static double americanCallBSApprox(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.BSAmericanCallApprox(d, d1, d5, d2, d2 - d3, d4);
    }

    public static double americanPutBSApprox(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.BSAmericanPutApprox(d, d1, d5, d2, d2 - d3, d4);
    }

    public static double americanCallBAWApprox(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.BAWAmericanCallApprox(d, d1, d5, d2, d2 - d3, d4);
    }

    public static double americanPutBAWApprox(double d, double d1, double d2, double d3, double d4, double d5) {
        return ShekOption.BAWAmericanPutApprox(d, d1, d5, d2, d2 - d3, d4);
    }
    
    public static double europeanCallDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = (Math.log(d / d1) + ((d2 - d3) + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        return Math.exp(-d3 * d5) * Common.CND(d6);
    }

    public static double europeanPutDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        double d6 = (Math.log(d / d1) + ((d2 - d3) + (d4 * d4) / 2D) * d5) / (d4 * Math.sqrt(d5));
        return Math.exp(-d3 * d5) * (Common.CND(d6) - 1.0D);
    }

    public static double americanCallBSDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        return delta(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBSApprox);
    }

    public static double americanPutBSDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        return delta(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBSApprox);
    }

    public static double americanCallBAWDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        return delta(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBAWApprox);
    }

    public static double americanPutBAWDelta(double d, double d1, double d2, double d3, double d4, double d5) {
        return delta(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBAWApprox);
    }
    public static double europeanCallGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::europeanCallPrice);
    }

    public static double europeanPutGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::europeanPutPrice);
    }

    public static double americanCallBSGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBSApprox);
    }

    public static double americanPutBSGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBSApprox);
    }

    public static double americanCallBAWGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBAWApprox);
    }

    public static double americanPutBAWGamma(double d, double d1, double d2, double d3, double d4, double d5) {
        return gamma(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBAWApprox);
    }
    
    public static double europeanCallVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::europeanCallPrice);
    }

    public static double europeanPutVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::europeanPutPrice);
    }

    public static double americanCallBSVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBSApprox);
    }

    public static double americanPutBSVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBSApprox);
    }

    public static double americanCallBAWVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::americanCallBAWApprox);
    }

    public static double americanPutBAWVega(double d, double d1, double d2, double d3, double d4, double d5) {
        return vega(d, d1, d2, d3, d4, d5, BlackScholes::americanPutBAWApprox);
    }
    
    public static double delta(double S, double X, double r, double D, double v, double T, IBlackScholesPrice optCalc) {
        return optCalc.calc(S + 1.0D, X, r, D, v, T) - optCalc.calc(S, X, r, D, v, T);
    }

    public static double gamma(double S, double X, double r, double D, double v, double T, IBlackScholesPrice optCalc) {
        double d3 = 0.01D * S;
        return (1.0D / (d3 * d3)) * ((optCalc.calc(S + d3, X, r, D, v, T) - 2D * optCalc.calc(S, X, r, D, v, T)) + optCalc.calc(S - d3, X, r, D, v, T));
    }
    
    public static double vega(double S, double X, double r, double D, double v, double T, IBlackScholesPrice optCalc) {
       double dv = 0.01D * v;
       return (1.0D / dv) * (optCalc.calc(S, X, r, D, v + dv, T) - optCalc.calc(S, X, r, D, v, T));
    }
    
    public static double impliedVol(double P, double S, double X, double r, double D, double v, double T, IBlackScholesPrice optCalc) {
            double error = 0.050000000000000003D;
            double diff;
            do {
                double d2 = optCalc.calc(S, X, r, D, v, T);
                diff = d2 - P;
                double dv = 0.0001D * v;
                double vega = (1.0D / dv) * (optCalc.calc(S, X, r, D, v + dv, T) - optCalc.calc(S, X, r, D, v, T));
                v = v - diff / vega;
            } while (diff > error);
            
            return v;
    }
    
    public static double europeanCallImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::europeanCallPrice);
    }

    public static double europeanPutImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::europeanPutPrice);
    }

    public static double americanCallBSImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::americanCallBSApprox);
    }

    public static double americanPutBSImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::americanPutBSApprox);
    }
    
    public static double americanCallBAWImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::americanCallBAWApprox);
    }

    public static double americanPutBAWImpliedVol(double P, double S, double X, double r, double D, double v, double T) {
        return impliedVol(P, S, X, r, D, v, T, BlackScholes::americanPutBAWApprox);
    }    
}
