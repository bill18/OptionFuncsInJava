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

public class BarrierPartialTwoAssetPanel extends JPanel {
    TextField TextField_Asset;
    TextField TextField_Asset2;
    TextField TextField_BarrierTime;
    TextField TextField_Strike;
    TextField TextField_rho;
    TextField TextField_Barrier;
    TextField TextField_Vol2;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Dividend2;
    TextField TextField_Dividend;
    TextField TextField_Rate;
    TextField TextField_Price;
    Choice PutCall;
    Choice BarrierFrequency;
    Label textLabel;
    double S;
    double X;
    double T;
    double S2;
    double D2;
    double D;
    double r;
    double v;
    double b;
    double rho;
    double v2;
    double t;
    double t_Constant;
    double H;
    double H_User;
    double TwoAssetBarrier;
    String sPutCall;
    
    public static final long serialVersionUID = 10002;

    public BarrierPartialTwoAssetPanel() {
        init();
    }

    double CalcBarrierTwoAsset(int i, double d, double d1, double d2,
            double d3, double d4, double d5, double d6, double d7, double d8, double d9,
            double d10, double d11) {
        double d23 = 0.0D;
        double d24 = d3 - d4;
        double d25 = d3 - d5;
        int k;
        if (i == 4 || i == 6 || i == 0 || i == 2) {
            k = -1;
        } else {
            k = 1;
        }
        int j;
        if (i == 4 || i == 5 || i == 0 || i == 1) {
            j = 1;
        } else {
            j = -1;
        }
        double d20 = d24 - (d6 * d6) / 2D;
        double d21 = d25 - (d7 * d7) / 2D;
        double d12 = (Math.log(d / d2) + (d20 + d6 * d6) * d9) / (d6 * Math.sqrt(d9));
        double d13 = d12 - d6 * Math.sqrt(d9);
        double d14 = d12 + (2D * d8 * Math.log(d10 / d1)) / (d7 * Math.sqrt(d9));
        double d15 = d13 + (2D * d8 * Math.log(d10 / d1)) / (d7 * Math.sqrt(d9));
        double d16 = (Math.log(d10 / d1) - (d21 + d8 * d6 * d7) * d11) / (d7 * Math.sqrt(d11));
        double d17 = d16 + d8 * d6 * Math.sqrt(d11);
        double d18 = d16 - (2D * Math.log(d10 / d1)) / (d7 * Math.sqrt(d11));
        double d19 = d17 - (2D * Math.log(d10 / d1)) / (d7 * Math.sqrt(d11));
        double d22 = (double) j * d * Math.exp((d24 - d3) * d9) * (Common.CBND((double) j * d12, (double) k * d16, (double) (-j * k) * d8 * Math.sqrt(d11 / d9)) - Math.exp((2D * Math.log(d10 / d1) * (d21 + d8 * d6 * d7)) / (d7 * d7)) * Common.CBND((double) j * d14, (double) k * d18, (double) (-j * k) * d8 * Math.sqrt(d11 / d9))) - (double) j * Math.exp(-d3 * d9) * d2 * (Common.CBND((double) j * d13, (double) k * d17, (double) (-j * k) * d8 * Math.sqrt(d11 / d9)) - Math.exp((2D * Math.log(d10 / d1) * d21) / (d7 * d7)) * Common.CBND((double) j * d15, (double) k * d19, (double) (-j * k) * d8 * Math.sqrt(d11 / d9)));
        switch (i) {
            case 4:
            case 5:
            case 6:
            case 7:
                d23 = d22;
                break;
            case 1:
            case 0:
                d23 = ShekOption.blackPrice(ShekOption.EUROPEAN_CALL, d, d2, d3, d4, d6, d9) - d22;
                break;
            case 3:
            case 2:
                d23 = ShekOption.blackPrice(ShekOption.EUROPEAN_PUT, d, d2, d3, d4, d6, d9) - d22;
                break;
            default:
                break;
        }
        return d23;
    }

    public boolean action(Event event, Object obj) {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        s = TextField_Asset2.getText();
        double1 = Double.valueOf(s);
        S2 = double1.doubleValue();
        s = TextField_Strike.getText();
        double1 = Double.valueOf(s);
        X = double1.doubleValue();
        s = TextField_Barrier.getText();
        double1 = Double.valueOf(s);
        H_User = double1.doubleValue();
        s = TextField_BarrierTime.getText();
        double1 = Double.valueOf(s);
        t = double1.doubleValue();
        s = TextField_Expiry.getText();
        double1 = Double.valueOf(s);
        T = double1.doubleValue();
        s = TextField_Rate.getText();
        double1 = Double.valueOf(s);
        r = double1.doubleValue() / 100D;
        s = TextField_Dividend.getText();
        double1 = Double.valueOf(s);
        D = double1.doubleValue() / 100D;
        s = TextField_Dividend2.getText();
        double1 = Double.valueOf(s);
        D2 = double1.doubleValue() / 100D;
        s = TextField_rho.getText();
        double1 = Double.valueOf(s);
        rho = double1.doubleValue();
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v = double1.doubleValue() / 100D;
        s = TextField_Vol2.getText();
        double1 = Double.valueOf(s);
        v2 = double1.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        int j = BarrierFrequency.getSelectedIndex();
        if (j == 0) {
            t_Constant = 0.0D;
        } else if (j == 1) {
            t_Constant = 0.00011415525114155251D;
        } else if (j == 2) {
            t_Constant = 0.0027397260273972603D;
        } else if (j == 3) {
            t_Constant = 0.019230769230769232D;
        } else if (j == 4) {
            t_Constant = 0.083333333333333329D;
        }
        double d = Common.discreteAdjustedBarrier(S2, H_User, v2, t_Constant);
        double d1 = CalcBarrierTwoAsset(i, S, S2, X, r, D, D2, v, v2, rho, T, d, t);
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        String s1 = decimalformat.format(d1);
        TextField_Price.setText(s1);

        return true;
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
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        PutCall = new Choice();
        PutCall.add("Down Knock-in call");
        PutCall.add("Up Knock-in call");
        PutCall.add("Down Knock-in put");
        PutCall.add("Up Knock-in put");
        PutCall.add("Down Knock-out call");
        PutCall.add("Up Knock-out call");
        PutCall.add("Down Knock-out put");
        PutCall.add("Up Knock-out put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
        i++;
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        BarrierFrequency = new Choice();
        BarrierFrequency.add("Continuous");
        BarrierFrequency.add("Hourly");
        BarrierFrequency.add("Daily");
        BarrierFrequency.add("Weekly");
        BarrierFrequency.add("Monthly");
        gridbaglayout.setConstraints(BarrierFrequency, gridbagconstraints);
        add(BarrierFrequency);
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Asset 1", 0);
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
        textLabel = new Label("Asset 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Asset2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Asset2, gridbagconstraints);
        add(TextField_Asset2);
        TextField_Asset2.setText("90");
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
        textLabel = new Label("Barrier", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Barrier = new TextField(5);
        gridbaglayout.setConstraints(TextField_Barrier, gridbagconstraints);
        add(TextField_Barrier);
        TextField_Barrier.setText("90");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Barrier Time", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_BarrierTime = new TextField(5);
        gridbaglayout.setConstraints(TextField_BarrierTime, gridbagconstraints);
        add(TextField_BarrierTime);
        TextField_BarrierTime.setText("1.0");
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
        textLabel = new Label("Dividend 1", 0);
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
        textLabel = new Label("Dividend 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Dividend2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Dividend2, gridbagconstraints);
        add(TextField_Dividend2);
        TextField_Dividend2.setText("10.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Volatility 1", 0);
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
        textLabel = new Label("Volatility 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Vol2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Vol2, gridbagconstraints);
        add(TextField_Vol2);
        TextField_Vol2.setText("20.0");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Correlation", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_rho = new TextField(5);
        gridbaglayout.setConstraints(TextField_rho, gridbagconstraints);
        add(TextField_rho);
        TextField_rho.setText("0.50");
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
    }


}
