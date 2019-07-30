package org.wwh.opt.models.exotic;

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

public class Binomial3D extends JPanel
{
	public static final long serialVersionUID = 10041;

    double PayoffFunction(int i, int j, double d, double d1, double d2, double d3, double d4, double d5)
    {
        int k = 0;
        if(j == 0 || j == 1)
            k = 1;
        else
        if(j == 2 || j == 3)
            k = -1;
        if(i == 0)
            PayoffFunction = Math.max(0.0D, (double)k * (d2 * d - d3 * d1) - (double)k * d4);
        else
        if(i == 1)
            PayoffFunction = Math.max(0.0D, (double)k * Math.max(d2 * d, d3 * d1) - (double)k * d4);
        else
        if(i == 2)
            PayoffFunction = Math.max(0.0D, (double)k * Math.min(d2 * d, d3 * d1) - (double)k * d4);
        else
        if(i == 3)
            PayoffFunction = Math.max(0.0D, Math.max((double)k * (d2 * d - d4), (double)k * (d3 * d1 - d5)));
        else
        if(i == 4)
            PayoffFunction = Math.max(0.0D, Math.max((double)k * (d2 * d - d4), (double)k * (d5 - d3 * d1)));
        else
        if(i == 5)
            PayoffFunction = Math.max(0.0D, (double)k * (d2 * d + d3 * d1) - (double)k * d4);
        else
        if(i == 6)
            PayoffFunction = Math.max(0.0D, Math.max(0.0D, d2 * d - d3 * d1));
        return PayoffFunction;
    }

    public Binomial3D()
    {
        textLabel = new Label[20];
        TextField = new TextField[20];
        Value = new double[20];
        init();
    }

    double BinomialThreeD(int i, int j, double d, double d1, double d2, double d3, double d4, double d5, 
            double d6, double d7, double d8, double d9, double d10, double d11, double d12, 
            int k)
    {
        double ad[][] = new double[k + 1][k + 1];
        double d13 = d6 / (double)k;
        double d16 = d8 - (d10 * d10) / 2D;
        double d17 = d9 - (d11 * d11) / 2D;
        double d14 = Math.exp(d16 * d13 + d10 * Math.sqrt(d13));
        double d15 = Math.exp(d16 * d13 - d10 * Math.sqrt(d13));
        for(int j1 = 0; j1 <= k; j1++)
        {
            double d18 = (double)(2 * j1 - k) * Math.sqrt(d13);
            double d22 = d * Math.pow(d14, j1) * Math.pow(d15, k - j1);
            for(int l = 0; l <= k; l++)
            {
                double d20 = d12 * d18 + Math.sqrt(1.0D - d12 * d12) * (double)(2 * l - k) * Math.sqrt(d13);
                double d24 = d1 * Math.exp(d17 * (double)k * d13) * Math.exp(d11 * (d12 * d18 + Math.sqrt(1.0D - d12 * d12) * (double)(2 * l - k) * Math.sqrt(d13)));
                ad[j1][l] = PayoffFunction(j, i, d22, d24, d2, d3, d4, d5);
            }

        }

        for(int l1 = k - 1; l1 >= 0; l1--)
        {
            for(int k1 = 0; k1 <= l1; k1++)
            {
                double d19 = (double)(2 * k1 - l1) * Math.sqrt(d13);
                double d23 = d * Math.pow(d14, k1) * Math.pow(d15, l1 - k1);
                for(int i1 = 0; i1 <= l1; i1++)
                {
                    double d21 = d12 * d19 + Math.sqrt(1.0D - d12 * d12) * (double)(2 * i1 - l1) * Math.sqrt(d13);
                    double d25 = d1 * Math.exp(d17 * (double)l1 * d13) * Math.exp(d11 * d21);
                    ad[k1][i1] = 0.25D * (ad[k1][i1] + ad[k1 + 1][i1] + ad[k1][i1 + 1] + ad[k1 + 1][i1 + 1]) * Math.exp(-d7 * d13);
                    if(i == 1 || i == 3)
                        ad[k1][i1] = Math.max(ad[k1][i1], PayoffFunction(j, i, d23, d25, d2, d3, d4, d5));
                }

            }

        }

        return ad[0][0];
    }

    public boolean action(Event event, Object obj)
    {

        int i = 3;
        do
        {
            String s1 = TextField[i].getText();
            Double double1 = Double.valueOf(s1);
            Value[i] = double1.doubleValue();
        } while(++i <= 15);
        String s = TextField[16].getText();
        Integer integer = Integer.valueOf(s);
        n = integer.intValue();
        S1 = Value[3];
        Q1 = Value[4];
        S2 = Value[5];
        Q2 = Value[6];
        X1 = Value[7];
        X2 = Value[8];
        T = Value[9];
        r = Value[10] / 100D;
        b1 = r - Value[11] / 100D;
        b2 = r - Value[12] / 100D;
        v1 = Value[13] / 100D;
        v2 = Value[14] / 100D;
        rho = Value[15];
        indexPutCall = PutCall.getSelectedIndex();
        indexStyle = Style.getSelectedIndex();
        double d = BinomialThreeD(indexPutCall, indexStyle, S1, S2, Q1, Q2, X1, X2, T, r, b1, b2, v1, v2, rho, n);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s2 = decimalformat.format(d);
        TextField[17].setText(s2);
    
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
        textLabel[i] = new Label("Style", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        Style = new Choice();
        Style.add("Spread Option");
        Style.add("Maximum of Assets");
        Style.add("Minimum of Assets");
        Style.add("Dual-Strike");
        Style.add("Reverse Dual-Strike");
        Style.add("Portfolio");
        Style.add("Exchange Asset");
        gridbaglayout.setConstraints(Style, gridbagconstraints);
        add(Style);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Exercise", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        PutCall = new Choice();
        PutCall.add("European Call");
        PutCall.add("American Call");
        PutCall.add("European Put");
        PutCall.add("American Put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Asset 1", 0);
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
        textLabel[i] = new Label("Weight 1", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("1.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Asset 2", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("105");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Weight 2", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("1.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Strike 1", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("3");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Strike 2", 0);
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
        textLabel[i] = new Label("Expiry", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("5.00");
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
        textLabel[i] = new Label("Dividend 1", 0);
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
        textLabel[i] = new Label("Dividend 2", 0);
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
        textLabel[i] = new Label("Volatility 1", 0);
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
        textLabel[i] = new Label("Volatility 2", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("15.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Correlation", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("0.75");
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
    Choice Style;
    Choice Type;
    double S1;
    double S2;
    double Q1;
    double Q2;
    double X1;
    double X2;
    double T;
    double r;
    double b1;
    double b2;
    double v1;
    double v2;
    double rho;
    double PayoffFunction;
    int indexPutCall;
    int indexType;
    int indexStyle;
    int n;
}
