package view.mainFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import constants.ViewConstants.EButton;
import constants.ViewConstants.ELogin;
import constants.ViewConstants.EPanels;
import constants.ViewConstants.EViewFrame;
import control.login.Login;
import control.login.LoginList;
import control.login.LoginListImpl;
import main.Menu;
import view.defaultClass.DefaultPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public static ELogin user = null;
	private Vector<DefaultPanel> panels;
	private ButtonPanel buttonPanel;
	private LoginPanel loginPanel;
	private BackBtnPanel backBtnPanel;
	private DefaultPanel currentPanel;
	private ActionHandler actionHandler;
	private Menu menu;
	private JLabel imageLabel;
	private LoginList loginList;
	
	public MainFrame(Menu menu) {
		super();
		this.setThema();
		this.setTitle("���� �ý���");
		this.setSize(new Dimension(611,580));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBackground(new Color(94,175,164));
		this.setLayout(new BorderLayout());
		this.menu = menu;
		this.loginList = (LoginListImpl) menu.getLoginlist();
		
		this.actionHandler = new ActionHandler();
		this.panels = new Vector<DefaultPanel>();
		
		this.createLoginPanel();
		this.createButtonPanel();
	}
	
	// �׸� ���� �޼ҵ�
	public void setThema() {
		try {
		    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		    JFrame.setDefaultLookAndFeelDecorated(true);
		} catch (Exception e) {}
	}
	// ���������ӿ� �⺻ �гε��� ����� �޼ҵ�
	private void createLoginPanel() {
		this.loginPanel = new LoginPanel(this.actionHandler);
		this.add(this.loginPanel, BorderLayout.CENTER);
	}
	public void createButtonPanel() {
		this.buttonPanel = new ButtonPanel(this.actionHandler);
	}
	private void createDefaultPanels() {
		this.backBtnPanel = new BackBtnPanel(actionHandler);
		this.add(backBtnPanel, BorderLayout.SOUTH);
		
		this.imageLabel = new JLabel(setImage());
		this.add(this.imageLabel, BorderLayout.CENTER);
		
		// ���α׷����� ���Ǵ� �гε��� ����ϱ����� �гκ��͸� ����� �־��.
		for(EPanels ePanel : EPanels.values()) {this.panels.add(this.createPanel(ePanel.getPanel()));}
	}
	
	// ������������ imagelabel�� �̹����� �ֱ����� �̹��������� ����ϴ� �޼ҵ�
	public ImageIcon setImage() {
		ImageIcon icon = EViewFrame.eImg.getImageIcon();
		Image originImg = icon.getImage(); 
		Image changedImg= originImg.getScaledInstance(600, 530, Image.SCALE_SMOOTH );
		ImageIcon changedicon = new ImageIcon(changedImg);
		return changedicon;
	}
	
	// Reflection�� �̿��� �г��� ������ִ� �޼ҵ�
	@SuppressWarnings("rawtypes")
	public DefaultPanel createPanel(String name) {
		try{
			Class<?> clazz = Class.forName("view."+name.toLowerCase()+"."+name);
			Class arg = Menu.class;
			Object newPanel = clazz.getConstructor(arg).newInstance(this.menu);
			return (DefaultPanel) newPanel;
		}catch (Exception e) {e.printStackTrace();}
		return null;
	}
	@SuppressWarnings("deprecation")
	public ELogin logincheck() {
		ELogin userType = null;
		for(Login login : this.loginList.getLoginList()) {
			if (login.getId().equals(this.loginPanel.getLoginTextField().getText()) && login.getPassword().equals(this.loginPanel.getPasswordField().getText())) {
				userType = login.getLogin();
			}
		}
		MainFrame.user = userType;
		return userType;
	}
	// ��ư�� Ŭ���� ����Ǵ� �޼ҵ�
	public void buttonClick(String name) {
		Vector<JButton> buttons = this.buttonPanel.getButtons();
		if(name.equals("login")){
			if(logincheck() == null){
				JOptionPane.showMessageDialog(null, "��ϵ��� ���� ������Դϴ�. �ٽ� Ȯ���� �ּ���.", "��ϵ��� ���� �����", JOptionPane.WARNING_MESSAGE);
				return;
			}else {
				if (logincheck() == ELogin.developer) {
					for(JButton button : buttons) {
						if (button.equals(buttons.get(EButton.insDevelopmentBtn.ordinal()))) {
							button.setVisible(true);
						}else {
							button.setVisible(false);
						}
					}
				}else if(logincheck() == ELogin.customer) {
					for(JButton button : buttons) {
						if (button.equals(buttons.get(EButton.insRegistrationBtn.ordinal()))) {
							button.setVisible(true);
						}else {
							button.setVisible(false);
						}
					}
				}else if(logincheck() == ELogin.salesman) {
					for(JButton button : buttons) {
						if (button.equals(buttons.get(EButton.checkCustomerBtn.ordinal()))) {
							button.setVisible(true);
						}else {
							button.setVisible(false);
						}
					}
				}else if(logincheck() == ELogin.underwriter) {
					for(JButton button : buttons) {
						if (button.equals(buttons.get(EButton.acceptInsBtn.ordinal())) || button.equals(buttons.get(EButton.insRegistrationBtn.ordinal()))) {
							button.setVisible(true);
						}else {
							button.setVisible(false);
						}
					}
				}else if(logincheck() == ELogin.assessment) {
					for(JButton button : buttons) {
						if (button.equals(buttons.get(EButton.insCoverBtn.ordinal()))) {
							button.setVisible(true);
						}else {
							button.setVisible(false);
						}
					}
				}
				this.createDefaultPanels();
				this.add(this.buttonPanel, BorderLayout.NORTH);
				this.backBtnPanel.setVisible(false);
				this.loginPanel.setVisible(false);
				this.repaint();
			}
		}
		else if (name.equals("backToMain")) {
			// ó��ȭ������ ���� : ���� �������� �ʱ�ȭ�ϰ� �гκ��Ϳ��� �����г��� ������.
			this.remove(currentPanel);
			this.createDefaultPanels();
			this.createButtonPanel();
			this.add(this.buttonPanel, BorderLayout.NORTH);
			this.backBtnPanel.setVisible(false);
			this.buttonPanel.setVisible(true);
			this.panels.remove(this.currentPanel);
			this.repaint();
		} else if(name.equals("logout")){
			this.createLoginPanel();
			this.remove(buttonPanel);
			this.remove(backBtnPanel);
			this.remove(currentPanel);
			this.createButtonPanel();
			this.loginPanel.setVisible(true);
			this.backBtnPanel.setVisible(false);
			this.imageLabel.setVisible(false);
			this.panels.remove(this.currentPanel);
			this.repaint();
			this.revalidate();
		}else {
			// ��ư�гο� �ִ� ��ư Ŭ����
			this.backBtnPanel.setVisible(true);
			this.remove(this.imageLabel);
			this.remove(this.buttonPanel);
			// �г��� ũ�Ⱑ 4�̸� ���õ� �гι�ư�� �г��� �߰�
			if (this.panels.size() == 4) {this.panels.add(createPanel(name));}
			/* 
			 * ������ ������� �г� ���Ϳ��� ���� �ϳ��� ���� 
			 * ���õ� �г��� �̸��� ���� ��  ���������ӿ� �г��� �ٿ��ְ� �����гη� ���� 
			*/
			for (DefaultPanel panel : this.panels) {
				if (panel.getClass().getName().equals("view." + name.toLowerCase() + "." + name)) {
					getContentPane().add(panel, BorderLayout.CENTER);
					this.currentPanel = panel;
				}
			}
		}
	}
	
	public class ActionHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			buttonClick(e.getActionCommand());
		}
	}
}
