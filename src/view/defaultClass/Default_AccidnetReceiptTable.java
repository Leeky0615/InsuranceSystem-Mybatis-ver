package view.defaultClass;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import constants.ViewConstants.EAccidentReceiptHead;
import constants.ViewConstants.EMainFrame;

public class Default_AccidnetReceiptTable extends JTable{
	private static final long serialVersionUID = 1L;
	protected Vector<Vector<Object>> rowData;

	public Default_AccidnetReceiptTable() {
		this.rowData = new Vector<Vector<Object>>();
		
		this.setPreferredSize(new Dimension(EMainFrame.eWidth.getValue(), EMainFrame.eHeight.getValue() / 9 * 3));
		this.setModel(setDefaultTableModel(rowData));
		this.initTable();
	}
	// ��������� ���̺��� ���鶧 ���Ǵ� �޼ҵ�
	public void initTable() {
		DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
		cellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		this.getColumnModel().getColumn(0).setPreferredWidth(70);
		this.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(1).setPreferredWidth(100);
		this.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(2).setPreferredWidth(30);
		this.getColumnModel().getColumn(2).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(3).setPreferredWidth(30);
		this.getColumnModel().getColumn(3).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(4).setPreferredWidth(60);
		this.getColumnModel().getColumn(4).setCellRenderer(cellRenderer);
		this.getColumnModel().getColumn(5).setPreferredWidth(30);
		this.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
		
		this.getTableHeader().setReorderingAllowed(false); 
		this.getTableHeader().setResizingAllowed(false);
		this.getTableHeader().setDefaultRenderer(cellRenderer);
		
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setFillsViewportHeight(true);
	}
	public Vector<Object> setHead(){
		Vector<Object> head = new Vector<Object>();
		for (EAccidentReceiptHead eAccidentReceiptHead : EAccidentReceiptHead.values()) {head.add(eAccidentReceiptHead.getText());}
		return head;
	}
	public DefaultTableModel setDefaultTableModel(Vector<Vector<Object>> rowData) {
		DefaultTableModel dtm = new DefaultTableModel(rowData, setHead()){
			private static final long serialVersionUID = 1L;
			public boolean isCellEditable(int rowindex, int mColIndex) {return false;}
		};
		return dtm;
	}
	public Vector<Object> getRow() {
		try {return this.rowData.get(this.getSelectedRow());}
		catch (Exception e) {return null;}
	}
}
