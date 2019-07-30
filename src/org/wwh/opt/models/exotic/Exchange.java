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

public class Exchange extends JPanel
{
	public static final long serialVersionUID = 10042;

    public Exchange()
    {
    	init();
    }

    double EuropeanExchangeOption(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8, double d9, double d10)
    {
        double d11 = Math.sqrt((d8 * d8 + d9 * d9) - 2D * d10 * d8 * d9);
        double d12 = (Math.log((d2 * d) / (d3 * d1)) + ((d6 - d7) + (d11 * d11) / 2D) * d4) / (d11 * Math.sqrt(d4));
        double d13 = d12 - d11 * Math.sqrt(d4);
        return d2 * d * Math.exp((d6 - d5) * d4) * Common.CND(d12) - d3 * d1 * Math.exp((d7 - d5) * d4) * Common.CND(d13);
    }

    double AmericanExchangeOption(double d, double d1, double d2, double d3, double d4, double d5, double d6, 
            double d7, double d8, double d9, double d10)
    {
        double d11 = Math.sqrt((d8 * d8 + d9 * d9) - 2D * d10 * d8 * d9);
        return ShekOption.BSAmericanCallApprox(d2 * d, d3 * d1, d4, d5 - d7, d6 - d7, d11);
    }

    public boolean action(Event event, Object obj)
    {

        String s = TextField_Asset.getText();
        Double double1 = Double.valueOf(s);
        S1 = double1.doubleValue();
        s = TextField_Asset2.getText();
        double1 = Double.valueOf(s);
        S2 = double1.doubleValue();
        s = TextField_Quant1.getText();
        double1 = Double.valueOf(s);
        Q1 = double1.doubleValue();
        s = TextField_Quant2.getText();
        double1 = Double.valueOf(s);
        Q2 = double1.doubleValue();
        s = TextField_Expiry.getText();
        double1 = Double.valueOf(s);
        T = double1.doubleValue();
        s = TextField_Rate.getText();
        double1 = Double.valueOf(s);
        r = double1.doubleValue() / 100D;
        s = TextField_Dividend.getText();
        double1 = Double.valueOf(s);
        D1 = double1.doubleValue() / 100D;
        s = TextField_Dividend2.getText();
        double1 = Double.valueOf(s);
        D2 = double1.doubleValue() / 100D;
        s = TextField_Vol.getText();
        double1 = Double.valueOf(s);
        v1 = double1.doubleValue() / 100D;
        s = TextField_Vol2.getText();
        double1 = Double.valueOf(s);
        v2 = double1.doubleValue() / 100D;
        s = TextField_rho.getText();
        double1 = Double.valueOf(s);
        rho = double1.doubleValue();
        int i = Style.getSelectedIndex();
        if(i == 0)
            OptionPrice = EuropeanExchangeOption(S1, S2, Q1, Q2, T, r, r - D1, r - D2, v1, v2, rho);
        else
        if(i == 1)
            OptionPrice = AmericanExchangeOption(S1, S2, Q1, Q2, T, r, r - D1, r - D2, v1, v2, rho);
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
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        Style = new Choice();
        Style.add("European");
        Style.add("American");
        gridbaglayout.setConstraints(Style, gridbagconstraints);
        add(Style);
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
        TextField_Asset2.setText("110");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Quantity 1", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Quant1 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Quant1, gridbagconstraints);
        add(TextField_Quant1);
        TextField_Quant1.setText("1.00");
        i++;
        buildConstraints(gridbagconstraints, 0, i, 1, 1, 0, 40);
        gridbagconstraints.fill = 0;
        gridbagconstraints.anchor = 13;
        textLabel = new Label("Quantity 2", 0);
        gridbaglayout.setConstraints(textLabel, gridbagconstraints);
        add(textLabel);
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 0, 0);
        gridbagconstraints.fill = 2;
        TextField_Quant2 = new TextField(5);
        gridbaglayout.setConstraints(TextField_Quant2, gridbagconstraints);
        add(TextField_Quant2);
        TextField_Quant2.setText("1.00");
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
        TextField_Expiry.setText("3");
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
        textLabel = new Label("Dividend 2", 0);
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
        TextField_Dividend2.setText("8.0");
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
        TextField_Vol2.setText("9.0");
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
        TextField_rho.setText("0.80");
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
    TextField TextField_Asset2;
    TextField TextField_Quant1;
    TextField TextField_Quant2;
    TextField TextField_Expiry;
    TextField TextField_Vol;
    TextField TextField_Vol2;
    TextField TextField_Dividend;
    TextField TextField_Dividend2;
    TextField TextField_rho;
    TextField TextField_Rate;
    TextField TextField_Price;
    Choice Style;
    Label textLabel;
    double S1;
    double S2;
    double T;
    double Q2;
    double Q1;
    double D1;
    double D2;
    double r;
    double v1;
    double v2;
    double b1;
    double b2;
    double rho;
    double OptionPrice;
}
