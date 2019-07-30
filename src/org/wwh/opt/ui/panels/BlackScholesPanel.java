package org.wwh.opt.ui.panels;

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

import org.wwh.opt.models.vanilla.BlackScholes;

public class BlackScholesPanel extends JPanel {
    private TextField textField_S;
    private TextField textField_X;
    private TextField textField_v;
    private TextField textField_r;
    private TextField textField_P;
    private TextField textField_T;
    private TextField textField_D;
    private TextField textField_vega;
    private TextField textField_delta;
    private TextField textField_gamma;
    private Choice putCall;
    private Choice solveFor;

    public static final long serialVersionUID = 10060;

    double price(String s, double d, double d1, double d2,
            double d3, double d4, double d5) {
        double optionPrice=0;
        if ("European Call".equals(s)) {
            optionPrice = BlackScholes.europeanCallPrice(d, d1, d2, d3, d4, d5);
        } else if ("European Put".endsWith(s)) {
            optionPrice = BlackScholes.europeanPutPrice(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BAW)".equals(s)) {
            optionPrice = BlackScholes.americanCallBAWApprox(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BAW)".equals(s)) {
            optionPrice = BlackScholes.americanPutBAWApprox(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BS)".equals(s)) {
            optionPrice = BlackScholes.americanCallBSApprox(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BS)".equals(s)) {
            optionPrice = BlackScholes.americanPutBSApprox(d, d1, d2, d3, d4, d5);
        }
        return optionPrice;
    }

    public BlackScholesPanel() {
        init();
    }
    
    private double blackDelta(String sPutCall, double d, double d1, double d2, double d3, double d4, double d5) {
        double blackScholesDelta = 0;
        if ("European Call".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.europeanCallDelta(d, d1, d2, d3, d4, d5);
        } else if ("European Put".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.europeanPutDelta(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BS)".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.americanCallBSDelta(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BS)".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.americanPutBSDelta(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BAW)".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.americanCallBAWDelta(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BAW)".equals(sPutCall)) {
            blackScholesDelta = BlackScholes.americanPutBAWDelta(d, d1, d2, d3, d4, d5);
        }
        return blackScholesDelta;
    }

    private double calcGamma(String sPutCall, double d, double d1, double d2, double d3, double d4, double d5) {
        double gamma = 0;
        if ("European Call".equals(sPutCall)) {
            gamma = BlackScholes.europeanCallGamma(d, d1, d2, d3, d4, d5);
        } else if ("European Put".equals(sPutCall)) {
            gamma = BlackScholes.europeanPutGamma(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BS)".equals(sPutCall)) {
            gamma = BlackScholes.americanCallBSGamma(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BS)".equals(sPutCall)) {
            gamma = BlackScholes.americanPutBSGamma(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BAW)".equals(sPutCall)) {
            gamma = BlackScholes.americanCallBAWGamma(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BAW)".equals(sPutCall)) {
            gamma = BlackScholes.americanPutBAWGamma(d, d1, d2, d3, d4, d5);
        }
        return gamma;
    }
    
    private double calcVega(String sPutCall, double d, double d1, double d2, double d3, double d4, double d5) {
        double vega = 0;
        if ("European Call".equals(sPutCall)) {
            vega = BlackScholes.europeanCallVega(d, d1, d2, d3, d4, d5);
        } else if ("European Put".equals(sPutCall)) {
            vega = BlackScholes.europeanPutVega(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BS)".equals(sPutCall)) {
            vega = BlackScholes.americanCallBSVega(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BS)".equals(sPutCall)) {
            vega = BlackScholes.americanPutBSVega(d, d1, d2, d3, d4, d5);
        } else if ("American Call (BAW)".equals(sPutCall)) {
            vega = BlackScholes.americanCallBAWVega(d, d1, d2, d3, d4, d5);
        } else if ("American Put (BAW)".equals(sPutCall)) {
            vega = BlackScholes.americanPutBAWVega(d, d1, d2, d3, d4, d5);
        }
        return vega;
    }

    private double calcImpliedVol(String sPutCall, double d, double d1, double d2, double d3, double d4, double d5, double P) {
        double ivol = 0;
        if ("European Call".equals(sPutCall)) {
            ivol = BlackScholes.europeanCallImpliedVol(P, d, d1, d2, d3, d4, d5);
        } else if ("European Put".equals(sPutCall)) {
            ivol = BlackScholes.europeanPutImpliedVol(P, d, d1, d2, d3, d4, d5);
        } else if ("American Call (BS)".equals(sPutCall)) {
            ivol = BlackScholes.americanCallBSImpliedVol(P, d, d1, d2, d3, d4, d5);
        } else if ("American Put (BS)".equals(sPutCall)) {
            ivol = BlackScholes.americanPutBSImpliedVol(P, d, d1, d2, d3, d4, d5);
        } else if ("American Call (BAW)".equals(sPutCall)) {
            ivol = BlackScholes.americanCallBAWImpliedVol(P, d, d1, d2, d3, d4, d5);
        } else if ("American Put (BAW)".equals(sPutCall)) {
            ivol = BlackScholes.americanPutBAWImpliedVol(P, d, d1, d2, d3, d4, d5);
        }
        return ivol;
    }
        
    public boolean action(Event event, Object obj) {
        DecimalFormat df = new DecimalFormat("0.#####");

        double S = Double.valueOf(textField_S.getText());
        double X = Double.valueOf(textField_X.getText());
        double v = Double.valueOf(textField_v.getText()) / 100D;
        double r = Double.valueOf(textField_r.getText()) / 100D;
        double T = Double.valueOf(textField_T.getText());
        double D = Double.valueOf(textField_D.getText()) / 100D;
        String sPutCall = putCall.getSelectedItem();
        String sSolveFor = solveFor.getSelectedItem();
        if ("Price".equals(sSolveFor)) {
            textField_v.setEditable(true);
            textField_P.setEditable(false);
            double d = price(sPutCall, S, X, r, D, v, T);
            textField_P.setText(df.format(d));
        } else {
            textField_v.setEditable(false);
            textField_P.setEditable(true);
            double P = Double.valueOf(textField_P.getText());
            v = calcImpliedVol(sPutCall, S, X, r, D, v, T, P);
            textField_v.setText(df.format(v * 100D));
        }
        double delta = blackDelta(sPutCall, S, X, r, D, v, T);
        double gamma = calcGamma(sPutCall, S, X, r, D, v, T);
        double vega = calcVega(sPutCall, S, X, r, D, v, T);
        textField_delta.setText(df.format(delta));
        textField_gamma.setText(df.format(gamma));
        textField_vega.setText(df.format(vega));

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

    @Override
    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }

    public void init() {
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        GridBagLayout gridbaglayout = new GridBagLayout();
        GridBagConstraints gridbagconstraints = new GridBagConstraints();
        setLayout(gridbaglayout);
        buildConstraints(gridbagconstraints, 0, 0, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label = new Label("Solve For", 0);
        gridbaglayout.setConstraints(label, gridbagconstraints);
        add(label);
        buildConstraints(gridbagconstraints, 1, 0, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        solveFor = new Choice();
        solveFor.add("Price");
        solveFor.add("Implied Volatility");
        gridbaglayout.setConstraints(solveFor, gridbagconstraints);
        add(solveFor);
        String sSolveFor = solveFor.getSelectedItem();
        buildConstraints(gridbagconstraints, 1, 1, 1, 1, 100, 100);
        putCall = new Choice();
        putCall.add("European Call");
        putCall.add("American Call (BS)");
        putCall.add("American Call (BAW)");
        putCall.add("European Put");
        putCall.add("American Put (BS)");
        putCall.add("American Put (BAW)");
        gridbaglayout.setConstraints(putCall, gridbagconstraints);
        add(putCall);
        buildConstraints(gridbagconstraints, 0, 2, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label1 = new Label("Asset", 0);
        gridbaglayout.setConstraints(label1, gridbagconstraints);
        add(label1);
        buildConstraints(gridbagconstraints, 1, 2, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_S = new TextField(5);
        gridbaglayout.setConstraints(textField_S, gridbagconstraints);
        add(textField_S);
        textField_S.setText("100");
        buildConstraints(gridbagconstraints, 0, 3, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label2 = new Label("Strike", 0);
        gridbaglayout.setConstraints(label2, gridbagconstraints);
        add(label2);
        buildConstraints(gridbagconstraints, 1, 3, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_X = new TextField(5);
        gridbaglayout.setConstraints(textField_X, gridbagconstraints);
        add(textField_X);
        textField_X.setText("100");
        buildConstraints(gridbagconstraints, 0, 4, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label3 = new Label("Volatility", 0);
        gridbaglayout.setConstraints(label3, gridbagconstraints);
        add(label3);
        buildConstraints(gridbagconstraints, 1, 4, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_v = new TextField(5);
        gridbaglayout.setConstraints(textField_v, gridbagconstraints);
        add(textField_v);
        if (!"Price".equals(sSolveFor)) {
            textField_v.setEditable(false);
        } else {
            textField_v.setText("20");
        }
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
        textField_D.setText("4.0");
        buildConstraints(gridbagconstraints, 0, 7, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label6 = new Label("Expiry", 0);
        gridbaglayout.setConstraints(label6, gridbagconstraints);
        add(label6);
        buildConstraints(gridbagconstraints, 1, 7, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_T = new TextField(5);
        gridbaglayout.setConstraints(textField_T, gridbagconstraints);
        add(textField_T);
        textField_T.setText("1");
        buildConstraints(gridbagconstraints, 0, 8, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label7 = new Label("Price", 0);
        gridbaglayout.setConstraints(label7, gridbagconstraints);
        add(label7);
        buildConstraints(gridbagconstraints, 1, 8, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_P = new TextField(10);
        gridbaglayout.setConstraints(textField_P, gridbagconstraints);
        add(textField_P);
        textField_P.setEditable(false);
        buildConstraints(gridbagconstraints, 0, 9, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label8 = new Label("Delta", 0);
        gridbaglayout.setConstraints(label8, gridbagconstraints);
        add(label8);
        buildConstraints(gridbagconstraints, 1, 9, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_delta = new TextField(10);
        gridbaglayout.setConstraints(textField_delta, gridbagconstraints);
        add(textField_delta);
        textField_delta.setEditable(false);
        buildConstraints(gridbagconstraints, 0, 10, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label9 = new Label("Gamma", 0);
        gridbaglayout.setConstraints(label9, gridbagconstraints);
        add(label9);
        buildConstraints(gridbagconstraints, 1, 10, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_gamma = new TextField(10);
        gridbaglayout.setConstraints(textField_gamma, gridbagconstraints);
        add(textField_gamma);
        textField_gamma.setEditable(false);
        buildConstraints(gridbagconstraints, 0, 11, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        Label label10 = new Label("Vega", 0);
        gridbaglayout.setConstraints(label10, gridbagconstraints);
        add(label10);
        buildConstraints(gridbagconstraints, 1, 11, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        textField_vega = new TextField(10);
        gridbaglayout.setConstraints(textField_vega, gridbagconstraints);
        add(textField_vega);
        textField_vega.setEditable(false);
    }

}
