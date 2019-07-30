package org.wwh.opt.ui.panels;

import java.awt.Choice;
import java.awt.Event;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Label;
import java.awt.TextField;
import java.text.DecimalFormat;

import javax.swing.JPanel;

import org.wwh.opt.models.barrier.Barrier;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class BarrierPanel extends JPanel {

    public static final long serialVersionUID = 10000;

    private final TextField textFields[];
    private Choice putCall;
    private int indexPutCall;
    private static final String choices[] = {
        "Down Knock-in call",
        "Up Knock-in call",
        "Down Knock-in put",
        "Up Knock-in put",
        "Down Knock-out call",
        "Up Knock-out call",
        "Down Knock-out put",
        "Up Knock-out put",};

    private static String labels[] = {
        "Style",     // 0
        "Asset",     // 1
        "Strike",    // 2
        "Barrier",   // 3
        "Rebate",    // 4
        "Expiry",    // 5
        "Rate",      // 6
        "Dividend",  // 7
        "Volatility",// 8
        "Price",     // 9
        "Delta",     // 10
        "Gamma",     // 11
        "Vega",};    // 12

    private static double[] vals = {
        100,
        100,
        90,
        50,
        1,
        5.5,
        4.0,
        35.5
    };

    public BarrierPanel() {
        textFields = new TextField[labels.length];
        init();
    }

    @Override
    public boolean action(Event event, Object obj) {
        // repaint();
        double values[] = new double[labels.length];

        int i = 1;
        do {
            String s = textFields[i].getText();
            values[i] = Double.valueOf(s);
        } while (++i <= 8);
        double S = values[1];
        double X = values[2];
        double H = values[3];
        double K = values[4];
        double T = values[5];
        double r = values[6] / 100D;
        double D = values[7] / 100D;
        double v = values[8] / 100D;
        indexPutCall = putCall.getSelectedIndex();
        double[] retVals = Barrier.price(indexPutCall, S,X,H,K,T,r,D,v);
                
        DecimalFormat decimalformat = new DecimalFormat("0.#####");
        textFields[9].setText(decimalformat.format(retVals[0])); // Price
        textFields[10].setText(decimalformat.format(retVals[1])); // Delta
        textFields[11].setText(decimalformat.format(retVals[2]));  // Gamma
        textFields[12].setText(decimalformat.format(retVals[3]));  // Vega

        return true;
    }

    public Insets insets() {
        return new Insets(10, 10, 10, 10);
    }

    public void init() {
        Font font = new Font("Century Gothic", 1, 12);
        setFont(font);
        StringBuffer rowSpec = new StringBuffer();
        for (int j = 0; j < labels.length; j++) {
            // textLabels[j+1] = new Label(labels[j], 0);
            if (j == 0) {
                rowSpec.append("pref");
            } else {
                rowSpec.append(",4dlu, pref");
            }
        }

        FormLayout layout = new FormLayout("pref, 5dlu, pref:grow",
                rowSpec.toString());
        // setBackground(Color.gray);
        setLayout(layout);
        CellConstraints cc = new CellConstraints();
        for (int j = 0; j < labels.length; j++) {
            add(new Label(labels[j], 0), cc.xy(1, j * 2 + 1));
            if (j == 0) {
                putCall = new Choice();
                for (int l = 0; l < choices.length; l++) {
                    putCall.add(choices[l]);
                }
                add(putCall, cc.xy(3, j * 2 + 1));
            } else {
                textFields[j] = new TextField(5);
                add(textFields[j], cc.xy(3, j * 2 + 1));
                if (j > vals.length) {
                    textFields[j].setEditable(false);
                } else {
                    textFields[j].setText("" + vals[j - 1]);
                }
            }

        }
    }

}
