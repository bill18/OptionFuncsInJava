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

import org.wwh.opt.models.barrier.BarrierEnd;


public class BarrierEndPanel extends JPanel
{
    public static final long serialVersionUID = 10001;
    private final Label textLabel[];
    private final TextField TextField[];
    private final double Value[];
    private Choice putCallChoice;

    public BarrierEndPanel()
    {
        textLabel = new Label[15];
        TextField = new TextField[15];
        Value = new double[15];
        init();
    }

    public boolean action(Event event, Object obj)
    {
        int i = 2;
        do
        {
            String s = TextField[i].getText();
            Value[i] = Double.valueOf(s);
        } while(++i < 10);
        double S = Value[2];
        double X = Value[3];
        double H = Value[4];
        double t = Value[5];
        double T = Value[6];
        double r = Value[7] / 100D;
        double D = Value[8] / 100D;
        double v = Value[9] / 100D;
        int indexPutCall = putCallChoice.getSelectedIndex();
        int indexType = 1;
        double[] retVals = BarrierEnd.calc(
             indexType,
             indexPutCall,
             S,
             X,
             H,
             T,
             r,
             v,
             D,
             t);
            
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        TextField[10].setText(decimalformat.format(retVals[0]));
        TextField[11].setText(decimalformat.format(retVals[1]));
        TextField[12].setText(decimalformat.format(retVals[2]));
        TextField[13].setText(decimalformat.format(retVals[3]));
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

    @Override
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
        buildConstraints(gridbagconstraints, 1, i, 1, 1, 100, 100);
        gridbagconstraints.fill = 2;
        gridbagconstraints.anchor = 13;
        putCallChoice = new Choice();
        putCallChoice.add("Up Knock-out Call");
        putCallChoice.add("Down Knock-out Call");
        putCallChoice.add("Up Knock-out Put");
        putCallChoice.add("Down Knock-out Put");
        putCallChoice.add("One-touch Call");
        putCallChoice.add("One-touch Put");
        gridbaglayout.setConstraints(putCallChoice, gridbagconstraints);
        add(putCallChoice);
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


}
