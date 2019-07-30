package org.wwh.opt.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.Iterator;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.wwh.opt.models.barrier.BarrierPartialTwoAssetPanel;
import org.wwh.opt.models.barrier.BarrierSoftPanel;
import org.wwh.opt.models.barrier.BarrierStartPanel;
import org.wwh.opt.models.barrier.BarrierTrinomialPanel;
import org.wwh.opt.models.barrier.BarrierTwoAssetPanel;
import org.wwh.opt.models.barrier.DoubleBarrierPanel;
import org.wwh.opt.models.barrier.LookBarrierPanel;
import org.wwh.opt.models.barrier.PartialFixedStrikePanel;
import org.wwh.opt.models.digital.DigitalAsset;
import org.wwh.opt.models.digital.DigitalCash;
import org.wwh.opt.models.digital.Gap;
import org.wwh.opt.models.exotic.Accrual;
import org.wwh.opt.models.exotic.Binomial3D;
import org.wwh.opt.models.exotic.Exchange;
import org.wwh.opt.models.exotic.Extendible;
import org.wwh.opt.models.exotic.OptionOption;
import org.wwh.opt.models.exotic.Quanto;
import org.wwh.opt.models.exotic.Spread;
import org.wwh.opt.models.exotic.TwoAssetCorr;
import org.wwh.opt.models.lookback.FixedStrike;
import org.wwh.opt.models.lookback.FloatingStrike;
import org.wwh.opt.models.lookback.LookBackMC;
import org.wwh.opt.models.lookback.PartialFloatStrike;
import org.wwh.opt.models.lookback.TrinomialLookback;
import org.wwh.opt.models.vanilla.ForwardStart;
import org.wwh.opt.models.vanilla.JumpDiffusion;
import org.wwh.opt.ui.panels.BarrierEndPanel;
import org.wwh.opt.ui.panels.BarrierPanel;
import org.wwh.opt.ui.panels.BlackScholesPanel;
import org.wwh.opt.ui.panels.ComplexChooserPanel;
import org.wwh.opt.ui.panels.SimpleChooserPanel;

import com.jgoodies.forms.factories.Borders;
import com.jgoodies.looks.LookUtils;
import com.jgoodies.uif_lite.component.UIFSplitPane;

public class FinModels {
	private ModelPanel modelPane= new ModelPanel();
	
	public FinModels()
	{
		// initModels();
	}
	
	private ModelPanel initModels()
	{
		Category catBarrier = new Category("Barrier");
		catBarrier.addModel(new Model("Barrier               ", new BarrierPanel()));
		catBarrier.addModel(new Model("BarrierEnd            ", new BarrierEndPanel()));
		catBarrier.addModel(new Model("BarrierPartialTwoAsset", new BarrierPartialTwoAssetPanel()));
		catBarrier.addModel(new Model("BarrierSoft           ", new BarrierSoftPanel()));
		catBarrier.addModel(new Model("BarrierStart          ", new BarrierStartPanel()));
		catBarrier.addModel(new Model("BarrierTrinomial      ", new BarrierTrinomialPanel()));
		catBarrier.addModel(new Model("BarrierTwoAsset       ", new BarrierTwoAssetPanel()));
		catBarrier.addModel(new Model("BlackScholes          ", new BlackScholesPanel()));
		catBarrier.addModel(new Model("DoubleBarrier         ", new DoubleBarrierPanel()));
		catBarrier.addModel(new Model("LookBarrier           ", new LookBarrierPanel()));
		catBarrier.addModel(new Model("PartialFixedStrike    ", new PartialFixedStrikePanel()));
		modelPane.add(catBarrier);

		Category catChooser = new Category("Chooser");
		catChooser.addModel(new Model("ComplexChooser",  new ComplexChooserPanel()));
		catChooser.addModel(new Model("SimpleChooser ",  new SimpleChooserPanel()));
		modelPane.add(catChooser);

		Category catDigital = new Category("Digital");
		catDigital.addModel(new Model("DigitalAsset", new DigitalAsset()));
		catDigital.addModel(new Model("DigitalCash ", new DigitalCash()));
		catDigital.addModel(new Model("Gap         ", new Gap()));
		modelPane.add(catDigital);

		Category catExotic = new Category("Exotic");
		catExotic.addModel(new Model("Accrual     ", new Accrual()));
		catExotic.addModel(new Model("Binomial3D  ", new Binomial3D()));
		catExotic.addModel(new Model("Exchange    ", new Exchange()));
		catExotic.addModel(new Model("Extendible  ", new Extendible()));
		catExotic.addModel(new Model("OptionOption", new OptionOption()));
		catExotic.addModel(new Model("Quanto      ", new Quanto()));
		catExotic.addModel(new Model("Spread      ", new Spread()));
		catExotic.addModel(new Model("TwoAssetCorr", new TwoAssetCorr()));
		modelPane.add(catExotic);

		Category catLookback = new Category("Lookback");
		catLookback.addModel(new Model("FixedStrike       ", new FixedStrike()));
		catLookback.addModel(new Model("FloatingStrike    ", new FloatingStrike()));
		catLookback.addModel(new Model("LookBackMC        ", new LookBackMC()));
		catLookback.addModel(new Model("PartialFixedStrike", new PartialFixedStrikePanel()));
		catLookback.addModel(new Model("PartialFloatStrike", new PartialFloatStrike()));
		catLookback.addModel(new Model("TrinomialLookback ", new TrinomialLookback()));
		modelPane.add(catLookback);

		Category catVanilla = new Category("Vanilla");
		catVanilla.addModel(new Model("BlackScholes ", new BlackScholesPanel()));
		catVanilla.addModel(new Model("ForwardStart ", new ForwardStart()));
		catVanilla.addModel(new Model("JumpDiffusion", new JumpDiffusion()));
		modelPane.add(catVanilla);
		
		return modelPane;
	}
	/**
	 * Builds and returns the panel.
	 */
	public JComponent build() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setOpaque(false);
		panel.setBorder(Borders.DIALOG_BORDER);
		panel.add(buildHorizontalSplit());
		return panel;
	}

	/**
	 * Builds and returns the horizontal split using stripped split panes.
	 * <p>
	 * 
	 * Nesting split panes often leads to duplicate borders. However, a
	 * look&feel should not remove borders completely - unless he has good
	 * knowledge about the context: the surrounding components in the component
	 * tree and the border states.
	 */
	private JComponent buildHorizontalSplit() {
		JScrollPane modelMape = new JScrollPane(initModels());
		JComponent left = new JScrollPane(buildTree());
		left.setPreferredSize(new Dimension(200, 100));
		/*
		JComponent upperRight = new JScrollPane(buildTextArea());
		upperRight.setPreferredSize(new Dimension(100, 100));

		JComponent lowerRight = new JScrollPane(modelMape);
		lowerRight.setPreferredSize(new Dimension(100, 100));

		JSplitPane verticalSplit = UIFSplitPane.createStrippedSplitPane(
				JSplitPane.VERTICAL_SPLIT, upperRight, lowerRight);
		*/
		JSplitPane horizontalSplit = UIFSplitPane.createStrippedSplitPane(
				JSplitPane.HORIZONTAL_SPLIT, left, modelMape); // verticalSplit
		return horizontalSplit;
	}

	/**
	 * Builds and returns a sample tree.
	 */
	private JTree buildTree() {
		JTree tree = new JTree(createModelsTreeModel());
		tree.expandRow(3);
		tree.expandRow(2);
		tree.expandRow(1);
		tree.addTreeSelectionListener(new 
				TreeNodeSelectionListener());
		
		return tree;
	}

	/**
	 * Builds and returns a sample text area.
	 */
	private JTextArea buildTextArea() {
		JTextArea area = new JTextArea();
		area
				.setText("May\nI\nKindly\nRemind you that a\nMargin\nImproves a text's readability.");
		return area;
	}

	/**
	 * Builds and returns a sample table.
	 */
	private JTable buildTable() {
		JTable table = new JTable(createSampleTableData(), new String[] {
				"Artist", "Title      " });

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(300);
		table.setRowSelectionInterval(2, 2);
		int tableFontSize = table.getFont().getSize();
		int minimumRowHeight = tableFontSize + 6;
		int defaultRowHeight = LookUtils.IS_LOW_RESOLUTION ? 17 : 18;
		table.setRowHeight(Math.max(minimumRowHeight, defaultRowHeight));
		return table;
	}

	/**
	 * Creates and returns a sample tree model.
	 */
	private TreeModel createModelsTreeModel() {
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Models");
		DefaultMutableTreeNode parent;

		Iterator cats = modelPane.iterator();
		while (cats.hasNext())
		{
			Category cat = (Category)cats.next();
			Iterator mls = cat.getModels();
			parent = new DefaultMutableTreeNode(cat.getName());
			root.add(parent);
			while (mls.hasNext())
			{
				Model mdl = (Model)mls.next();
				parent.add(new DefaultMutableTreeNode(mdl.getName()));
			}
		}

		return new DefaultTreeModel(root);
	}

    DefaultMutableTreeNode getTreeNode(TreePath path)
	{
	    return (DefaultMutableTreeNode)(path.getLastPathComponent());
	}



	/**
	 * Creates and returns sample table data.
	 */
	private String[][] createSampleTableData() {
		return new String[][] { { "Albert Ayler", "Greenwich Village" },
				{ "Carla Bley", "Escalator Over the Hill" },
				{ "Frank Zappa", "Yo' Mama" },
				{ "John Coltrane", "Ascension" },
				{ "Miles Davis", "In a Silent Way" },
				{ "Pharoa Sanders", "Karma" }, { "Wayne Shorter", "Juju" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, { "", "" },
				{ "", "" }, { "", "" }, { "", "" }, { "", "" }, };
	}
	
	class TreeNodeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent event) {
			// DefaultMutableTreeNode node = getTreeNode(event.getPath());
			TreePath tpath=event.getPath();
		    String cardName=""+tpath.getPath()[1];
			CardLayout cl = (CardLayout)(modelPane.getLayout());
	        cl.show(modelPane, cardName);
	        if (tpath.getPath().length>2)
	        {
			    String tabName=""+tpath.getPath()[2];
	        	modelPane.selectTab(cardName, tabName);
	        }
			System.out.println("you selected:" + event.getPath());
		}
	}
}

