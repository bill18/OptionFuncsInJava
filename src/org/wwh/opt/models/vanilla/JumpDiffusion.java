package org.wwh.opt.models.vanilla;

import java.awt.Choice;
import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.wwh.opt.models.common.ShekOption;

public class JumpDiffusion extends JPanel
{
	public static final long serialVersionUID = 10062;
	
    public JumpDiffusion()
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
        s = TextField_Minimum.getText();
        double1 = Double.valueOf(s);
        sMin = double1.doubleValue();
        s = TextField_Maximum.getText();
        double1 = Double.valueOf(s);
        sMax = double1.doubleValue() / 100D;
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        double d = JumpDiffusionPricer(S, sMin, sMax, X, T, r, v);
        double d1 = 0.01D * S;
        double d2 = 0.01D * v;
        double d3 = (1.0D / d1) * (JumpDiffusionPricer(S + d1, sMin, sMax, X, T, r, v) - JumpDiffusionPricer(S, sMin, sMax, X, T, r, v));
        double d4 = (1.0D / d2) * (JumpDiffusionPricer(S, sMin, sMax, X, T, r, v + d2) - JumpDiffusionPricer(S, sMin, sMax, X, T, r, v));
        double d5 = (1.0D / (d1 * d1)) * ((JumpDiffusionPricer(S + d1, sMin, sMax, X, T, r, v) - 2D * JumpDiffusionPricer(S, sMin, sMax, X, T, r, v)) + JumpDiffusionPricer(S - d1, sMin, sMax, X, T, r, v));
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d);
        TextField_Price.setText(s1);
        s1 = decimalformat.format(d3);
        TextField_Delta.setText(s1);
        s1 = decimalformat.format(d5);
        TextField_Gamma.setText(s1);
        s1 = decimalformat.format(d4);
        TextField_Vega.setText(s1);
    
        return true;
    }

    double JumpDiffusionPricer(double d, double d1, double d2, double d3, double d4, double d5, double d6)
    {
        String s = "";
        int i = PutCall.getSelectedIndex();
//        if(i == 0)
//            s = "European Call";
//        else
//        if(i == 1)
//            s = "European Put";
        double d7 = Math.sqrt((d2 * d6 * d6) / d1);
        double d9 = Math.sqrt(d6 * d6 - d1 * d7 * d7);
        double d8 = 0.0D;
        double d11 = 0.0D;
        int j = 0;
        do
        {
            int k = 1;
            double d10 = Math.sqrt(d9 * d9 + d7 * d7 * ((double)j / d4));
            for(int l = 1; l <= j; l++)
                k = l * k;

            d8 += ((Math.exp(-d1 * d4) * Math.pow(d1 * d4, j)) / (double)k) * ShekOption.blackPrice(i, d, d3, d5, 0.0D, d10, d4);
        } while(++j <= 10);
        d11 = d8;
        return d11;
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
        TextField_Rate.setText("5.0");
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
        textLabel = new Label("Jump/Year", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Minimum = new TextField(5);
        gridbaglayout.setConstraints(TextField_Minimum, gridbagconstraints);
        add(TextField_Minimum);
        TextField_Minimum.setText("3");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("% Total Volatility", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Maximum = new TextField(5);
        gridbaglayout.setConstraints(TextField_Maximum, gridbagconstraints);
        add(TextField_Maximum);
        TextField_Maximum.setText("40.5");
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
    TextField TextField_Strike;
    TextField TextField_Minimum;
    TextField TextField_Vega;
    TextField TextField_Gamma;
    TextField TextField_Delta;
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
