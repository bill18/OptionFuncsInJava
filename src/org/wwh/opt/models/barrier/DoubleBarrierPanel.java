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
import org.wwh.opt.models.common.ShekOption;

public class DoubleBarrierPanel extends JPanel
{
	public static final long serialVersionUID = 10008;

    public DoubleBarrierPanel()
    {
        textLabel = new Label[20];
        TextField = new TextField[20];
        Value = new double[20];
        init();
    }

    public boolean action(Event event, Object obj)
    {

        double d1 = 3;
		int n=3;
        do
        {
            String s = TextField[n].getText();
            Double double1 = Double.valueOf(s);
            Value[n] = double1.doubleValue();
        } while(++n < 13);
        S = Value[3];
        X = Value[4];
        L = Value[5];
        U = Value[6];
        T = Value[7];
        r = Value[8] / 100D;
        d = Value[9] / 100D;
        v = Value[10] / 100D;
        delta1 = Value[11];
        delta2 = Value[12];
        indexBarrier = Barrier.getSelectedIndex();
        switch(indexBarrier)
        {
        case 0: // '\0'
            dt = 0.0D;
            break;

        case 1: // '\001'
            dt = 0.0027397260273972603D;
            break;

        case 2: // '\002'
            dt = 0.019178082191780823D;
            break;

        case 3: // '\003'
            dt = 0.083333333333333329D;
            break;

        case 4: // '\004'
            dt = 0.25D;
            break;

        case 5: // '\005'
            dt = 0.5D;
            break;

        case 6: // '\006'
            dt = 1.0D;
            break;
        }
        L = Common.discreteAdjustedBarrier(S, L, v, dt);
        U = Common.discreteAdjustedBarrier(S, U, v, dt);
        indexPutCall = PutCall.getSelectedIndex();
        d1 = DoubleBarrier(indexPutCall, S, X, L, U, T, r, d, v, delta1, delta2);
        double d2 = 0.01D * S;
        double d3 = 0.01D * v;
        double d4 = (1.0D / d2) * (DoubleBarrier(indexPutCall, S + d2, X, L, U, T, r, d, v, delta1, delta2) - DoubleBarrier(indexPutCall, S, X, L, U, T, r, d, v, delta1, delta2));
        double d5 = (1.0D / d2) * (DoubleBarrier(indexPutCall, S - d2, X, L, U, T, r, d, v, delta1, delta2) - DoubleBarrier(indexPutCall, S, X, L, U, T, r, d, v, delta1, delta2));
        double d6 = (1.0D / d2) * (d4 + d5);
        double d7 = (1.0D / d3) * (DoubleBarrier(indexPutCall, S, X, L, U, T, r, d, v + d3, delta1, delta2) - DoubleBarrier(indexPutCall, S, X, L, U, T, r, d, v, delta1, delta2));
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d1);
        TextField[13].setText(s1);
        s1 = decimalformat.format(d4);
        TextField[14].setText(s1);
        s1 = decimalformat.format(d6);
        TextField[15].setText(s1);
        s1 = decimalformat.format(d7);
        TextField[16].setText(s1);
    
        return true;
    }

    double DoubleBarrier(int i, double d1, double d2, double d3, 
            double d4, double d5, double d6, double d7, double d8, double d9, double d10)
    {
        double d12 = d4 * Math.exp(d9 * d5);
        double d11 = d3 * Math.exp(d9 * d5);
        double d13 = 0.0D;
        double d14 = 0.0D;
        double d32 = d6 - d7;
        double d29 = 0.0D;
        if(i == 0 || i == 2)
        {
            for(double d30 = -5D; d30 < 5D; d30++)
            {
                double d15 = (Math.log((d1 * Math.pow(d4, 2D * d30)) / (d2 * Math.pow(d3, 2D * d30))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d17 = (Math.log((d1 * Math.pow(d4, 2D * d30)) / (d12 * Math.pow(d3, 2D * d30))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d19 = (Math.log(Math.pow(d3, 2D * d30 + 2D) / (d2 * d1 * Math.pow(d4, 2D * d30))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d21 = (Math.log(Math.pow(d3, 2D * d30 + 2D) / (d12 * d1 * Math.pow(d4, 2D * d30))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d23 = (2D * (d32 - d10 - d30 * (d9 - d10))) / (d8 * d8) + 1.0D;
                double d25 = (2D * d30 * (d9 - d10)) / (d8 * d8);
                double d27 = (2D * ((d32 - d10) + d30 * (d9 - d10))) / (d8 * d8) + 1.0D;
                d13 = (d13 + Math.pow(Math.pow(d4, d30) / Math.pow(d3, d30), d23) * Math.pow(d3 / d1, d25) * (Common.CND(d15) - Common.CND(d17))) - Math.pow(Math.pow(d3, d30 + 1.0D) / (Math.pow(d4, d30) * d1), d27) * (Common.CND(d19) - Common.CND(d21));
                d14 = (d14 + Math.pow(Math.pow(d4, d30) / Math.pow(d3, d30), d23 - 2D) * Math.pow(d3 / d1, d25) * (Common.CND(d15 - d8 * Math.sqrt(d5)) - Common.CND(d17 - d8 * Math.sqrt(d5)))) - Math.pow(Math.pow(d3, d30 + 1.0D) / (Math.pow(d4, d30) * d1), d27 - 2D) * (Common.CND(d19 - d8 * Math.sqrt(d5)) - Common.CND(d21 - d8 * Math.sqrt(d5)));
            }

            d29 = d1 * Math.exp((d32 - d6) * d5) * d13 - d2 * Math.exp(-d6 * d5) * d14;
        } else
        if(i == 1 || i == 3)
        {
            for(double d31 = -5D; d31 < 5D; d31++)
            {
                double d16 = (Math.log((d1 * Math.pow(d4, 2D * d31)) / (d11 * Math.pow(d3, 2D * d31))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d18 = (Math.log((d1 * Math.pow(d4, 2D * d31)) / (d2 * Math.pow(d3, 2D * d31))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d20 = (Math.log(Math.pow(d3, 2D * d31 + 2D) / (d11 * d1 * Math.pow(d4, 2D * d31))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d22 = (Math.log(Math.pow(d3, 2D * d31 + 2D) / (d2 * d1 * Math.pow(d4, 2D * d31))) + (d32 + (d8 * d8) / 2D) * d5) / (d8 * Math.sqrt(d5));
                double d24 = (2D * (d32 - d10 - d31 * (d9 - d10))) / (d8 * d8) + 1.0D;
                double d26 = ((2D * d31 * (d9 - d10)) / d8) * d8;
                double d28 = (2D * ((d32 - d10) + d31 * (d9 - d10))) / (d8 * d8) + 1.0D;
                d13 = (d13 + Math.pow(Math.pow(d4, d31) / Math.pow(d3, d31), d24) * Math.pow(d3 / d1, d26) * (Common.CND(d16) - Common.CND(d18))) - Math.pow(Math.pow(d3, d31 + 1.0D) / (Math.pow(d4, d31) * d1), d28) * (Common.CND(d20) - Common.CND(d22));
                d14 = (d14 + Math.pow(Math.pow(d4, d31) / Math.pow(d3, d31), d24 - 2D) * Math.pow(d3 / d1, d26) * (Common.CND(d16 - d8 * Math.sqrt(d5)) - Common.CND(d18 - d8 * Math.sqrt(d5)))) - Math.pow(Math.pow(d3, d31 + 1.0D) / (Math.pow(d4, d31) * d1), d28 - 2D) * (Common.CND(d20 - d8 * Math.sqrt(d5)) - Common.CND(d22 - d8 * Math.sqrt(d5)));
            }

            d29 = d2 * Math.exp(-d6 * d5) * d14 - d1 * Math.exp((d32 - d6) * d5) * d13;
        }
        if(i == 0 || i == 1)
            DoubleBarrier = d29;
        else
        if(i == 2)
            DoubleBarrier = ShekOption.blackPrice(ShekOption.EUROPEAN_CALL, d1, d2, d6, d7, d8, d5) - d29;
        else
        if(i == 3)
            DoubleBarrier = ShekOption.blackPrice(ShekOption.EUROPEAN_PUT, d1, d2, d6, d7, d8, d5) - d29;
        return DoubleBarrier;
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
        PutCall.add("Knock-Out Call");
        PutCall.add("Knock-Out Put");
        PutCall.add("Knock-In Call");
        PutCall.add("Knock-In Put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
        i = 2;
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
        Barrier.add("Continuously");
        Barrier.add("Daily");
        Barrier.add("Weekly");
        Barrier.add("Monthly");
        Barrier.add("Quarterly");
        Barrier.add("Semi-Annually");
        Barrier.add("Annually");
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
        textLabel[i] = new Label("Lower Barrier", 0);
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
        textLabel[i] = new Label("Upper Barrier", 0);
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
        textLabel[i] = new Label("Maturity", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("0.25");
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
        textLabel[i] = new Label("Upper Curvature", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel[i] = new Label("Lower Curvature", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("0");
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
    Choice Barrier;
    double S;
    double X;
    double L;
    double U;
    double T;
    double r;
    double d;
    double v;
    double delta1;
    double delta2;
    double DoubleBarrier;
    double dt;
    int indexPutCall;
    int indexBarrier;
}
