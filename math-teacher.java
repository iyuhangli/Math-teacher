/**
 * Title      : Jdbc.java
 * Description: This class contains the class for young children 
 * to practice simple arithmetic.
 * Copyright  : Copyright (c) 2018 - 2018
 * @author  Yuhang Li
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This is MathTeacher class, it extends JFrame, and it 
 * reputation some necessary variables.
 */
public class Jdbc extends JFrame{
	private static final long serialVersionUID = -703762691834380067L;
	JPanel ques,answ,butt,buts,keyb,keyc,left,righ,tabl;
	JButton rese,chec,nexx,jbut[];
	JLabel jls1,jls2,jls3,jls4;
	JTextField jt=new JTextField(4);
	JTable table;
	JScrollPane scrollPane;
	int result,cor,sum,checkok=1,first=0,checc=0;
	String testwhat,re;
	
	/**
	 * New some modules, and make layout.
	 */
	public Jdbc() {
		table=new JTable(new MyTableModel());
		table.setPreferredScrollableViewportSize(new Dimension(310,110));
		DefaultTableCellRenderer tcr= new DefaultTableCellRenderer();
		tcr.setHorizontalAlignment(JLabel.CENTER);//Middle alignment of data in a table
		table.setDefaultRenderer(Object.class,tcr);
		scrollPane=new JScrollPane(table);//Add scrollbar.
		table.setFillsViewportHeight(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(20);
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);//Adjustment form
		
		/**
		 * New panel, button, label, text box, table and other necessary variables.
		 */
		ques=new JPanel();
		answ=new JPanel();
		buts=new JPanel();
		butt=new JPanel();
		keyb=new JPanel();
		keyc=new JPanel();
		righ=new JPanel();
		tabl=new JPanel();
		rese=new JButton("Reset");
		chec=new JButton("Press for answer");
		nexx=new JButton("Next");
		jbut=new JButton[12];
		jls1=new JLabel();
		jls2=new JLabel("Your answer:");
		jls3=new JLabel();
		jls4=new JLabel();
		ques.add(jls1);
		answ.add(jls2);
		answ.add(jt);
		buts.add(chec);
		butt.add(rese);
		butt.add(nexx);
		keyb.add(jls3);
		keyc.add(jls4);
		righ.setLayout(new GridLayout(4,3,10,10));
		jbut[0]=new JButton("7");
		jbut[1]=new JButton("8");
		jbut[2]=new JButton("9");
		jbut[3]=new JButton("4");
		jbut[4]=new JButton("5");
		jbut[5]=new JButton("6");
		jbut[6]=new JButton("1");
		jbut[7]=new JButton("2");
		jbut[8]=new JButton("3");
		jbut[9]=new JButton("-");
		jbut[10]=new JButton("0");
		jbut[11]=new JButton("C");		
		for(int i=0;i<12;i++) {
			righ.add(jbut[i]);
		}
		tabl.add(scrollPane);
		this.setSize(400,460);
		this.setLayout(null);
		righ.setBounds(200,30,160,180);
		ques.setBounds(30,20,140,40);
		answ.setBounds(40,60,120,50);
		buts.setBounds(16,136,160,40);
		butt.setBounds(26,176,140,40);
		keyc.setBounds(16,116,180,28);
		keyb.setBounds(110,380,160,20);
		tabl.setBounds(-55,240,500,200);
		this.add(ques);//Add element
		this.add(answ);
		this.add(buts);
		this.add(butt);
		this.add(keyb);
		this.add(keyc);
		this.add(righ);
		this.add(tabl);
		this.setVisible(true);//Set JFrame's format
		this.setDefaultCloseOperation(Jdbc.EXIT_ON_CLOSE);
		this.setTitle("Math Teacher");
		jls1.setFont(new java.awt.Font("Times New Roman", 1,18));
		nexx.setEnabled(false);
		result=0;
		sum=0;
		display();
	}
	
    /** This is the display method for the problems.
     * It created the calculation problem.
     */
	public void display() {
		int ch=(int)(1+Math.random()*4);
		int n1=(int)(1+Math.random()*10);
		int n2=(int)(1+Math.random()*10);
		int n3=n1*n2;
		if(ch==4) {//The division operation is guaranteed to be less than 10
			while(n1*n2>10) {
				n1=(int)(1+Math.random()*10);
				n2=(int)(1+Math.random()*10);
				n3=n1*n2;
			}
		}
		result=0;
		String s="";
		switch(ch) {
		case 1:
			s="+";
			result=n1+n2;
			break;
		case 2:
			s="-";
			result=n1-n2;
			break;
		case 3:
			s="*";
			result=n1*n2;
			break;
		case 4:
			s="/";
			result=n3/n1;
			break;
		}
		if(ch!=4) {//Display the problem and record
			testwhat=Integer.toString(n1)+s+Integer.toString(n2);
			jls1.setText("Question:  "+Integer.toString(n1)+s+Integer.toString(n2));
		}
		else {
			testwhat=Integer.toString(n3)+s+Integer.toString(n1);
			jls1.setText("Question:  "+Integer.toString(n3)+s+Integer.toString(n1));
		}
	}
	
    /** This is the action method for mouse's action.
     * It gives feedback to the action of the mouse.
     */
	public void action() {
		chec.addActionListener(new checListener());
		rese.addActionListener(new reseListener());
		nexx.addActionListener(new nexxListener());
		for(int temp=0;temp<11;temp++) {
			jbut[temp].addActionListener(new jbutListener());
		}
		jbut[11].addActionListener(new cleaListener());
	}
	
	class checListener implements ActionListener{
	    /** This is the listener method for action in button "check".
	     * It checks the answers right or not and gives the results.
	     * @param che The action event in "check" button.
	     */
		public void actionPerformed(ActionEvent che) {
			/**
			 * When you enter illegal characters, the program does not stop running, but gives hints.
			 */
			Pattern pattern=Pattern.compile("-?[0-9]+\\.?[0-9]*");
			Matcher isNum = pattern.matcher(jt.getText());
			checc=0;
			if(!isNum.matches()) {
				checc=1;
			}
			if(jt.getText().length()==0||jt.getText().equals("")||checc==1) {
				jls4.setText("Format error, please try again!");
			}
			else {
				int answer=Integer.parseInt(jt.getText());
				if(answer==result) {
					cor++;
					sum++;
					jls4.setText("Your answer is right");
					re="Right";
				}
				else {
					jls4.setText("Your answer is wrong");
					sum++;
					re="Wrong, the answer is "+result;
				}
				jls3.setText(cor+" correct out of "+sum);
				chec.setEnabled(false);//Let button disabled
				jt.setEnabled(false);
				int no=first+1;
				table.setValueAt(Integer.toString(no), first, 0);
				table.setValueAt(testwhat, first, 1);
				table.setValueAt(Integer.toString(answer), first, 2);
				table.setValueAt(re, first, 3);
				first++;
				checkok=0;
				nexx.setEnabled(true);
			}
		}
	}
	
	class reseListener implements ActionListener{
		
	    /** This is the listener method for action in button "reset".
	     * It clear the history of the mathematical problem.
	     * @param res The action event in "reset" button.
	     */
		public void actionPerformed(ActionEvent res) {//Reset the program
			cor=0;
			sum=0;
			display();
			jls3.setText(null);
			jls4.setText(null);
			chec.setEnabled(true);
			jt.setEnabled(true);
			checkok=1;
			jt.setText(null);
			first=0;
			for(int p=0;p<99;p++) {
				for(int q=0;q<4;q++) {
					table.setValueAt("", p, q);
				}
			}
			nexx.setEnabled(true);
		}
	}
	
	class nexxListener implements ActionListener{
		
	    /** This is the listener method for action in button "next".
	     * It display next mathematical problem.
	     * @param nex The action event in "next" button.
	     */
		public void actionPerformed(ActionEvent nex) {
			display();
			jls4.setText(null);
			jt.setText(null);
			chec.setEnabled(true);
			jt.setEnabled(true);
			checkok=1;
			nexx.setEnabled(false);
		}
	}
	
	class jbutListener implements ActionListener{
	    /** This is the listener method for action in virtual keyboard.
	     * It implement the virtual keyboard event.
	     * @param num The action event in which button named 0-9,and "-".
	     */
		public void actionPerformed(ActionEvent num) {
			if(checkok==1) {
				String buttonName=num.getActionCommand();
				String befo=jt.getText();
				jt.setText(befo+buttonName);
			}
		}
	}
	
	class cleaListener implements ActionListener{
	    /** This is the listener method for action in button "clean".
	     * It clean the text box.
	     * @param c The action event in "clean" button.
	     */
		public void actionPerformed(ActionEvent c) {
			jt.setText("");
		}
	}
	
	class MyTableModel extends AbstractTableModel{//Setting up my own table model
		private static final long serialVersionUID = 1L;
		String[] columnNames= {"NO.","History","Your answer","Result"};
		Object[][] data=new String[99][4];
		
	    /** This is the method for count the column.
	     * @return columnNames.length The column's number.
	     */
		public int getColumnCount() {
			return columnNames.length;
		}
		
	    /** This is the method for count the rows.
	     * @return data.length The row's number.
	     */
		public int getRowCount() {
			return data.length;
		}

	    /** This is the method for return the column's name.
	     * @param col The number of column.
	     * @return columnNames[col] The column's name.
	     */
	    public String getColumnName(int col) {  
	        return columnNames[col];  
	    } 
	    
	    /** This is the method for get the value of table.
	     * @param row The number of row.
	     * @param col The number of column.
	     * @return data[row][col] The value of the table in row and column.
	     */
	    public Object getValueAt(int row, int col) {  
	        return data[row][col];  
	    }
		
	    /** This is the method for decide whether the form can be edited.
	     * @param row The number of row.
	     * @param col The number of column.
	     * @return false The form can't be edited.
	     */
		public boolean iscellEditable(int row, int col) {
			return false;
		}
		
	    /** This is the method for set value of form.
	     * @param value The value which want to add.
	     * @param row The number of row.
	     * @param col The number of column.
	     */
		public void setValueAt(Object value,int row,int col) {
			data[row][col]=value;
			fireTableCellUpdated(row,col);
		}
	}
	
    /** This is the main method for this program.
     * It creates a new instance of my program and calls the program's method.
     * @param args This program does not use this parameter.
     */
	public static void main(String[] args) {
		Jdbc mathtest=new Jdbc();
		mathtest.action();
	}
}


