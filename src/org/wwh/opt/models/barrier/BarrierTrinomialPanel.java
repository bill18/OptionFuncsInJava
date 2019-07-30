package org.wwh.opt.models.barrier;

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

public class BarrierTrinomialPanel extends JPanel
{
	public static final long serialVersionUID = 10005;

    double StandardBarrierTri(int i, int j, double d, double d1, double d2, double d3, double d4, double d5, 
            double d6, double d7, int k)
    {
        double ad[][] = new double[k * 3][k * 3];
        double ad1[][] = new double[k * 3][k * 3];
        double ad2[][] = new double[k * 3][k * 3];
        double d8 = d4 / (double)k;
        double d9 = d7 * Math.sqrt(3D * d8);
        double d10 = d5 - d6 - 0.5D * d7 * d7;
        double d11 = 0.5D * ((d7 * d7 * d8 + d10 * d10 * d8 * d8) / (d9 * d9) + (d10 * d8) / d9);
        double d12 = 1.0D - (d7 * d7 * d8 + d10 * d10 * d8 * d8) / (d9 * d9);
        double d13 = 0.5D * ((d7 * d7 * d8 + d10 * d10 * d8 * d8) / (d9 * d9) - (d10 * d8) / d9);
        for(int j1 = k; j1 >= 1; j1--)
        {
            int l = j1;
            for(int j2 = -l; j2 <= l; j2++)
            {
                ad1[j2 + l][j1] = d * Math.pow(Math.exp(d9), j2);
                if((d2 > d) & (j == 1) & (ad1[j2 + l][j1] >= d2))
                    ad2[j2 + l][j1] = 0.0D;
                else
                if((d2 < d) & (j == 1) & (ad1[j2 + l][j1] <= d2))
                    ad2[j2 + l][j1] = 0.0D;
                else
                if((d2 > d) & (j == 2) & (ad1[j2 + l][j1] < d2))
                    ad2[j2 + l][j1] = 0.0D;
                else
                if((d2 < d) & (j == 2) & (ad1[j2 + l][j1] > d2))
                    ad2[j2 + l][j1] = 0.0D;
                else
                if(j == 0)
                    ad2[j2 + l][j1] = 1.0D;
                else
                    ad2[j2 + l][j1] = 1.0D;
            }

        }

        ad1[0][0] = d;
        if(i == 0 || i == 2)
        {
            for(int k1 = -k; k1 <= k; k1++)
                ad[k1 + k][k] = ad2[k1 + k][k] * Math.max(ad1[k1 + k][k] - d1, 0.0D);

        } else
        if(i == 1 || i == 3)
        {
            for(int l1 = -k; l1 <= k; l1++)
                ad[l1 + k][k] = ad2[l1 + k][k] * Math.max(d1 - ad1[l1 + k][k], 0.0D);

        }
        for(int i2 = k - 1; i2 >= 0; i2--)
        {
            int i1 = i2;
            for(int k2 = -i1; k2 <= i1; k2++)
            {
                ad[k2 + i1][i2] = ad2[k2 + k][k] * (Math.exp(-d5 * d8) * (d13 * ad[k2 + i1][i2 + 1] + d12 * ad[k2 + i1 + 1][i2 + 1] + d11 * ad[k2 + i1 + 2][i2 + 1]));
                if(i == 2)
                    ad[k2 + i1][i2] = Math.max(ad[k2 + i1][i2], ad1[k2 + i1][i2] - d1);
                else
                if(i == 3)
                    ad[k2 + i1][i2] = Math.max(ad[k2 + i1][i2], d1 - ad1[k2 + i1][i2]);
                if((ad1[k2 + k][k] < d2) & (ad1[k2 + k + 1][k] >= d2) & (j != 0))
                    ad[k2 + k][k] = ((d3 - ad[k2 + k][k]) / (ad1[k2 + k + 1][k] - ad1[k2 + k][k])) * (d2 - ad1[k2 + k][k]);
            }

        }

        return ad[0][0];
    }

    public BarrierTrinomialPanel()
    {
        textLabel = new Label[15];
        TextField = new TextField[15];
        Value = new double[15];
        init();
    }

    public boolean action(Event event, Object obj)
    {

        int i = 3;
        do
        {
            String s1 = TextField[i].getText();
            Double double1 = Double.valueOf(s1);
            Value[i] = double1.doubleValue();
        } while(++i < 11);
        String s = TextField[11].getText();
        Integer integer = Integer.valueOf(s);
        n = integer.intValue();
        S = Value[3];
        X = Value[4];
        H = Value[5];
        K = Value[6];
        T = Value[7];
        r = Value[8] / 100D;
        b = Value[9] / 100D;
        v = Value[10] / 100D;
        indexPutCall = PutCall.getSelectedIndex();
        indexBarrier = Barrier.getSelectedIndex();
        double d = StandardBarrierTri(indexPutCall, indexBarrier, S, X, H, K, T, r, b, v, n);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s2 = decimalformat.format(d);
        TextField[12].setText(s2);
    
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
        // setBackground(Color.gray);
        GridBagLayout gridbaglayout = new GridBagLayout();
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        setLayout(gridbaglayout);
        int i = 1;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Style", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
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
        textLabel[i] = new Label("Barrier", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        Barrier = new Choice();
        Barrier.add("None");
        Barrier.add("Knock-Out");
        Barrier.add("Knock-In");
        gridbaglayout.setConstraints(Barrier, gridbagconstraints);
        add(Barrier);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Asset", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("100");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Strike", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("100");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Barrier", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("90");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Rebate", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("50");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Maturity", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("1");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Rate", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("5.50");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Dividend", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("4.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Volatility", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("35.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("TimeNodes", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("10");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Price", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setEditable(false);
    }

    Label textLabel[];
    TextField TextField[];
    double Value[];
    Choice PutCall;
    Choice Barrier;
    double S;
    double X;
    double H;
    double K;
    double T;
    double r;
    double b;
    double v;
    double StandardBarrier;
    int indexPutCall;
    int indexBarrier;
    int n;
}
