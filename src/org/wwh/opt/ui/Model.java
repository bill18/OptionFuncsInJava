package org.wwh.opt.ui;

import javax.swing.JPanel;

public class Model {
	public static final long serialVersionUID = 10071;
	private String name;
	private JPanel ui;
	public Model(String name, JPanel ui) {
		this.name = name;
		this.ui = ui;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public JPanel getUi() {
		return ui;
	}
	public void setUi(JPanel ui) {
		this.ui = ui;
	}
}
