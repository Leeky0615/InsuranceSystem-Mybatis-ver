package view.defaultClass;

import javax.swing.JPanel;

import main.Menu;

// ���Ǵ� �гε��� SuperClass
public abstract class DefaultPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	protected Menu menu;
	public DefaultPanel(Menu menu) {this.menu = menu;}
	public abstract void createPanel();
}
