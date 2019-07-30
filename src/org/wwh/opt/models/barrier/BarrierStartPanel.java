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

public class BarrierStartPanel extends JPanel {

    public static final long serialVersionUID = 10004;

    public BarrierStartPanel() {
        textLabel = new Label[15];
        TextField = new TextField[15];
        Value = new double[15];
        init();
    }

    public boolean action(Event event, Object obj) {

        int i = 2;
        do {
            String s = TextField[i].getText();
            Double double1 = Double.valueOf(s);
            Value[i] = double1.doubleValue();
        } while (++i < 10);
        S = Value[2];
        X = Value[3];
        H = Value[4];
        t = Value[5];
        T = Value[6];
        r = Value[7] / 100D;
        D = Value[8] / 100D;
        v = Value[9] / 100D;
        indexPutCall = PutCall.getSelectedIndex();
        indexType = 0;
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        double d = partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v);
        String s1 = decimalformat.format(d);
        TextField[10].setText(s1);
        double d1 = (1.0D / (S * 0.01D)) * (partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v) - partialBarrier(indexType, indexPutCall, S - S * 0.01D, X, H, t, T, r, r - D, v));
        s1 = decimalformat.format(d1);
        TextField[11].setText(s1);
        double d2 = (1.0D / (S * 0.01D * (S * 0.01D))) * ((partialBarrier(indexType, indexPutCall, S + 0.01D * S, X, H, t, T, r, r - D, v) - 2D * partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v)) + partialBarrier(indexType, indexPutCall, S - S * 0.01D, X, H, t, T, r, r - D, v));
        s1 = decimalformat.format(d2);
        TextField[12].setText(s1);
        double d3 = (1.0D / (v * 0.01D)) * (partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v) - partialBarrier(indexType, indexPutCall, S, X, H, t, T, r, r - D, v - v * 0.01D));
        s1 = decimalformat.format(d3);
        TextField[13].setText(s1);

        return true;
    }

    double partialBarrier(int i, int j, double d, double d1, double d2, double d3, double d4, double d5,
            double d6, double d7) {
        double d22 = 0.0D;
        if ((i == 0) & (j == 1)) {
            d22 = 1.0D;
        } else if ((i == 0) & (j == 0)) {
            d22 = -1D;
        }
        double d8 = (Math.log(d / d1) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d9 = d8 - d7 * Math.sqrt(d4);
        double d10 = (Math.log(d / d1) + 2D * Math.log(d2 / d) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d11 = d10 - d7 * Math.sqrt(d4);
        double d12 = (Math.log(d / d2) + (d6 + (d7 * d7) / 2D) * d3) / (d7 * Math.sqrt(d3));
        double d13 = d12 - d7 * Math.sqrt(d3);
        double d14 = d12 + (2D * Math.log(d2 / d)) / (d7 * Math.sqrt(d3));
        double d15 = d14 - d7 * Math.sqrt(d3);
        double d20 = (d6 - (d7 * d7) / 2D) / (d7 * d7);
        double d21 = Math.sqrt(d3 / d4);
        double d16 = (Math.log(d / d2) + (d6 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d17 = d16 - d7 * Math.sqrt(d4);
        double d18 = d16 + (2D * Math.log(d2 / d)) / (d7 * Math.sqrt(d4));
        double d19 = d18 - d7 * Math.sqrt(d4);
        double d23 = Common.CND(d13) - Math.pow(d2 / d, 2D * d20) * Common.CND(d15);
        double d24 = Common.CND(-d13) - Math.pow(d2 / d, 2D * d20) * Common.CND(-d15);
        double d25 = Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21);
        double d26 = Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21);
        double d27 = Common.CND(d12) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CND(d14);
        double d28 = Common.CND(-d12) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CND(-d14);
        double d29 = Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21);
        double d30 = Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21);
        if ((i == 0) & (j == 1) || (i == 0) & (j == 0)) {
            PartialBarrier = d * Math.exp((d6 - d5) * d4) * (Common.CBND(d8, d22 * d12, d22 * d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d10, d22 * d14, d22 * d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d9, d22 * d13, d22 * d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d11, d22 * d15, d22 * d21));
        } else if ((i == 1) & (j == 7) & (d1 < d2)) {
            PartialBarrier = d * Math.exp((d6 - d5) * d4) * (Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21));
        } else if ((i == 1) & (j == 7) & (d1 > d2)) {
            PartialBarrier = partialBarrier(1, 4, d, d1, d2, d3, d4, d5, d6, d7);
        } else if ((i == 1) & (j == 6) & (d1 < d2)) {
            PartialBarrier = (d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21)) - d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d8, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d14, -d10, -d21))) + d1 * Math.exp(-d5 * d4) * (Common.CBND(-d9, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d15, -d11, -d21));
        } else if ((i == 1) & (j == 4) & (d1 > d2)) {
            PartialBarrier = d * Math.exp((d6 - d5) * d4) * (Common.CBND(d8, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d10, -d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d9, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d11, -d15, -d21));
        } else if ((i == 1) & (j == 4) & (d1 < d2)) {
            PartialBarrier = ((d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d16, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d18, d14, -d21)) - d1 * Math.exp(-d5 * d4) * (Common.CBND(-d17, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d19, d15, -d21)) - d * Math.exp((d6 - d5) * d4) * (Common.CBND(-d8, -d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(-d10, d14, -d21))) + d1 * Math.exp(-d5 * d4) * (Common.CBND(-d9, -d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(-d11, d15, -d21)) + d * Math.exp((d6 - d5) * d4) * (Common.CBND(d16, d12, d21) - Math.pow(d2 / d, 2D * (d20 + 1.0D)) * Common.CBND(d18, -d14, -d21))) - d1 * Math.exp(-d5 * d4) * (Common.CBND(d17, d13, d21) - Math.pow(d2 / d, 2D * d20) * Common.CBND(d19, -d15, -d21));
        } else if ((i == 0) & (j == 3)) {
            PartialBarrier = (partialBarrier(0, 1, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d27) + d1 * Math.exp(-d5 * d4) * d23;
        } else if ((i == 0) & (j == 2)) {
            PartialBarrier = (partialBarrier(0, 0, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d28) + d1 * Math.exp(-d5 * d4) * d24;
        } else if ((i == 1) & (j == 5)) {
            PartialBarrier = (((partialBarrier(1, 4, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d30) + d1 * Math.exp(-d5 * d4) * d26) - d * Math.exp((d6 - d5) * d4) * d29) + d1 * Math.exp(-d5 * d4) * d25;
        } else if ((i == 1) & (j == 9)) {
            PartialBarrier = (partialBarrier(1, 7, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d29) + d1 * Math.exp(-d5 * d4) * d25;
        } else if ((i == 1) & (j == 8)) {
            PartialBarrier = (partialBarrier(1, 6, d, d1, d2, d3, d4, d5, d6, d7) - d * Math.exp((d6 - d5) * d4) * d30) + d1 * Math.exp(-d5 * d4) * d26;
        }
        return PartialBarrier;
    }

    void buildConstraints(GridBagConstraints gridbagconstraints, int i, int j, int k, int l, int i1, int j1) {
        gridbagconstraints.gridx = i;
        gridbagconstraints.gridy = j;
        gridbagconstraints.gridwidth = k;
        gridbagconstraints.gridheight = l;
        gridbagconstraints.weightx = i1;
        gridbagconstraints.weighty = j1;
    }

    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }

    public void init() {
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
        PutCall.add("Up Knock-out Call");
        PutCall.add("Down Knock-out Call");
        PutCall.add("Up Knock-out Put");
        PutCall.add("Down Knock-out Put");
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
        textLabel[i] = new Label("t", 0);
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
        textLabel[i] = new Label("Expiry", 0);
        gridbaglayout.setConstraints(textLabel[i], gridbagconstraints);
        add(textLabel[i]);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField[i] = new TextField(5);
        gridbaglayout.setConstraints(TextField[i], gridbagconstraints);
        add(TextField[i]);
        TextField[i].setText("4");
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
    Choice Type;
    double S;
    double X;
    double H;
    double K;
    double T;
    double r;
    double b;
    double v;
    double D;
    double PartialBarrier;
    double t;
    int indexPutCall;
    int indexType;
}
