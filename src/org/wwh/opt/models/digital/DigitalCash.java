package org.wwh.opt.models.digital;

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

public class DigitalCash extends JPanel
{
	public static final long serialVersionUID = 10031;

    public DigitalCash()
    {
    	init();
    }

    double CashPricer(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6)
    {
        double d8 = 0.0D;
        double d7 = (Math.log(d / d1) + (d4 - (d5 * d5) / 2D) * d2) / (d5 * Math.sqrt(d2));
        if(i == 0)
            d8 = d6 * Math.exp(-d3 * d2) * Common.CND(d7);
        else
        if(i == 1)
            d8 = d6 * Math.exp(-d3 * d2) * Common.CND(-d7);
        return d8;
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
        s = TextField_Payout.getText();
        double1 = Double.valueOf(s);
        Payout = double1.doubleValue();
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        double d = CashPricer(i, S, X, T, r, r - D, v, Payout);
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
        TextField_Strike.setText("110");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Payoff", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Payout = new TextField(5);
        gridbaglayout.setConstraints(TextField_Payout, gridbagconstraints);
        add(TextField_Payout);
        TextField_Payout.setText("10");
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
        TextField_Expiry.setText("3");
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
        textLabel = new Label("Dividend", 0);
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

    TextField TextField_Asset;
    TextField TextField_Strike;
    TextField TextField_Payout;
    TextField TextField_Expiry2;
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
    double T2;
    double Payout;
    double D;
    double r;
    double v;
    double b;
    String sPutCall;
}
