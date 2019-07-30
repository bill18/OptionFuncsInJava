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
import org.wwh.opt.models.common.ShekOption;

public class TrinomialLookback extends JPanel
{
	public static final long serialVersionUID = 10055;

    double Lookback_Call(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, int j, 
            int k)
    {
        int l = k * j * 2 + 10;
        double ad[][][] = new double[j * 2 + 10][j * 2 + 10][l];
        double ad1[][] = new double[j * 2 + 10][j * 2 + 10];
        int ai[][] = new int[j * 2 + 10][j * 2 + 10];
        double ad2[][][] = new double[j * 2 + 10][j * 2 + 10][l];
        double d6 = d2 / (double)j;
        double d7 = d5 * Math.pow(3D * d6, 0.5D);
        double d8 = d3 - d4 - 0.5D * d5 * d5;
        double d9 = 0.5D * ((d5 * d5 * d6 + d8 * d8 * d6 * d6) / (d7 * d7) + (d8 * d6) / d7);
        double d10 = 1.0D - (d5 * d5 * d6 + d8 * d8 * d6 * d6) / (d7 * d7);
        double d11 = 0.5D * ((d5 * vol * d6 + d8 * d8 * d6 * d6) / (d7 * d7) - (d8 * d6) / d7);
        for(int i1 = 0; i1 <= j; i1++)
        {
            TopNode = i1;
            for(int i2 = -TopNode; i2 <= TopNode; i2++)
                ad1[i2 + TopNode][i1] = d * Math.pow(Math.exp(d7), i2);

        }

        ai[0][0] = 1;
        ad2[0][0][1] = ad1[0][0];
        for(int j1 = 1; j1 <= j; j1++)
        {
            TopNode = j1;
            for(int j2 = -TopNode; j2 <= TopNode; j2++)
            {
                ai[j2 + TopNode][j1] = 1 + k * (j1 - Math.abs(j2));
                if(j2 == -TopNode)
                    ad2[j2 + TopNode][j1][1] = ad1[0][0];
                else
                if(j2 == -TopNode + 1)
                    ad2[j2 + TopNode][j1][1] = ad1[0][0];
                else
                    ad2[j2 + TopNode][j1][1] = Common.max(ad2[(j2 + TopNode) - 2][j1 - 1][1], ad1[j2 + TopNode][j1]);
                if(j2 == TopNode)
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = ad1[j2 + TopNode][j1];
                else
                if(j2 == TopNode - 1)
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = ad1[j2 + TopNode][j1];
                else
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = Common.max(ad2[j2 + TopNode][j1 - 1][ai[j2 + TopNode][j1 - 1]], ad1[j2 + TopNode][j1]);
                if(Math.abs(j2) != j1)
                {
                    double d12 = (ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] - ad2[j2 + TopNode][j1][1]) / (double)(ai[j2 + TopNode][j1] - 1);
                    for(int k3 = 2; k3 <= ai[j2 + TopNode][j1] - 1; k3++)
                        ad2[j2 + TopNode][j1][k3] = ad2[j2 + TopNode][j1][1] + d12 * (double)(k3 - 1);

                }
            }

        }

        for(int k1 = -TopNode; k1 <= TopNode; k1++)
        {
            for(int k2 = 1; k2 <= ai[k1 + TopNode][j]; k2++)
                ad[k1 + TopNode][j][k2] = Common.max(ad2[k1 + TopNode][j][k2] - d1, 0.0D);

        }

        for(int l1 = j - 1; l1 >= 0; l1--)
        {
            for(int l2 = -l1; l2 <= l1; l2++)
            {
                for(int i3 = 1; i3 <= ai[l2 + l1][l1]; i3++)
                {
                    if(Math.abs(l2) != l1)
                    {
                        Fu = Common.max(ad2[l2 + l1][l1][i3], ad1[l2 + l1 + 2][l1 + 1]);
                        Fm = ad2[l2 + l1][l1][i3];
                        Fd = ad2[l2 + l1][l1][i3];
                    } else
                    {
                        Fu = ad2[l2 + l1][l1][i3];
                        Fm = ad2[l2 + l1][l1][i3];
                        Fd = ad2[l2 + l1][l1][i3];
                    }
                    int j3 = 0;
                    int l3 = 0;
                    int i4 = 0;
                    double d13 = -1D;
                    double d14 = -1D;
                    double d15 = -1D;
                    while(d13 < 0.0D) 
                    {
                        d13 = ad2[l2 + l1 + 2][l1 + 1][j3] - Fu;
                        j3++;
                    }
                    while(d14 < 0.0D) 
                    {
                        d14 = ad2[l2 + l1 + 1][l1 + 1][l3] - Fm;
                        l3++;
                    }
                    while(d15 < 0.0D) 
                    {
                        d15 = ad2[l2 + l1][l1 + 1][i4] - Fd;
                        i4++;
                    }
                    if((Math.abs(l2) != l1) & (Math.abs(l2) != l1 - 1))
                    {
                        if(j3 > 1)
                            Vu = ad[l2 + l1 + 2][l1 + 1][1] + ((ad[l2 + l1 + 2][l1 + 1][j3] - ad[l2 + l1 + 2][l1 + 1][1]) * (Fu - ad2[l2 + l1 + 2][l1 + 1][1])) / (ad2[l2 + l1 + 2][l1 + 1][j3] - ad2[l2 + l1 + 2][l1 + 1][1]);
                        else
                            Vu = ad[l2 + l1 + 2][l1 + 1][1];
                        if(l3 > 1)
                            Vm = ad[l2 + l1 + 1][l1 + 1][1] + ((ad[l2 + l1 + 1][l1 + 1][l3] - ad[l2 + l1 + 1][l1 + 1][1]) * (Fm - ad2[l2 + l1 + 1][l1 + 1][1])) / (ad2[l2 + l1 + 1][l1 + 1][l3] - ad2[l2 + l1 + 1][l1 + 1][1]);
                        else
                            Vm = ad[l2 + l1 + 1][l1 + 1][1];
                        if(i4 > 1)
                            Vd = ad[l2 + l1][l1 + 1][1] + ((ad[l2 + l1][l1 + 1][i4] - ad[l2 + l1][l1 + 1][1]) * (Fd - ad2[l2 + l1][l1 + 1][1])) / (ad2[l2 + l1][l1 + 1][i4] - ad2[l2 + l1][l1 + 1][1]);
                        else
                            Vd = ad[l2 + l1][l1 + 1][1];
                    } else
                    {
                        Vu = ad[l2 + l1 + 2][l1 + 1][1];
                        Vm = ad[l2 + l1 + 1][l1 + 1][1];
                        Vd = ad[l2 + l1][l1 + 1][1];
                    }
                    ad[l2 + l1][l1][i3] = Math.exp(-d3 * d6) * (d9 * Vu + d10 * Vm + d11 * Vd);
                    if(i == 2)
                        ad[l2 + l1][l1][i3] = Common.max(ad2[l2 + l1][l1][i3] - d1, ad[l2 + l1][l1][i3]);
                }

            }

        }

        return ad[0][0][1];
    }

    double Lookback_Put(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, int j, 
            int k)
    {
        int l = k * j * 2 + 10;
        double ad[][][] = new double[j * 2 + 10][j * 2 + 10][l];
        double ad1[][] = new double[j * 2 + 10][j * 2 + 10];
        int ai[][] = new int[j * 2 + 10][j * 2 + 10];
        double ad2[][][] = new double[j * 2 + 10][j * 2 + 10][l];
        double d6 = d2 / (double)j;
        double d7 = d5 * Math.pow(3D * d6, 0.5D);
        double d8 = d3 - d4 - 0.5D * d5 * d5;
        double d9 = 0.5D * ((d5 * d5 * d6 + d8 * d8 * d6 * d6) / (d7 * d7) + (d8 * d6) / d7);
        double d10 = 1.0D - (d5 * d5 * d6 + d8 * d8 * d6 * d6) / (d7 * d7);
        double d11 = 0.5D * ((d5 * vol * d6 + d8 * d8 * d6 * d6) / (d7 * d7) - (d8 * d6) / d7);
        for(int i1 = 0; i1 <= j; i1++)
        {
            TopNode = i1;
            for(int i2 = -TopNode; i2 <= TopNode; i2++)
                ad1[i2 + TopNode][i1] = d * Math.pow(Math.exp(d7), i2);

        }

        ai[0][0] = 1;
        ad2[0][0][1] = ad1[0][0];
        for(int j1 = 1; j1 <= j; j1++)
        {
            TopNode = j1;
            for(int j2 = -TopNode; j2 <= TopNode; j2++)
            {
                ai[j2 + TopNode][j1] = 1 + k * (j1 - Math.abs(j2));
                if(j2 == -TopNode)
                    ad2[j2 + TopNode][j1][1] = ad1[j2 + TopNode][j1];
                else
                if(j2 == -TopNode + 1)
                    ad2[j2 + TopNode][j1][1] = ad1[j2 + TopNode][j1];
                else
                    ad2[j2 + TopNode][j1][1] = Common.min(ad2[(j2 + TopNode) - 2][j1 - 1][1], ad1[j2 + TopNode][j1]);
                if(j2 == TopNode)
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = ad1[0][0];
                else
                if(j2 == TopNode - 1)
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = ad1[0][0];
                else
                    ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] = Common.min(ad2[j2 + TopNode][j1 - 1][ai[j2 + TopNode][j1 - 1]], ad1[j2 + TopNode][j1]);
                if(Math.abs(j2) != j1)
                {
                    double d12 = (ad2[j2 + TopNode][j1][ai[j2 + TopNode][j1]] - ad2[j2 + TopNode][j1][1]) / (double)(ai[j2 + TopNode][j1] - 1);
                    for(int k3 = 2; k3 <= ai[j2 + TopNode][j1] - 1; k3++)
                        ad2[j2 + TopNode][j1][k3] = ad2[j2 + TopNode][j1][1] + d12 * (double)(k3 - 1);

                }
            }

        }

        for(int k1 = -TopNode; k1 <= TopNode; k1++)
        {
            for(int k2 = 1; k2 <= ai[k1 + TopNode][j]; k2++)
                ad[k1 + TopNode][j][k2] = Common.max(-1D * (ad2[k1 + TopNode][j][k2] - d1), 0.0D);

        }

        for(int l1 = j - 1; l1 >= 0; l1--)
        {
            for(int l2 = -l1; l2 <= l1; l2++)
            {
                for(int i3 = 1; i3 <= ai[l2 + l1][l1]; i3++)
                {
                    if(Math.abs(l2) != l1)
                    {
                        Fu = ad2[l2 + l1][l1][i3];
                        Fm = ad2[l2 + l1][l1][i3];
                        Fd = Common.min(ad2[l2 + l1][l1][i3], ad1[l2 + l1][l1 + 1]);
                    } else
                    {
                        Fu = ad2[l2 + l1][l1][i3];
                        Fm = ad2[l2 + l1][l1][i3];
                        Fd = ad2[l2 + l1][l1][i3];
                    }
                    int j3 = 0;
                    int l3 = 0;
                    int i4 = 0;
                    double d13 = -1D;
                    double d14 = -1D;
                    double d15 = -1D;
                    while(d13 < 0.0D) 
                    {
                        d13 = ad2[l2 + l1 + 2][l1 + 1][j3] - Fu;
                        j3++;
                    }
                    while(d14 < 0.0D) 
                    {
                        d14 = ad2[l2 + l1 + 1][l1 + 1][l3] - Fm;
                        l3++;
                    }
                    while(d15 < 0.0D) 
                    {
                        d15 = ad2[l2 + l1 + 1][l1 + 1][i4] - Fd;
                        i4++;
                    }
                    if((Math.abs(l2) != l1) & (Math.abs(l2) != l1 - 1))
                    {
                        if(j3 > 1)
                            Vu = ad[l2 + l1 + 2][l1 + 1][1] + ((ad[l2 + l1 + 2][l1 + 1][j3] - ad[l2 + l1 + 2][l1 + 1][1]) * (Fu - ad2[l2 + l1 + 2][l1 + 1][1])) / (ad2[l2 + l1 + 2][l1 + 1][j3] - ad2[l2 + l1 + 2][l1 + 1][1]);
                        else
                            Vu = ad[l2 + l1 + 2][l1 + 1][1];
                        if(l3 > 1)
                            Vm = ad[l2 + l1 + 1][l1 + 1][1] + ((ad[l2 + l1 + 1][l1 + 1][l3] - ad[l2 + l1 + 1][l1 + 1][1]) * (Fm - ad2[l2 + l1 + 1][l1 + 1][1])) / (ad2[l2 + l1 + 1][l1 + 1][l3] - ad2[l2 + l1 + 1][l1 + 1][1]);
                        else
                            Vm = ad[l2 + l1 + 1][l1 + 1][1];
                        if(i4 > 1)
                            Vd = ad[l2 + l1][l1 + 1][1] + ((ad[l2 + l1][l1 + 1][i4] - ad[l2 + l1][l1 + 1][1]) * (Fd - ad2[l2 + l1][l1 + 1][1])) / (ad2[l2 + l1][l1 + 1][i4] - ad2[l2 + l1][l1 + 1][1]);
                        else
                            Vd = ad[l2 + l1][l1 + 1][1];
                    } else
                    {
                        Vu = ad[l2 + l1 + 2][l1 + 1][1];
                        Vm = ad[l2 + l1 + 1][l1 + 1][1];
                        Vd = ad[l2 + l1][l1 + 1][1];
                    }
                    ad[l2 + l1][l1][i3] = Math.exp(-d3 * d6) * (d9 * Vu + d10 * Vm + d11 * Vd);
                    if(i == 3)
                        ad[l2 + l1][l1][i3] = Common.max(-1D * (ad2[l2 + l1][l1][i3] - d1), ad[l2 + l1][l1][i3]);
                }

            }

        }

        return ad[0][0][1];
    }

    public TrinomialLookback()
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
        s = TextField_Alpha.getText();
        Integer integer = Integer.valueOf(s);
        Alpha = integer.intValue();
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        vol = double1.doubleValue() / 100D;
        s = TextField_TimeNodes.getText();
        Integer integer1 = Integer.valueOf(s);
        n = integer1.intValue();
        int i = PutCall.getSelectedIndex();
        if(i == 0 || i == 2)
            OptionPrice = Lookback_Call(i, S, X, T, r, D, vol, n, Alpha);
        else
            OptionPrice = Lookback_Put(i, S, X, T, r, D, vol, n, Alpha);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(OptionPrice);
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
        PutCall.add("European Call");
        PutCall.add("European Put");
        PutCall.add("American Call");
        PutCall.add("American Put");
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
        TextField_Expiry.setText("1");
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
        textLabel = new Label("Alpha", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Alpha = new TextField(5);
        gridbaglayout.setConstraints(TextField_Alpha, gridbagconstraints);
        add(TextField_Alpha);
        TextField_Alpha.setText("10");
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
    TextField TextField_Alpha;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    TextField TextField_TimeNodes;
    Choice PutCall;
    Label textLabel;
    double S;
    double X;
    double T;
    double D;
    double r;
    double b;
    double vol;
    double Fu;
    double Fm;
    double Fd;
    double Vu;
    double Vm;
    double Vd;
    double OptionPrice;
    int n;
    int Alpha;
    int TopNode;
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
