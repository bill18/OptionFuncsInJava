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

public class PartialFixedStrike extends JPanel {

    public static final long serialVersionUID = 10053;
    TextField TextField_Asset;
    TextField TextField_Start;
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
    double t1;
    String sPutCall;

    double PartialFixedStrikeLookback(int i, double d, double d1, double d2,
            double d3, double d4, double d5, double d6) {
        double d13 = 0.0D;
        double d7 = (Math.log(d / d1) + (d5 + (d6 * d6) / 2D) * d3) / (d6 * Math.sqrt(d3));
        double d8 = d7 - d6 * Math.sqrt(d3);
        double d9 = ((d5 + (d6 * d6) / 2D) * (d3 - d2)) / (d6 * Math.sqrt(d3 - d2));
        double d10 = d9 - d6 * Math.sqrt(d3 - d2);
        double d11 = (Math.log(d / d1) + (d5 + (d6 * d6) / 2D) * d2) / (d6 * Math.sqrt(d2));
        double d12 = d11 - d6 * Math.sqrt(d2);
        if (i == 0) {
            d13 = (((d * Math.exp((d5 - d4) * d3) * Common.CND(d7) - Math.exp(-d4 * d3) * d1 * Common.CND(d8)) + ((d * Math.exp(-d4 * d3) * (d6 * d6)) / (2D * d5)) * (-Math.pow(d / d1, (-2D * d5) / (d6 * d6)) * Common.CBND(d7 - (2D * d5 * Math.sqrt(d3)) / d6, -d11 + (2D * d5 * Math.sqrt(d2)) / d6, -Math.sqrt(d2 / d3)) + Math.exp(d5 * d3) * Common.CBND(d9, d7, Math.sqrt(1.0D - d2 / d3)))) - d * Math.exp((d5 - d4) * d3) * Common.CBND(-d9, d7, -Math.sqrt(1.0D - d2 / d3)) - d1 * Math.exp(-d4 * d3) * Common.CBND(d12, -d8, -Math.sqrt(d2 / d3))) + Math.exp(-d5 * (d3 - d2)) * (1.0D - (d6 * d6) / (2D * d5)) * d * Math.exp((d5 - d4) * d3) * Common.CND(d11) * Common.CND(-d10);
        } else if (i == 1) {
            d13 = ((d1 * Math.exp(-d4 * d3) * Common.CND(-d8) - d * Math.exp((d5 - d4) * d3) * Common.CND(-d7)) + ((d * Math.exp(-d4 * d3) * (d6 * d6)) / (2D * d5)) * (Math.pow(d / d1, (-2D * d5) / (d6 * d6)) * Common.CBND(-d7 + (2D * d5 * Math.sqrt(d3)) / d6, d11 - (2D * d5 * Math.sqrt(d2)) / d6, -Math.sqrt(d2 / d3)) - Math.exp(d5 * d3) * Common.CBND(-d9, -d7, Math.sqrt(1.0D - d2 / d3))) + d * Math.exp((d5 - d4) * d3) * Common.CBND(d9, -d7, -Math.sqrt(1.0D - d2 / d3)) + d1 * Math.exp(-d4 * d3) * Common.CBND(-d12, d8, -Math.sqrt(d2 / d3))) - Math.exp(-d5 * (d3 - d2)) * (1.0D - (d6 * d6) / (2D * d5)) * d * Math.exp((d5 - d4) * d3) * Common.CND(-d11) * Common.CND(d10);
        }
        return d13;
    }

    public PartialFixedStrike() {
        init();
    }

    public boolean action(Event event, Object obj) {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        s = TextField_Strike.getText();
        double1 = Double.valueOf(s);
        X = double1.doubleValue();
        s = TextField_Start.getText();
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
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        double d = PartialFixedStrikeLookback(i, S, X, t1, T, r, r - D, v);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d);
        TextField_Price.setText(s1);

        return true;
    }

    void buildConstraints(GridBagConstraints gridbagconstraints, int i, int j, int k, int l, int i1, int j1) {
        gridbagconstraints.gridx = i;
        gridbagconstraints.gridy = j;
        gridbagconstraints.gridwidth = k;
        gridbagconstraints.gridheight = l;
        gridbagconstraints.weightx = i1;
        gridbagconstraints.weighty = j1;
    }

    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }

    public void init() {
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
        textLabel = new Label("Start", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Start = new TextField(5);
        gridbaglayout.setConstraints(TextField_Start, gridbagconstraints);
        add(TextField_Start);
        TextField_Start.setText("2");
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

}
