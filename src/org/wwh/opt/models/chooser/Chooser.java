package org.wwh.opt.models.chooser;

import org.wwh.opt.models.common.Common;
import org.wwh.opt.models.common.ShekOption;

public class Chooser 
{
    public static double simpleChooserCalc(double d, double d1, double d2, double d3, double d4, double d5, double d6)
    {
        double d8 = d2 - d3;
        double d9 = (Math.log(d / d1) + (d8 + (d4 * d4) / 2D) * d6) / (d4 * Math.sqrt(d6));
        double d10 = (Math.log(d / d1) + d8 * d6 + (d4 * d4 * d5) / 2D) / (d4 * Math.sqrt(d5));
        double d7 = (d * Math.exp((d8 - d2) * d6) * Common.CND(d9) - d1 * Math.exp(-d2 * d6) * Common.CND(d9 - d4 * Math.sqrt(d6)) - d * Math.exp((d8 - d2) * d6) * Common.CND(-d10)) + d1 * Math.exp(-d2 * d6) * Common.CND(-d10 + d4 * Math.sqrt(d5));
        return d7;
    }
    
    public static double criticalValueChooserCalc(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8, double D)
    {
        // double d21 = 0.0D;
        double d9 = d;
        double d10 = ShekOption.blackPriceEuropeanCall(d9, d1, d6, D, d8, d4 - d3);
        double d12 = ShekOption.blackPriceEuropeanPut (d9, d2, d6, D, d8, d5 - d3);
        double d15 = ShekOption.blackDeltaEuropeanCall(d9, d1, d6, D, d8, d4 - d3);
        double d17 = ShekOption.blackDeltaEuropeanPut (d9, d2, d6, D, d8, d5 - d3);
        double d19 = d10 - d12;
        double d20 = d15 - d17;
        for(double d14 = 0.001D; Math.abs(d19) > d14;)
        {
            d9 -= d19 / d20;
            double d11 = ShekOption.blackPriceEuropeanCall(d9, d1, d6, D, d8, d4 - d3);
            double d13 = ShekOption.blackPriceEuropeanPut (d9, d2, d6, D, d8, d5 - d3);
            double d16 = ShekOption.blackDeltaEuropeanCall(d9, d1, d6, D, d8, d4 - d3);
            double d18 = ShekOption.blackDeltaEuropeanPut (d9, d2, d6, D, d8, d5 - d3);
            d19 = d11 - d13;
            d20 = d16 - d18;
        }

        return d9; // d21 = d9;
    }
    
    public static double complexChooserCalc(double S, double Xcall, double Xput, double r, double D, double vol, double TD, 
            double Tcall, double Tput)
    {
        double d9 = r - D;
        double d10 = 0.0D;
        double d11 = criticalValueChooserCalc(S, Xcall, Xput, TD, Tcall, Tput, r, d9, vol, D);
        double d12 = (Math.log(S / d11) + (d9 + (vol * vol) / 2D) * TD) / (vol * Math.sqrt(TD));
        double d13 = d12 - vol * Math.sqrt(TD);
        double d14 = (Math.log(S / Xcall) + (d9 + (vol * vol) / 2D) * Tcall) / (vol * Math.sqrt(Tcall));
        double d15 = (Math.log(S / Xput) + (d9 + (vol * vol) / 2D) * Tput) / (vol * Math.sqrt(Tput));
        double d16 = Math.sqrt(TD / Tcall);
        double d17 = Math.sqrt(TD / Tput);
        d10 = (S * Math.exp((d9 - r) * Tcall) * Common.CBND(d12, d14, d16) - Xcall * Math.exp(-r * Tcall) * Common.CBND(d13, d14 - vol * Math.sqrt(Tcall), d16) - S * Math.exp((d9 - r) * Tput) * Common.CBND(-d12, -d15, d17)) + Xput * Math.exp(-r * Tput) * Common.CBND(-d13, -d15 + vol * Math.sqrt(Tput), d17);
        return d10;
    }
    
    public static double[] price(double S, double Xcall, double Xput, double r, double D, double vol, double TD, 
            double Tcall, double Tput)
    {
        double[] retVals = new double[4];
        // price
        double ds = 0.01D * S;
        double dv = 0.01D * vol;
        double price = complexChooserCalc(S, Xcall, Xput, r, D, vol, TD, Tcall, Tput);
        double priceSplusD = complexChooserCalc(S + ds, Xcall, Xput, r, D, vol, TD, Tcall, Tput);
        double delta = (1.0D / ds) * (priceSplusD - price);
        double gamma = (1.0D / (ds * ds)) * ((priceSplusD - 2D * price) + complexChooserCalc(S - ds, Xcall, Xput, r, D, vol, TD, Tcall, Tput));
        double vega = (1.0D / dv) * (complexChooserCalc(S, Xcall, Xput, r, D, vol + dv, TD, Tcall, Tput) - price);
        retVals[0] = price;
        retVals[1] = delta; // delta
        retVals[2] = gamma; // gamma
        retVals[3] = vega; // vega

        return retVals;
    }    
}
