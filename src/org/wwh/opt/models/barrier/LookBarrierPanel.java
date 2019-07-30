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

import org.wwh.opt.models.common.Common;


public class LookBarrierPanel extends JPanel
{
	public static final long serialVersionUID = 10009;

    public LookBarrierPanel()
    {
        textLabel = new Label[20];
        TextField = new TextField[20];
        Value = new double[20];
        call_PartialFixedStrike = new PartialFixedStrikePanel();
        init();
    }

    double LookBarrier(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7)
    {
        double d13 = 0.0D;
        double d14 = 0.0D;
        double d8 = Math.log(d2 / d);
        double d9 = Math.log(d1 / d);
        double d10 = d6 - (d7 * d7) / 2D;
        double d11 = d6 + (d7 * d7) / 2D;
        double d12 = Math.sqrt(d3 / d4);
        if(i == 0 || i == 1)
        {
            d13 = 1.0D;
            d14 = Math.min(d8, d9);
        } else
        if(i == 2 || i == 3)
        {
            d13 = -1D;
            d14 = Math.max(d8, d9);
        }
        double d15 = Common.CND((d13 * (d8 - d11 * d3)) / (d7 * Math.sqrt(d3))) - Math.exp((2D * d11 * d8) / (d7 * d7)) * Common.CND((d13 * (-d8 - d11 * d3)) / (d7 * Math.sqrt(d3))) - (Common.CND((d13 * (d14 - d11 * d3)) / (d7 * Math.sqrt(d3))) - Math.exp((2D * d11 * d8) / (d7 * d7)) * Common.CND((d13 * (d14 - 2D * d8 - d11 * d3)) / (d7 * Math.sqrt(d3))));
        double d16 = Common.CND((d13 * (d8 - d10 * d3)) / (d7 * Math.sqrt(d3))) - Math.exp((2D * d10 * d8) / (d7 * d7)) * Common.CND((d13 * (-d8 - d10 * d3)) / (d7 * Math.sqrt(d3))) - (Common.CND((d13 * (d14 - d10 * d3)) / (d7 * Math.sqrt(d3))) - Math.exp((2D * d10 * d8) / (d7 * d7)) * Common.CND((d13 * (d14 - 2D * d8 - d10 * d3)) / (d7 * Math.sqrt(d3))));
        double d18 = d * Math.exp((d6 - d5) * d4) * (1.0D + (d7 * d7) / (2D * d6)) * (Common.CBND((d13 * (d14 - d11 * d3)) / (d7 * Math.sqrt(d3)), (d13 * (-d9 + d11 * d4)) / (d7 * Math.sqrt(d4)), -d12) - Math.exp((2D * d11 * d8) / (d7 * d7)) * Common.CBND((d13 * (d14 - 2D * d8 - d11 * d3)) / (d7 * Math.sqrt(d3)), (d13 * ((2D * d8 - d9) + d11 * d4)) / (d7 * Math.sqrt(d4)), -d12));
        double d19 = -Math.exp(-d5 * d4) * d1 * (Common.CBND((d13 * (d14 - d10 * d3)) / (d7 * Math.sqrt(d3)), (d13 * (-d9 + d10 * d4)) / (d7 * Math.sqrt(d4)), -d12) - Math.exp((2D * d10 * d8) / (d7 * d7)) * Common.CBND((d13 * (d14 - 2D * d8 - d10 * d3)) / (d7 * Math.sqrt(d3)), (d13 * ((2D * d8 - d9) + d10 * d4)) / (d7 * Math.sqrt(d4)), -d12));
        double d20 = ((-Math.exp(-d5 * d4) * d7 * d7) / (2D * d6)) * (d * Math.pow(d / d1, (-2D * d6) / (d7 * d7)) * Common.CBND((d13 * (d14 + d10 * d3)) / (d7 * Math.sqrt(d3)), (d13 * (-d9 - d10 * d4)) / (d7 * Math.sqrt(d4)), -d12) - d2 * Math.pow(d2 / d1, (-2D * d6) / (d7 * d7)) * Common.CBND((d13 * ((d14 - 2D * d8) + d10 * d3)) / (d7 * Math.sqrt(d3)), (d13 * (2D * d8 - d9 - d10 * d4)) / (d7 * Math.sqrt(d4)), -d12));
        double d21 = d * Math.exp((d6 - d5) * d4) * ((1.0D + (d7 * d7) / (2D * d6)) * Common.CND((d13 * d11 * (d4 - d3)) / (d7 * Math.sqrt(d4 - d3))) + Math.exp(-d6 * (d4 - d3)) * (1.0D - (d7 * d7) / (2D * d6)) * Common.CND((d13 * (-d10 * (d4 - d3))) / (d7 * Math.sqrt(d4 - d3)))) * d15 - Math.exp(-d5 * d4) * d1 * d16;
        double d17 = d13 * (d18 + d19 + d20 + d21);
        double d22 = 0.0D;
        if(i == 0 || i == 2)
            d22 = d17;
        else
        if(i == 1)
            d22 = call_PartialFixedStrike.PartialFixedStrikeLookback(0, d, d1, d3, d4, d5, d6, d7) - d17;
        else
        if(i == 3)
            d22 = call_PartialFixedStrike.PartialFixedStrikeLookback(1, d, d1, d3, d4, d5, d6, d7) - d17;
        return d22;
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
        } while(++n < 10);
        S = Value[2];
        X = Value[3];
        H = Value[4];
        t1 = Value[5];
        T2 = Value[6];
        r = Value[7] / 100D;
        D = Value[8] / 100D;
        v = Value[9] / 100D;
        indexPutCall = PutCall.getSelectedIndex();
        d = LookBarrier(indexPutCall, S, X, H, t1, T2, r, r - D, v);
        double d1 = 0.01D * S;
        double d2 = 0.01D * v;
        double d3 = (1.0D / d1) * (LookBarrier(indexPutCall, S + d1, X, H, t1, T2, r, r - D, v) - LookBarrier(indexPutCall, S, X, H, t1, T2, r, r - D, v));
        double d4 = (1.0D / d1) * (LookBarrier(indexPutCall, S - d1, X, H, t1, T2, r, r - D, v) - LookBarrier(indexPutCall, S, X, H, t1, T2, r, r - D, v));
        double d5 = (1.0D / d1) * (d3 + d4);
        double d6 = (1.0D / d2) * (LookBarrier(indexPutCall, S, X, H, t1, T2, r, r - D, v + d2) - LookBarrier(indexPutCall, S, X, H, t1, T2, r, r - D, v));
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
        PutCall.add("Up Knock-out call");
        PutCall.add("Up Knock-in call");
        PutCall.add("Down Knock-out put");
        PutCall.add("Down Knock-in put");
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
        textLabel[i] = new Label("B-Tenor", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("0.5");
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
        TextField[i].setText("5");
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
    double t1;
    double T2;
    int indexPutCall;
    PartialFixedStrikePanel call_PartialFixedStrike;
}
