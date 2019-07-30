package org.wwh.opt.models.lookback;

import java.awt.Choice;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.wwh.opt.models.common.Common;
import org.wwh.opt.models.common.ShekOption;

public class PartialFloatStrike extends JPanel
{
	public static final long serialVersionUID = 10054;

    public PartialFloatStrike()
    {
    	init();
    }

    double PartialFloatingStrikeLookback(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7, double d8)
    {
        double d17 = 0.0D;
        double d18 = 0.0D;
        double d19 = 0.0D;
        double d20 = 0.0D;
        if(i == 0)
            d17 = d1;
        else
        if(i == 1)
            d17 = d2;
        double d9 = (Math.log(d / d17) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d10 = d9 - d7 * Math.sqrt(d4);
        double d11 = ((d6 + (d7 * d7) / 2D) * (d4 - d3)) / (d7 * Math.sqrt(d4 - d3));
        double d12 = d11 - d7 * Math.sqrt(d4 - d3);
        double d13 = (Math.log(d / d17) + (d6 + (d7 * d7) / 2D) * d3) / (d7 * Math.sqrt(d3));
        double d14 = d13 - d7 * Math.sqrt(d3);
        double d15 = Math.log(d8) / (d7 * Math.sqrt(d4));
        double d16 = Math.log(d8) / (d7 * Math.sqrt(d4 - d3));
        if(i == 0)
        {
            d18 = d * Math.exp((d6 - d5) * d4) * Common.CND(d9 - d15) - d8 * d17 * Math.exp(-d5 * d4) * Common.CND(d10 - d15);
            d19 = ((Math.exp(-d5 * d4) * (d7 * d7)) / (2D * d6)) * d8 * d * (Math.pow(d / d17, (-2D * d6) / (d7 * d7)) * Common.CBND(-d13 + (2D * d6 * Math.sqrt(d3)) / d7, (-d9 + (2D * d6 * Math.sqrt(d4)) / d7) - d15, Math.sqrt(d3 / d4)) - Math.exp(d6 * d4) * Math.pow(d8, (2D * d6) / (d7 * d7)) * Common.CBND(-d9 - d15, d11 + d16, -Math.sqrt(1.0D - d3 / d4))) + d * Math.exp((d6 - d5) * d4) * Common.CBND(-d9 + d15, d11 - d16, -Math.sqrt(1.0D - d3 / d4));
            d20 = Math.exp(-d5 * d4) * d8 * d17 * Common.CBND(-d14, d10 - d15, -Math.sqrt(d3 / d4)) - Math.exp(-d6 * (d4 - d3)) * Math.exp((d6 - d5) * d4) * (1.0D + (d7 * d7) / (2D * d6)) * d8 * d * Common.CND(d12 - d16) * Common.CND(-d13);
        } else
        if(i == 1)
        {
            d18 = d8 * d17 * Math.exp(-d5 * d4) * Common.CND(-d10 + d15) - d * Math.exp((d6 - d5) * d4) * Common.CND(-d9 + d15);
            d19 = ((-Math.exp(-d5 * d4) * (d7 * d7)) / (2D * d6)) * d8 * d * (Math.pow(d / d17, (-2D * d6) / (d7 * d7)) * Common.CBND(d13 - (2D * d6 * Math.sqrt(d3)) / d7, (d9 - (2D * d6 * Math.sqrt(d4)) / d7) + d15, Math.sqrt(d3 / d4)) - Math.exp(d6 * d4) * Math.pow(d8, (2D * d6) / (d7 * d7)) * Common.CBND(d9 + d15, -d11 - d16, -Math.sqrt(1.0D - d3 / d4))) - d * Math.exp((d6 - d5) * d4) * Common.CBND(d9 - d15, -d11 + d16, -Math.sqrt(1.0D - d3 / d4));
            d20 = -Math.exp(-d5 * d4) * d8 * d17 * Common.CBND(d14, -d10 + d15, -Math.sqrt(d3 / d4)) + Math.exp(-d6 * (d4 - d3)) * Math.exp((d6 - d5) * d4) * (1.0D + (d7 * d7) / (2D * d6)) * d8 * d * Common.CND(-d12 + d16) * Common.CND(d13);
        }
        return d18 + d19 + d20;
    }

    public boolean action(Event event, Object obj)
    {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        s = TextField_Period.getText();
        double1 = Double.valueOf(s);
        t1 = double1.doubleValue();
        s = TextField_Expiry.getText();
        double1 = Double.valueOf(s);
        T = double1.doubleValue();
        s = TextField_Rate.getText();
        double1 = Double.valueOf(s);
        r = double1.doubleValue() / 100D;
        s = TextField_Dividend.getText();
        double1 = Double.valueOf(s);
        D = double1.doubleValue() / 100D;
        s = TextField_Minimum.getText();
        double1 = Double.valueOf(s);
        sMin = double1.doubleValue();
        s = TextField_Maximum.getText();
        double1 = Double.valueOf(s);
        sMax = double1.doubleValue();
        s = TextField_lambda.getText();
        double1 = Double.valueOf(s);
        lambda = double1.doubleValue();
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        double d = PartialFloatingStrikeLookback(i, S, sMin, sMax, t1, T, r, r - D, v, lambda);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d);
        TextField_Price.setText(s1);
    
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
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Style", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        PutCall = new Choice();
        PutCall.add("Call-on-Min");
        PutCall.add("Put-on-Max");
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
        textLabel = new Label("Start", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Period = new TextField(5);
        gridbaglayout.setConstraints(TextField_Period, gridbagconstraints);
        add(TextField_Period);
        TextField_Period.setText("2");
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
        TextField_Expiry.setText("5");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Rate", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Rate = new TextField(5);
        gridbaglayout.setConstraints(TextField_Rate, gridbagconstraints);
        add(TextField_Rate);
        TextField_Rate.setText("5.00");
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
        TextField_Dividend.setText("4.50");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Volatility", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vol = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vol, gridbagconstraints);
        add(TextField_Vol);
        TextField_Vol.setText("20.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Minimum", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Minimum = new TextField(5);
        gridbaglayout.setConstraints(TextField_Minimum, gridbagconstraints);
        add(TextField_Minimum);
        TextField_Minimum.setText("10");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Maximum", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Maximum = new TextField(5);
        gridbaglayout.setConstraints(TextField_Maximum, gridbagconstraints);
        add(TextField_Maximum);
        TextField_Maximum.setText("150");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Fractional", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_lambda = new TextField(5);
        gridbaglayout.setConstraints(TextField_lambda, gridbagconstraints);
        add(TextField_lambda);
        TextField_lambda.setText("1");
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
    }

    TextField TextField_Asset;
    TextField TextField_Minimum;
    TextField TextField_Period;
    TextField TextField_Fractional;
    TextField TextField_lambda;
    TextField TextField_Maximum;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    Choice PutCall;
    Label textLabel;
    double S;
    double T;
    double sMin;
    double sMax;
    double D;
    double r;
    double v;
    double b;
    double t1;
    double T2;
    double lambda;
    String sPutCall;
}
