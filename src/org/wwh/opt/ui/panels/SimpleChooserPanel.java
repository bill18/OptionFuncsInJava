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

public class SimpleChooserPanel extends JPanel
{
    public static final long serialVersionUID = 10021;
    private TextField tfAsset;
    private TextField tfStrike;
    private TextField tfDecision;
    private TextField tfExpiry;
    private TextField tfVol;
    private TextField tfRate;
    private TextField tfDividend;
    private TextField tfPrice;

    public SimpleChooserPanel()
    {
        init();
    }

    public boolean action(Event event, Object obj)
    {

        String s = tfAsset.getText();
        double S = Double.valueOf(s);
        s = tfStrike.getText();
        double X = Double.valueOf(s);
        s = tfDecision.getText();
        double TD = Double.valueOf(s);
        s = tfExpiry.getText();
        double T = Double.valueOf(s);
        s = tfRate.getText();
        double r = Double.valueOf(s) / 100D;
        s = tfDividend.getText();
        double D = Double.valueOf(s) / 100D;
        s = tfVol.getText();
        double vol = Double.valueOf(s) / 100D;
        double price = Chooser.simpleChooserCalc(S, X, r, D, vol, TD, T);
        DecimalFormat decimalformat = new DecimalFormat("0.####");
        String s1 = decimalformat.format(price);
        tfPrice.setText(s1);
    
        return true;
    }

    public Insets insets()
    {
        return new Insets(10, 10, 10, 10);
    }

    public void init()
    {
    	FormLayout layout = new FormLayout("pref, 5dlu, pref:grow",
		"pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref,4dlu,pref");
    	
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        setLayout(layout);
        CellConstraints cc = new CellConstraints();
        int i = 1;
        add(new Label("Asset", 0), cc.xy(1,i));
        tfAsset = new TextField(5);
        add(tfAsset, cc.xy(3,i));
        tfAsset.setText("100");
        i+=2;
        add(new Label("Strike", 0), cc.xy(1,i));
        tfStrike = new TextField(5);
        add(tfStrike, cc.xy(3,i));
        tfStrike.setText("95");
        i+=2;
        add(new Label("Decision (yrs)", 0), cc.xy(1,i));
        tfDecision = new TextField(5);
        add(tfDecision, cc.xy(3,i));
        tfDecision.setText("0.5");
        i+=2;
        add(new Label("Expiry (yrs)", 0), cc.xy(1,i));
        tfExpiry = new TextField(5);
        add(tfExpiry, cc.xy(3,i));
        tfExpiry.setText("1");
        i+=2;
        add(new Label("Rate (%)", 0), cc.xy(1,i));
        tfRate = new TextField(5);
        add(tfRate, cc.xy(3,i));
        tfRate.setText("5.50");
        i+=2;
        add(new Label("Dividend (%)", 0), cc.xy(1,i));
        tfDividend = new TextField(5);
        add(tfDividend, cc.xy(3,i));
        tfDividend.setText("6.64");
        i+=2;
        add(new Label("Volatility (%)", 0), cc.xy(1,i));
        tfVol = new TextField(5);
        add(tfVol, cc.xy(3,i));
        tfVol.setText("50.0");
        i+=2;
        add(new Label("Price", 0), cc.xy(1,i));
        tfPrice = new TextField(5);
        add(tfPrice, cc.xy(3,i));
        tfPrice.setEditable(false);
    }

}
