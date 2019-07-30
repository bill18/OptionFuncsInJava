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

import javax.swing.JPanel;

import org.wwh.opt.models.common.Common;

public class TwoAssetCorr extends JPanel
{
	public static final long serialVersionUID = 10048;

    double TwoAssetCorr(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7, double d8, double d9, 
            double d10)
    {
        double d11 = d2;
        double d12 = d4 - d5;
        double d13 = d4 - d6;
        double d16 = 0.0D;
        double d14 = (Math.log(d / d11) + (d12 - (d7 * d7) / 2D) * d10) / (d7 * Math.sqrt(d10));
        double d15 = (Math.log(d1 / d3) + (d13 - (d8 * d8) / 2D) * d10) / (d8 * Math.sqrt(d10));
        if(i == 0)
            d16 = d1 * Math.exp((d13 - d4) * d10) * Common.CBND(d15 + d8 * Math.sqrt(d10), d14 + d9 * d8 * Math.sqrt(d10), d9) - d3 * Math.exp(-d4 * d10) * Common.CBND(d15, d14, d9);
        else
        if(i == 1)
            d16 = d3 * Math.exp(-d4 * d10) * Common.CBND(-d15, -d14, d9) - d1 * Math.exp((d13 - d4) * d10) * Common.CBND(-d15 - d8 * Math.sqrt(d10), -d14 - d9 * d8 * Math.sqrt(d10), d9);
        return d16;
    }

    public TwoAssetCorr()
    {
    	init();
    }

    public boolean action(Event event, Object obj)
    {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        s = TextField_Asset2.getText();
        double1 = Double.valueOf(s);
        S2 = double1.doubleValue();
        s = TextField_Strike.getText();
        double1 = Double.valueOf(s);
        X = double1.doubleValue();
        s = TextField_Strike2.getText();
        double1 = Double.valueOf(s);
        X2 = double1.doubleValue();
        s = TextField_Expiry.getText();
        double1 = Double.valueOf(s);
        T = double1.doubleValue();
        s = TextField_Rate.getText();
        double1 = Double.valueOf(s);
        r = double1.doubleValue() / 100D;
        s = TextField_Dividend.getText();
        double1 = Double.valueOf(s);
        D = double1.doubleValue() / 100D;
        s = TextField_Dividend2.getText();
        double1 = Double.valueOf(s);
        D2 = double1.doubleValue() / 100D;
        s = TextField_rho.getText();
        double1 = Double.valueOf(s);
        rho = double1.doubleValue();
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        s = TextField_Vol2.getText();
        double1 = Double.valueOf(s);
        v2 = double1.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        if(i == 0)
            sPutCall = "European Call";
        else
        if(i == 1)
            sPutCall = "European Put";
        double d = TwoAssetCorr(i, S, S2, X, X2, r, D, D2, v, v2, rho, T);
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
        textLabel = new Label("Asset 1", 0);
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
        textLabel = new Label("Asset 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Asset2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Asset2, gridbagconstraints);
        add(TextField_Asset2);
        TextField_Asset2.setText("90");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Strike 1", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Strike = new TextField(5);
        gridbaglayout.setConstraints(TextField_Strike, gridbagconstraints);
        add(TextField_Strike);
        TextField_Strike.setText("70");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Strike 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Strike2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Strike2, gridbagconstraints);
        add(TextField_Strike2);
        TextField_Strike2.setText("65");
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
        TextField_Rate.setText("5.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Dividend 1", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Dividend = new TextField(5);
        gridbaglayout.setConstraints(TextField_Dividend, gridbagconstraints);
        add(TextField_Dividend);
        TextField_Dividend.setText("8.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Dividend 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Dividend2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Dividend2, gridbagconstraints);
        add(TextField_Dividend2);
        TextField_Dividend2.setText("10.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Volatility 1", 0);
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
        textLabel = new Label("Volatility 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vol2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vol2, gridbagconstraints);
        add(TextField_Vol2);
        TextField_Vol2.setText("20.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Correlation", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_rho = new TextField(5);
        gridbaglayout.setConstraints(TextField_rho, gridbagconstraints);
        add(TextField_rho);
        TextField_rho.setText("0.50");
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
    TextField TextField_Asset2;
    TextField TextField_Strike;
    TextField TextField_rho;
    TextField TextField_Strike2;
    TextField TextField_Vol2;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend2;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    Choice PutCall;
    Label textLabel;
    double S;
    double X;
    double T;
    double S2;
    double D2;
    double D;
    double r;
    double v;
    double b;
    double rho;
    double v2;
    double X2;
    String sPutCall;
}
