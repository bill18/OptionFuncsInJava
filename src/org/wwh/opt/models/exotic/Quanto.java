package org.wwh.opt.models.exotic;

import java.awt.Choice;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;
import java.util.Random;

import javax.swing.JPanel;

import org.wwh.opt.models.common.Common;

public class Quanto extends JPanel
{
	public static final long serialVersionUID = 10046;

    public Quanto()
    {
        RND = new Random();
        init();
    }

    double Quanto_Calc(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7, double d8, double d9)
    {
        double d10 = 0.0D;
        double d11 = (Math.log(d / d1) + ((d3 - d4 - d7 * d5 * d6) + (d5 * d5) / 2D) * d9) / (d5 * Math.sqrt(d9));
        double d12 = d11 - d5 * Math.sqrt(d9);
        if(i == 0)
            d10 = d8 * (d * Math.exp((d3 - d2 - d4 - d7 * d5 * d6) * d9) * Common.CND(d11) - d1 * Math.exp(-d2 * d9) * Common.CND(d12));
        else
            d10 = d8 * (d1 * Math.exp(-d2 * d9) * Common.CND(-d12) - d * Math.exp((d3 - d2 - d4 - d7 * d5 * d6) * d9) * Common.CND(-d11));
        return d10;
    }

    public boolean action(Event event, Object obj)
    {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        s = TextField_Strike.getText();
        double1 = Double.valueOf(s);
        X = double1.doubleValue();
        s = TextField_Expiry.getText();
        double1 = Double.valueOf(s);
        T = double1.doubleValue();
        s = TextField_Rate.getText();
        double1 = Double.valueOf(s);
        r_dom = double1.doubleValue() / 100D;
        s = TextField_Rate2.getText();
        double1 = Double.valueOf(s);
        r_for = double1.doubleValue() / 100D;
        s = TextField_Dividend.getText();
        double1 = Double.valueOf(s);
        D = double1.doubleValue() / 100D;
        s = TextField_FX.getText();
        double1 = Double.valueOf(s);
        FX = double1.doubleValue();
        s = TextField_VolFX.getText();
        double1 = Double.valueOf(s);
        vol_FX = double1.doubleValue() / 100D;
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        vol = double1.doubleValue() / 100D;
        s = TextField_Corr.getText();
        double1 = Double.valueOf(s);
        corr = double1.doubleValue();
        int i = PutCall.getSelectedIndex();
        OptionPrice = Quanto_Calc(i, S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T);
        DecimalFormat decimalformat = new DecimalFormat("0.####");
        String s1 = decimalformat.format(OptionPrice);
        TextField_Price.setText(s1);
        double d = (1.0D / (S * 0.01D)) * (Quanto_Calc(i, S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T) - Quanto_Calc(i, S - 0.01D * S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T));
        s1 = decimalformat.format(d);
        TextField_Delta.setText(s1);
        double d1 = (1.0D / (S * 0.01D * (S * 0.01D))) * ((Quanto_Calc(i, S + 0.01D * S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T) - 2D * Quanto_Calc(i, S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T)) + Quanto_Calc(i, S - 0.01D * S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T));
        s1 = decimalformat.format(d1);
        TextField_Gamma.setText(s1);
        double d2 = (1.0D / (vol * 0.01D)) * (Quanto_Calc(i, S, X, r_dom, r_for, D, vol, vol_FX, corr, FX, T) - Quanto_Calc(i, S, X, r_dom, r_for, D, vol - 0.0001D * vol, vol_FX, corr, FX, T));
        s1 = decimalformat.format(d2);
        TextField_Vega.setText(s1);
    
        return true;
    }

    void buildConstraints(GridBagConstraints gridbagconstraints, int i, int j, int k, int l, int i1, int j1)
    {
        gridbagconstraints.gridx = i;
        gridbagconstraints.gridy = j;
        gridbagconstraints.gridwidth = k;
        gridbagconstraints.gridheight = l;
        gridbagconstraints.weightx = i1;
        gridbagconstraints.weighty = j1;
    }

    public Insets insets()
    {
        return new Insets(10, 10, 10, 10);
    }

    public void init()
    {
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        GridBagLayout gridbaglayout = new GridBagLayout();
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        setLayout(gridbaglayout);
        int i = 1;
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        PutCall = new Choice();
        PutCall.add("European Call");
        PutCall.add("European Put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Asset", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Asset = new TextField(5);
        gridbaglayout.setConstraints(TextField_Asset, gridbagconstraints);
        add(TextField_Asset);
        TextField_Asset.setText("100");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Strike", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Strike = new TextField(5);
        gridbaglayout.setConstraints(TextField_Strike, gridbagconstraints);
        add(TextField_Strike);
        TextField_Strike.setText("105");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Expiry", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Expiry = new TextField(5);
        gridbaglayout.setConstraints(TextField_Expiry, gridbagconstraints);
        add(TextField_Expiry);
        TextField_Expiry.setText("1");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Domestic Rate", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Rate = new TextField(5);
        gridbaglayout.setConstraints(TextField_Rate, gridbagconstraints);
        add(TextField_Rate);
        TextField_Rate.setText("6.2");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Foreign Rate", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Rate2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Rate2, gridbagconstraints);
        add(TextField_Rate2);
        TextField_Rate2.setText("7.5");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Dividend", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Dividend = new TextField(5);
        gridbaglayout.setConstraints(TextField_Dividend, gridbagconstraints);
        add(TextField_Dividend);
        TextField_Dividend.setText("4.5");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("FX", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_FX = new TextField(5);
        gridbaglayout.setConstraints(TextField_FX, gridbagconstraints);
        add(TextField_FX);
        TextField_FX.setText("1.65");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Asset Volatility", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vol = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vol, gridbagconstraints);
        add(TextField_Vol);
        TextField_Vol.setText("50.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("FX Volatility", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_VolFX = new TextField(5);
        gridbaglayout.setConstraints(TextField_VolFX, gridbagconstraints);
        add(TextField_VolFX);
        TextField_VolFX.setText("15.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Correlation", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Corr = new TextField(5);
        gridbaglayout.setConstraints(TextField_Corr, gridbagconstraints);
        add(TextField_Corr);
        TextField_Corr.setText("0.7");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Price", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Price = new TextField(5);
        gridbaglayout.setConstraints(TextField_Price, gridbagconstraints);
        add(TextField_Price);
        TextField_Price.setEditable(false);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Delta", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Delta = new TextField(5);
        gridbaglayout.setConstraints(TextField_Delta, gridbagconstraints);
        add(TextField_Delta);
        TextField_Delta.setEditable(false);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Gamma", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Gamma = new TextField(5);
        gridbaglayout.setConstraints(TextField_Gamma, gridbagconstraints);
        add(TextField_Gamma);
        TextField_Gamma.setEditable(false);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Vega", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vega = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vega, gridbagconstraints);
        add(TextField_Vega);
        TextField_Vega.setEditable(false);
        i++;
    }

    TextField TextField_Asset;
    TextField TextField_FX;
    TextField TextField_Strike;
    TextField TextField_Vega;
    TextField TextField_Delta;
    TextField TextField_Gamma;
    TextField TextField_Corr;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Rate;
    TextField TextField_VolFX;
    TextField TextField_Dividend;
    TextField TextField_Rate2;
    TextField TextField_Price;
    Choice PutCall;
    Label textLabel;
    double S;
    double X;
    double T;
    double D;
    double r_for;
    double b;
    double vol;
    double vol_FX;
    double r_dom;
    double corr;
    double OptionPrice;
    double FX;
    Random RND;
    String sPutCall;
}
