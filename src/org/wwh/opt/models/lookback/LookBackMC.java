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
import java.util.Random;

import javax.swing.JPanel;

import org.wwh.opt.models.common.Common;

public class LookBackMC extends JPanel
{
	public static final long serialVersionUID = 10052;


    double LookbackMonte(int i, int j, double d, double d1, double d2, double d3, double d4, double d5, 
            double d6, double d7, int k, int l)
    {
        Sum = 0.0D;
        SumAnti = 0.0D;
        dt = d4 / (double)k;
        Drift = (d5 - d6 - (d7 * d7) / 2D) * dt;
        VolSqrdt = d7 * Math.sqrt(dt);
        for(int i1 = 1; i1 <= l; i1++)
        {
            St = d;
            StAnti = d;
            StMax = d;
            StMin = d;
            StAntiMax = d;
            StAntiMin = d;
            for(int j1 = 1; j1 <= k; j1++)
            {
                St = St * Math.exp(Drift + VolSqrdt * RND.nextGaussian());
                if(St > StMax)
                    StMax = St;
                else
                if(St < StMin)
                    StMin = St;
                StAnti = StAnti * Math.exp(Drift - VolSqrdt * RND.nextGaussian());
                if(StAnti > StAntiMax)
                    StAntiMax = StAnti;
                else
                if(StAnti < StAntiMin)
                    StAntiMin = StAnti;
            }

            if(j == 1)
                if(i == 0)
                {
                    Sum = Sum + Common.max(StMax - d3, 0.0D);
                    SumAnti = SumAnti + Common.max(StAntiMax - d3, 0.0D);
                } else
                if(i == 1)
                {
                    Sum = Sum + Common.max(d3 - StMin, 0.0D);
                    SumAnti = SumAnti + Common.max(d3 - StAntiMin, 0.0D);
                }
            if(j == 0)
                if(i == 0)
                {
                    Sum = Sum + Common.max(St - StMin, 0.0D);
                    SumAnti = SumAnti + Common.max(St - StAntiMin, 0.0D);
                } else
                if(i == 1)
                {
                    Sum = Sum + Common.max(StMax - StAnti, 0.0D);
                    SumAnti = SumAnti + Common.max(StAntiMax - StAnti, 0.0D);
                }
        }

        return Math.exp(-d5 * d4) * ((0.5D * (Sum + SumAnti)) / (double)l);
    }

    public LookBackMC()
    {
        RND = new Random();
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
        s = TextField_TimeNodes.getText();
        Integer integer = Integer.valueOf(s);
        n = integer.intValue();
        s = TextField_Simulations.getText();
        Integer integer1 = Integer.valueOf(s);
        NumSimulations = integer1.intValue();
        int i = PutCall.getSelectedIndex();
        int j = Variable.getSelectedIndex();
        double d = LookbackMonte(i, j, S, sMin, sMax, X, T, r, D, v, n, NumSimulations);
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
        textLabel = new Label("Strike", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
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
        textLabel = new Label("Look-back", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        Variable = new Choice();
        Variable.add("Fixed Strike");
        Variable.add("Floating Strike");
        gridbaglayout.setConstraints(Variable, gridbagconstraints);
        add(Variable);
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
        TextField_Rate.setText("2.87");
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
        TextField_Dividend.setText("6.64");
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
        TextField_Vol.setText("50.0");
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
        TextField_Minimum.setText("100");
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
        TextField_Maximum.setText("100");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("TimeNodes", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_TimeNodes = new TextField(5);
        gridbaglayout.setConstraints(TextField_TimeNodes, gridbagconstraints);
        add(TextField_TimeNodes);
        TextField_TimeNodes.setText("10");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Simulations", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Simulations = new TextField(5);
        gridbaglayout.setConstraints(TextField_Simulations, gridbagconstraints);
        add(TextField_Simulations);
        TextField_Simulations.setText("50");
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

    TextField TextField_Simulations;
    TextField TextField_Asset;
    TextField TextField_Strike;
    TextField TextField_Minimum;
    TextField TextField_Maximum;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    TextField TextField_TimeNodes;
    Choice PutCall;
    Choice Variable;
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
    double junk;
    int n;
    int NumSimulations;
    Random RND;
    double q;
    double Maht;
    double Sum;
    double SumAnti;
    double dt;
    double Drift;
    double VolSqrdt;
    double St;
    double StAnti;
    double StMax;
    double StMin;
    double StAntiMax;
    double StAntiMin;
    String sPutCall;
}
