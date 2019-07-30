package org.wwh.opt.ui;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.JPanel;

public class ModelPanel extends JPanel {
	public static final long serialVersionUID = 10070;
	private LinkedHashMap cats = new LinkedHashMap();
	public ModelPanel()
	{
		super(new CardLayout());
	}

	public Component add(Category pane)
	{
		String cardName = pane.getName().trim();
		cats.put(cardName, pane);
		return super.add(cardName, pane);
	}

	public Iterator iterator()
	{
		return cats.values().iterator();
	}
	
	public void selectTab(String cardName, String tabName)
	{
		Category pane = (Category)cats.get(cardName.trim());
		pane.select(tabName.trim());
	}
}
