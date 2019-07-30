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

public class FixedStrike extends JPanel
{
	public static final long serialVersionUID = 10050;

    public FixedStrike()
    {
    	init();
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
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        double d = FixedStrikeLookback(S, sMin, sMax, X, T, r, D, v);
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
        PutCall.add("Call");
        PutCall.add("Put");
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
        TextField_Strike.setText("95");
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
        TextField_Rate.setText("6.25");
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
        textLabel = new Label("Volatility", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vol = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vol, gridbagconstraints);
        add(TextField_Vol);
        TextField_Vol.setText("9.0");
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

    double FixedStrikeLookback(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7)
    {
        int i = PutCall.getSelectedIndex();
        double d13 = d5 - d6;
        double d12 = 0.0D;
        if(i == 0)
            d12 = d2;
        else
        if(i == 1)
            d12 = d1;
        double d8 = (Math.log(d / d3) + (d13 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d9 = d8 - d7 * Math.sqrt(d4);
        double d10 = (Math.log(d / d12) + (d13 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d11 = d10 - d7 * Math.sqrt(d4);
        double d14 = 0.0D;
        if((i == 0) & (d3 > d12))
            d14 = (d * Math.exp((d13 - d5) * d4) * Common.CND(d8) - d3 * Math.exp(-d5 * d4) * Common.CND(d9)) + ((d * Math.exp(-d5 * d4) * d7 * d7) / (2D * d13)) * (-Math.pow(d / d3, ((-2D * d13) / d7) * d7) * Common.CND(d8 - ((2D * d13) / d7) * Math.sqrt(d4)) + Math.exp(d13 * d4) * Common.CND(d8));
        else
        if((i == 0) & (d3 <= d12))
            d14 = ((Math.exp(-d5 * d4) * (d12 - d3) + d * Math.exp((d13 - d5) * d4) * Common.CND(d10)) - Math.exp(-d5 * d4) * d12 * Common.CND(d11)) + ((d * Math.exp(-d5 * d4) * (d7 * d7)) / (2D * d13)) * (-Math.pow(d / d12, (-2D * d13) / (d7 * d7)) * Common.CND(d10 - ((2D * d13) / d7) * Math.sqrt(d4)) + Math.exp(d13 * d4) * Common.CND(d10));
        else
        if((i == 1) & (d3 < d12))
            d14 = -d * Math.exp((d13 - d5) * d4) * Common.CND(-d8) + d3 * Math.exp(-d5 * d4) * Common.CND(-d8 + d7 * Math.sqrt(d4)) + ((d * Math.exp(-d5 * d4) * d7 * d7) / (2D * d13)) * (Math.pow(d / d3, ((-2D * d13) / d7) * d7) * Common.CND(-d8 + ((2D * d13) / d7) * Math.sqrt(d4)) - Math.exp(d13 * d4) * Common.CND(-d8));
        else
        if((i == 1) & (d3 >= d12))
            d14 = (Math.exp(-d5 * d4) * (d3 - d12) - d * Math.exp((d13 - d5) * d4) * Common.CND(-d10)) + Math.exp(-d5 * d4) * d12 * Common.CND(-d10 + d7 * Math.sqrt(d4)) + ((Math.exp(-d5 * d4) * d7 * d7) / (2D * d13)) * d * (Math.pow(d / d12, ((-2D * d13) / d7) * d7) * Common.CND(-d10 + ((2D * d13) / d7) * Math.sqrt(d4)) - Math.exp(d13 * d4) * Common.CND(-d10));
        return d14;
    }

    TextField TextField_Asset;
    TextField TextField_Strike;
    TextField TextField_Minimum;
    TextField TextField_Maximum;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    Choice PutCall;
    Label textLabel;
    double S;
    double X;
    double T;
    double sMin;
    double sMax;
    double D;
    double r;
    double v;
    double b;
    String sPutCall;
}
