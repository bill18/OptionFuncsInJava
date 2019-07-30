package org.wwh.opt.ui;

import java.util.Iterator;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

public class Category extends JTabbedPane {
	public static final long serialVersionUID = 10070;
	private String name;
	private Vector models = new Vector();
    private Vector names = new Vector();
    public Category()
    {
    	super(SwingConstants.TOP);
    	super.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }
    
	public Category(String name) {
		this();
		this.name = name;
	}

	public void addModel(Model model) {
		models.add(model);
		String tabName = model.getName().trim();
		names.add(tabName);
        super.addTab(tabName, model.getUi());

		// this.models = model;
	}

	public Iterator getModels()
	{
		return models.iterator();
	}
	
	public void select(String name)
	{
		super.setSelectedIndex(names.indexOf(name.trim()));
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
