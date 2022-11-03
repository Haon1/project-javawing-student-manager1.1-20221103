package haon.student.window;

import haon.student.listener.AddStudentWindowActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AddStudentWindow extends JDialog {
	
	DataWindow dataWindow;
	
	Container container;
	JPanel panel;
	JLabel lb_name;
	JLabel lb_id;
	JLabel lb_cn;
	JLabel lb_math;
	JLabel lb_eng;
	JLabel lb_all;
	JTextField text_name;
	JTextField text_id;
	JTextField text_cn;
	JTextField text_math;
	JTextField text_eng;
	JTextField text_all;
	JButton btn_add;
	
	public final String COMMOND_ADD = "Add";
	
	
	public AddStudentWindow(DataWindow dataWindow) {
		super(dataWindow,"添加学生",true);
		this.dataWindow = dataWindow;
		setSize(400,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		uiInit();
		
		
		setVisible(true);
	}
	
	
	void  uiInit() {
		container = this.getContentPane();
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,12,20));
		
		lb_name = new JLabel("姓名:",JLabel.RIGHT);
		lb_name.setPreferredSize(new Dimension(100,30));
		lb_name.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_id = new JLabel("学号:",JLabel.RIGHT);
		lb_id.setPreferredSize(new Dimension(100,30));
		lb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_cn = new JLabel("语文成绩:",JLabel.RIGHT);
		lb_cn.setPreferredSize(new Dimension(100,30));
		lb_cn.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_math = new JLabel("数学成绩:",JLabel.RIGHT);
		lb_math.setPreferredSize(new Dimension(100,30));
		lb_math.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_eng = new JLabel("英语成绩:",JLabel.RIGHT);
		lb_eng.setPreferredSize(new Dimension(100,30));
		lb_eng.setFont(new Font("宋体", Font.PLAIN, 20));
		
		lb_all = new JLabel("总分:",JLabel.RIGHT);
		lb_all.setPreferredSize(new Dimension(100,30));
		lb_all.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_name = new JTextField();
		text_name.setPreferredSize(new Dimension(200,30));
		text_name.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_id = new JTextField();
		text_id.setPreferredSize(new Dimension(200,30));
		text_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_cn = new JTextField();
		text_cn.setPreferredSize(new Dimension(200,30));
		text_cn.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_math = new JTextField();
		text_math.setPreferredSize(new Dimension(200,30));
		text_math.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_eng = new JTextField();
		text_eng.setPreferredSize(new Dimension(200,30));
		text_eng.setFont(new Font("宋体", Font.PLAIN, 20));
		
		text_all = new JTextField();
		text_all.setPreferredSize(new Dimension(200,30));
		text_all.setFont(new Font("宋体", Font.PLAIN, 20));
		text_all.setEnabled(false);

		
		btn_add = new JButton("添加");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_add.setFocusPainted(false);		//取消焦点标志
		btn_add.setActionCommand(COMMOND_ADD);	//绑定按钮按下命令
		//添加到事件监听器中
		AddStudentWindowActionListener listener = new AddStudentWindowActionListener(this);
		btn_add.addActionListener(listener);
		
		
		panel.add(lb_name);
		panel.add(text_name);
		panel.add(lb_id);
		panel.add(text_id);
		panel.add(lb_cn);
		panel.add(text_cn);
		panel.add(lb_math);
		panel.add(text_math);
		panel.add(lb_eng);
		panel.add(text_eng);
		panel.add(lb_all);
		panel.add(text_all);
		
		panel.add(btn_add);
		
		container.add(panel);
	}
	
	//点击添加之后执行的方法
	public void btnAddClickHandler(){
		//新建一个容器
		Vector<Object> inputData = new Vector<>();
		
		//拿出输入框上的文字
		String name = text_name.getText();
		String id = text_id.getText();
		String cn = text_cn.getText();
		String math = text_math.getText();
		String eng = text_eng.getText();
		
		//判断数据输入是否正确
		if(name.isEmpty() || id.isEmpty() || cn.isEmpty() || math.isEmpty() || eng.isEmpty()) {
			JOptionPane.showMessageDialog(this,"数据不完整","添加",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		//填充总分
		float all = Float.parseFloat(cn) + Float.parseFloat(math) + Float.parseFloat(eng);
		text_all.setText(Float.toString(all));
		
		//把每个输入的数据都添加到容器中
		inputData.addElement(dataWindow.dataVector.size()+1);
		inputData.addElement(name);
		inputData.addElement(id);
		inputData.addElement(cn);
		inputData.addElement(math);
		inputData.addElement(eng);
		inputData.addElement(all);
		
		//把数据添加到父窗体的dataVector中
		dataWindow.dataVector.addElement(inputData);
		//执行父窗体的重新加载table
		dataWindow.reloadTable(dataWindow.dataVector);
		//提示
		JOptionPane.showMessageDialog(this,"添加成功","添加",JOptionPane.WARNING_MESSAGE);
		
		//清空所有输入框
		text_name.setText("");
		text_id.setText("");
		text_cn.setText("");
		text_math.setText("");
		text_eng.setText("");
		text_all.setText("");
	}

}
