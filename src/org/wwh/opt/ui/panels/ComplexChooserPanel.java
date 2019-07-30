package org.wwh.opt.ui.panels;

import java.awt.Event;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.wwh.opt.models.chooser.Chooser;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class ComplexChooserPanel extends JPanel
{
	public static final long serialVersionUID = 10020;
	private TextField tfAsset;
	private TextField tfCallStrike;
	private TextField tfPutStrike;
	private TextField tfDelta;
	private TextField tfGamma;
	private TextField tfVega;
	private TextField tfDecision;
	private TextField tfCallExpiry;
	private TextField tfPutExpiry;
	private TextField tfVol;
	private TextField tfRate;
	private TextField tfDividend;
	private TextField tfPrice;


    public ComplexChooserPanel()
    {
        init();
    }

    public boolean action(Event event, Object obj)
    {
        double S = Double.valueOf(tfAsset.getText());
        double Xcall = Double.valueOf(tfCallStrike.getText());
        double Xput = Double.valueOf(tfPutStrike.getText());
        double TD = Double.valueOf(tfDecision.getText());
        double Tcall = Double.valueOf(tfCallExpiry.getText());
        double Tput = Double.valueOf(tfPutExpiry.getText());
        double r = Double.valueOf(tfRate.getText()) /100D;
        double D = Double.valueOf(tfDividend.getText()) /100D;
        double vol = Double.valueOf(tfVol.getText()) /100D;
        double[] retVals = Chooser.price(S, Xcall, Xput, r, D, vol, TD, Tcall, Tput);
        DecimalFormat decimalformat = new DecimalFormat("0.####");
        tfPrice.setText(decimalformat.format(retVals[0]));// price
        tfDelta.setText(decimalformat.format(retVals[1]));
        tfGamma.setText(decimalformat.format(retVals[2]));
        tfVega.setText(decimalformat.format(retVals[3]));
    
        return true;
    }

    public Insets insets()
    {
        return new Insets(10, 10, 10, 10);
    }

    public void init()
    {
    	FormLayout layout = new FormLayout("pref, 5dlu, pref:grow",
    			"pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref");
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        setLayout(layout);
        CellConstraints cc = new CellConstraints();
        tfAsset = new TextField(5);
        tfAsset.setText("100");
        tfCallStrike = new TextField(5);
        tfCallStrike.setText("95");
        add(new Label("Asset", 0), cc.xy(1,1));
        add(tfAsset, cc.xy(3,1));
        add(new Label("Call Strike", 0),cc.xy(1,3));
        add(tfCallStrike, cc.xy(3,3));
        add(new Label("Put Strike", 0), cc.xy(1,5));
        tfPutStrike = new TextField(5);
        add(tfPutStrike, cc.xy(3,5));
        tfPutStrike.setText("95");
        add(new Label("Decision (yrs)", 0), cc.xy(1,7));
        tfDecision = new TextField(5);
        add(tfDecision, cc.xy(3,7));
        tfDecision.setText("0.5");
        add(new Label("Call Expiry (yrs)", 0), cc.xy(1,9));
        tfCallExpiry = new TextField(5);
        add(tfCallExpiry, cc.xy(3,9));
        tfCallExpiry.setText("1");
        add(new Label("Put Expiry (yrs)", 0), cc.xy(1,11));
        tfPutExpiry = new TextField(5);
        add(tfPutExpiry, cc.xy(3,11));
        tfPutExpiry.setText("1");
        add(new Label("Rate (%)", 0), cc.xy(1,13));
        tfRate = new TextField(5);
        add(tfRate, cc.xy(3,13));
        tfRate.setText("5.50");
        add(new Label("Dividend (%)", 0), cc.xy(1,15));
        tfDividend = new TextField(5);
        add(tfDividend, cc.xy(3,15));
        tfDividend.setText("6.64");
        add(new Label("Volatility (%)", 0), cc.xy(1,17));
        tfVol = new TextField(5);
        add(tfVol, cc.xy(3,17));
        tfVol.setText("50.0");
        add(new Label("Price", 0), cc.xy(1,19));
        tfPrice = new TextField(5);
        add(tfPrice, cc.xy(3,19));
        tfPrice.setEditable(false);
        add(new Label("Delta", 0), cc.xy(1,21));
        tfDelta = new TextField(5);
        add(tfDelta, cc.xy(3,21));
        tfDelta.setEditable(false);
        add(new Label("Gamma", 0), cc.xy(1,23));
        tfGamma = new TextField(5);
        add(tfGamma, cc.xy(3,23));
        tfGamma.setEditable(false);
        add(new Label("Vega", 0), cc.xy(1,25));
        tfVega = new TextField(5);
        add(tfVega, cc.xy(3,25));       
        tfVega.setEditable(false);
    }



}
