package org.wwh.opt.models.barrier;

import java.awt.Choice;
import java.awt.Event;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.wwh.opt.models.common.Common;
import org.wwh.opt.models.common.ShekOption;

public class BarrierSoftPanel extends JPanel
{
	public static final long serialVersionUID = 10003;

    public BarrierSoftPanel()
    {
        textLabel = new Label[15];
        TextField = new TextField[15];
        Value = new double[15];
        init();
    }

    double SoftBarrier(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7)
    {
        double d8 = 0.0D;
        double d9 = 0.0D;
        if(i == 0 || i == 1)
            d9 = 1.0D;
        else
            d9 = -1D;
        double d10 = (d6 + (d7 * d7) / 2D) / (d7 * d7);
        double d11 = Math.exp(-0.5D * d7 * d7 * d4 * (d10 + 0.5D) * (d10 - 0.5D));
        double d12 = Math.exp(-0.5D * d7 * d7 * d4 * (d10 - 0.5D) * (d10 - 1.5D));
        double d13 = Math.log((d3 * d3) / (d * d1)) / (d7 * Math.sqrt(d4)) + d10 * d7 * Math.sqrt(d4);
        double d14 = d13 - (d10 + 0.5D) * d7 * Math.sqrt(d4);
        double d15 = Math.log((d3 * d3) / (d * d1)) / (d7 * Math.sqrt(d4)) + (d10 - 1.0D) * d7 * Math.sqrt(d4);
        double d16 = d15 - (d10 - 0.5D) * d7 * Math.sqrt(d4);
        double d17 = Math.log((d2 * d2) / (d * d1)) / (d7 * Math.sqrt(d4)) + d10 * d7 * Math.sqrt(d4);
        double d18 = d17 - (d10 + 0.5D) * d7 * Math.sqrt(d4);
        double d19 = Math.log((d2 * d2) / (d * d1)) / (d7 * Math.sqrt(d4)) + (d10 - 1.0D) * d7 * Math.sqrt(d4);
        double d20 = d19 - (d10 - 0.5D) * d7 * Math.sqrt(d4);
        double d21 = ((d9 * 1.0D) / (d3 - d2)) * (((d * Math.exp((d6 - d5) * d4) * Math.pow(d, -2D * d10) * Math.pow(d * d1, d10 + 0.5D)) / (2D * (d10 + 0.5D))) * ((Math.pow((d3 * d3) / (d * d1), d10 + 0.5D) * Common.CND(d9 * d13) - d11 * Common.CND(d9 * d14) - Math.pow((d2 * d2) / (d * d1), d10 + 0.5D) * Common.CND(d9 * d17)) + d11 * Common.CND(d9 * d18)) - ((d1 * Math.exp(-d5 * d4) * Math.pow(d, -2D * (d10 - 1.0D)) * Math.pow(d * d1, d10 - 0.5D)) / (2D * (d10 - 0.5D))) * ((Math.pow((d3 * d3) / (d * d1), d10 - 0.5D) * Common.CND(d9 * d15) - d12 * Common.CND(d9 * d16) - Math.pow((d2 * d2) / (d * d1), d10 - 0.5D) * Common.CND(d9 * d19)) + d12 * Common.CND(d9 * d20)));
        if(i == 0 || i == 2)
            d8 = d21;
        else
        if(i == 1)
            d8 = ShekOption.blackPrice(ShekOption.EUROPEAN_CALL, d, d1, d5, d5 - d6, d7, d4) - d21;
        else
        if(i == 3)
            d8 = ShekOption.blackPrice(ShekOption.EUROPEAN_PUT, d, d1, d5, d5 - d6, d7, d4) - d21;
        return d8;
    }

    public boolean action(Event event, Object obj)
    {

        double d = 2;
		int n=2;
        do
        {
            String s = TextField[n].getText();
            Double double1 = Double.valueOf(s);
            Value[n] = double1.doubleValue();
        } while(++n <= 9);
        S = Value[2];
        X = Value[3];
        H = Value[4];
        K = Value[5];
        T = Value[6];
        r = Value[7] / 100D;
        D = Value[8] / 100D;
        v = Value[9] / 100D;
        indexPutCall = PutCall.getSelectedIndex();
        d = SoftBarrier(indexPutCall, S, X, H, K, T, r, r - D, v);
        double d1 = 0.01D * S;
        double d2 = 0.01D * v;
        double d3 = (1.0D / d1) * (SoftBarrier(indexPutCall, S + d1, X, H, K, T, r, r - D, v) - SoftBarrier(indexPutCall, S, X, H, K, T, r, r - D, v));
        double d4 = (1.0D / d1) * (SoftBarrier(indexPutCall, S - d1, X, H, K, T, r, r - D, v) - SoftBarrier(indexPutCall, S, X, H, K, T, r, r - D, v));
        double d5 = (1.0D / d1) * (d3 + d4);
        double d6 = (1.0D / d2) * (SoftBarrier(indexPutCall, S, X, H, K, T, r, r - D, v + d2) - SoftBarrier(indexPutCall, S, X, H, K, T, r, r - D, v));
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d);
        TextField[10].setText(s1);
        s1 = decimalformat.format(d3);
        TextField[11].setText(s1);
        s1 = decimalformat.format(d5);
        TextField[12].setText(s1);
        s1 = decimalformat.format(d6);
        TextField[13].setText(s1);
    
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
        PutCall.add("Down knock-in Call");
        PutCall.add("Down knock-out Call");
        PutCall.add("Up knock-in Put");
        PutCall.add("Up knock-out Put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
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
        textLabel[i] = new Label("Lower Barrier", 0);
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
        textLabel[i] = new Label("Upper Barrier", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("85");
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
        TextField[i].setText("5.0");
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
        TextField[i].setText("5.5");
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
        TextField[i].setText("4.0");
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
        TextField[i].setText("35.5");
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
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Delta", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setEditable(false);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Gamma", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setEditable(false);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Vega", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setEditable(false);
        i++;
    }

    Label textLabel[];
    TextField TextField[];
    double Value[];
    Choice PutCall;
    double S;
    double X;
    double H;
    double K;
    double T;
    double r;
    double b;
    double v;
    double D;
    double StandardBarrier;
    int indexPutCall;
}
