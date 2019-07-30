package org.wwh.opt.models.exotic;

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

public class OptionOption extends JPanel
{
	public static final long serialVersionUID = 10045;

    public OptionOption()
    {
    	init();
    }

    double OptionsOnOptions(int i, double d, double d1, double d2, 
            double d3, double d4, double d5, double d6, double d7)
    {
        String s;
//        if(i == 0 || i == 1)
//            s = "European Call";
//        else
//            s = "European Put";
        double d14 = d5 - d6;
        double d15 = 0.0D;
        double d12 = ShekOption.criticalValueOptionsOnOptions(i, d1, d2, d4 - d3, d5, d6, d7);
        double d13 = Math.sqrt(d3 / d4);
        double d8 = (Math.log(d / d12) + (d14 + (d7 * d7) / 2D) * d3) / (d7 * Math.sqrt(d3));
        double d9 = d8 - d7 * Math.sqrt(d3);
        double d10 = (Math.log(d / d1) + (d14 + (d7 * d7) / 2D) * d4) / (d7 * Math.sqrt(d4));
        double d11 = d10 - d7 * Math.sqrt(d4);
        if(i == 0)
            d15 = d * Math.exp((d14 - d5) * d4) * Common.CBND(d10, d8, d13) - d1 * Math.exp(-d5 * d4) * Common.CBND(d11, d9, d13) - d2 * Math.exp(-d5 * d3) * Common.CND(d9);
        else
        if(i == 1)
            d15 = (d1 * Math.exp(-d5 * d4) * Common.CBND(d11, -d9, -d13) - d * Math.exp((d14 - d5) * d4) * Common.CBND(d10, -d8, -d13)) + d2 * Math.exp(-d5 * d3) * Common.CND(-d9);
        else
        if(i == 2)
            d15 = d1 * Math.exp(-d5 * d4) * Common.CBND(-d11, -d9, d13) - d * Math.exp((d14 - d5) * d4) * Common.CBND(-d10, -d8, d13) - d2 * Math.exp(-d5 * d3) * Common.CND(-d9);
        else
        if(i == 3)
            d15 = (d * Math.exp((d14 - d5) * d4) * Common.CBND(-d10, d8, -d13) - d1 * Math.exp(-d5 * d4) * Common.CBND(-d11, d9, -d13)) + Math.exp(-d5 * d3) * d2 * Common.CND(d9);
        return d15;
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
    
    public boolean action(Event event, Object obj)
    {

        String s = textField_S.getText();
        Double double1 = Double.valueOf(s);
        S = double1.doubleValue();
        String s1 = textField_X.getText();
        Double double2 = Double.valueOf(s1);
        X = double2.doubleValue();
        String s2 = textField_X2.getText();
        Double double3 = Double.valueOf(s2);
        X2 = double3.doubleValue();
        String s3 = textField_v.getText();
        Double double4 = Double.valueOf(s3);
        v = double4.doubleValue() / 100D;
        String s4 = textField_r.getText();
        Double double5 = Double.valueOf(s4);
        r = double5.doubleValue() / 100D;
        String s5 = Common.sDate(-1D);
        String s6 = textField_T.getText();
        T = Common.yearFaction(s5, s6, 1);
        s6 = textField_T2.getText();
        T2 = Common.yearFaction(s5, s6, 1);
        String s7 = textField_D.getText();
        Double double6 = Double.valueOf(s7);
        D = double6.doubleValue() / 100D;
        int i = PutCall.getSelectedIndex();
        double d = OptionsOnOptions(i, S, X, X2, T, T2, r, D, v);
        DecimalFormat decimalformat = new DecimalFormat("0.####");
        String s8 = decimalformat.format(d);
        textField_P.setText(s8);
        
        return true;
    }

    public void init()
    {
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        GridBagLayout gridbaglayout = new GridBagLayout();
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        setLayout(gridbaglayout);
        buildConstraints(gridbagconstraints, 1, 0, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        PutCall = new Choice();
        PutCall.add("Call on Call");
        PutCall.add("Put on Call");
        PutCall.add("Call on Put");
        PutCall.add("Put on Put");
        gridbaglayout.setConstraints(PutCall, gridbagconstraints);
        add(PutCall);
        buildConstraints(gridbagconstraints, 0, 1, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label = new Label("Asset", 0);
        gridbaglayout.setConstraints(label, gridbagconstraints);
        add(label);
        buildConstraints(gridbagconstraints, 1, 1, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_S = new TextField(5);
        gridbaglayout.setConstraints(textField_S, gridbagconstraints);
        add(textField_S);
        textField_S.setText("300");
        buildConstraints(gridbagconstraints, 0, 2, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label1 = new Label("Asset Strike", 0);
        gridbaglayout.setConstraints(label1, gridbagconstraints);
        add(label1);
        buildConstraints(gridbagconstraints, 1, 2, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_X = new TextField(5);
        gridbaglayout.setConstraints(textField_X, gridbagconstraints);
        add(textField_X);
        textField_X.setText("350");
        buildConstraints(gridbagconstraints, 0, 3, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label2 = new Label("Option Strike", 0);
        gridbaglayout.setConstraints(label2, gridbagconstraints);
        add(label2);
        buildConstraints(gridbagconstraints, 1, 3, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_X2 = new TextField(5);
        gridbaglayout.setConstraints(textField_X2, gridbagconstraints);
        add(textField_X2);
        textField_X2.setText("50");
        buildConstraints(gridbagconstraints, 0, 4, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label3 = new Label("Volatity", 0);
        gridbaglayout.setConstraints(label3, gridbagconstraints);
        add(label3);
        buildConstraints(gridbagconstraints, 1, 4, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_v = new TextField(5);
        gridbaglayout.setConstraints(textField_v, gridbagconstraints);
        add(textField_v);
        textField_v.setText("20");
        buildConstraints(gridbagconstraints, 0, 5, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label4 = new Label("Rate", 0);
        gridbaglayout.setConstraints(label4, gridbagconstraints);
        add(label4);
        buildConstraints(gridbagconstraints, 1, 5, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_r = new TextField(5);
        gridbaglayout.setConstraints(textField_r, gridbagconstraints);
        add(textField_r);
        textField_r.setText("5.5");
        buildConstraints(gridbagconstraints, 0, 6, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label5 = new Label("Dividend", 0);
        gridbaglayout.setConstraints(label5, gridbagconstraints);
        add(label5);
        buildConstraints(gridbagconstraints, 1, 6, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_D = new TextField(5);
        gridbaglayout.setConstraints(textField_D, gridbagconstraints);
        add(textField_D);
        textField_D.setText("5.5");
        buildConstraints(gridbagconstraints, 0, 7, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label6 = new Label("OptionOption Maturity", 0);
        gridbaglayout.setConstraints(label6, gridbagconstraints);
        add(label6);
        buildConstraints(gridbagconstraints, 1, 7, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_T = new TextField(5);
        gridbaglayout.setConstraints(textField_T, gridbagconstraints);
        add(textField_T);
        textField_T.setText("6-Mar-2007");
        buildConstraints(gridbagconstraints, 0, 8, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label7 = new Label("Option Maturity", 0);
        gridbaglayout.setConstraints(label7, gridbagconstraints);
        add(label7);
        buildConstraints(gridbagconstraints, 1, 8, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_T2 = new TextField(5);
        gridbaglayout.setConstraints(textField_T2, gridbagconstraints);
        add(textField_T2);
        textField_T2.setText("6-Mar-2012");
        buildConstraints(gridbagconstraints, 0, 9, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label8 = new Label("Price", 0);
        gridbaglayout.setConstraints(label8, gridbagconstraints);
        add(label8);
        buildConstraints(gridbagconstraints, 1, 9, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_P = new TextField(10);
        gridbaglayout.setConstraints(textField_P, gridbagconstraints);
        add(textField_P);
        textField_P.setEditable(false);
    }

    TextField textField_X2;
    TextField textField_T2;
    TextField textField_S;
    TextField textField_X;
    TextField textField_v;
    TextField textField_r;
    TextField textField_P;
    TextField textField_T;
    TextField textField_D;
    TextField textField_delta;
    Choice PutCall;
    double X2;
    double T2;
    double BlackScholesOutput;
    double BlackScholesDelta;
    double S;
    double X;
    double r;
    double v;
    double T;
    double D;
    double BAWAmericanCallApprox_Out;
    double KcOut;
    String sPutCall;
}
