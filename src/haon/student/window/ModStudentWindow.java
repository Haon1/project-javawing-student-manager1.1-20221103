package haon.student.window;

import haon.student.listener.ModStudentWindowActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class ModStudentWindow extends JDialog {
	
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
	JButton btn_find;
	JButton btn_mod;
	
	public final String COMMOND_FIND = "Find";
	public final String COMMOND_MOD  = "Mod";
	
	int modIndex;		//要修改的学生的索引
	
	public ModStudentWindow(DataWindow dataWindow) {
		super(dataWindow,"修改信息",true);
		this.dataWindow = dataWindow;
		setSize(400,450);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);

		//UI控件初始化
		uiInit();
		
		//设置窗体可见
		setVisible(true);
	}
	
	//UI初始化
	public void  uiInit() {
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

		
		btn_mod = new JButton("修改");
		btn_mod.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_mod.setFocusPainted(false);		//取消焦点标志
		btn_mod.setActionCommand(COMMOND_MOD);
		
		btn_find = new JButton("查找");
		btn_find.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_find.setFocusPainted(false);		//取消焦点标志
		btn_find.setActionCommand(COMMOND_FIND);

		//添加到事件监听器中
		ModStudentWindowActionListener listener = new ModStudentWindowActionListener(this);
		btn_mod.addActionListener(listener);
		btn_find.addActionListener(listener);
		
		//禁止修改
		modifyDisable();
		
		
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
		
		panel.add(btn_find);
		panel.add(btn_mod);
		
		container.add(panel);
	}
	
	
	
	//禁止修改
	public void modifyDisable() {
		text_name.setEnabled(false);
		text_cn.setEnabled(false);
		text_math.setEnabled(false);
		text_eng.setEnabled(false);
		text_all.setEnabled(false);
		
		btn_mod.setEnabled(false);
		btn_find.setEnabled(true);
	}
	
	//允许修改
	public void modifyEnable() {
		text_name.setEnabled(true);
		text_cn.setEnabled(true);
		text_math.setEnabled(true);
		text_eng.setEnabled(true);
		//text_all.setEnabled(true);
		
		btn_mod.setEnabled(true);
		btn_find.setEnabled(false);
	}
	
	//点击查询之后执行的方法
	public void btnFindClickHandler(){
		
		//获取输入的学号
		String destId = text_id.getText();
		//判断是否有输入
		if(destId.isEmpty()) {
			JOptionPane.showMessageDialog(this,"请输入学号搜索","修改信息",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		//定义一个Vector容器指向 数据界面的容器
		Vector<Vector<Object>> tmpVector = dataWindow.dataVector;
		//建立迭代器用于遍历数据容器
		Iterator<Vector<Object>> it =  tmpVector.iterator();
		//遍历学生数据
    	while(it.hasNext()) {
    		Vector<Object> data = it.next();
    		//如果data中的第二项和id匹配,也就是存在该学生
    		if(data.elementAt(2).equals(destId)) {
    			//把学生信息填充到文本输入框中
    			text_name.setText(data.elementAt(1).toString());	//填充姓名
    			text_cn.setText(data.elementAt(3).toString());		//填充语文
    			text_math.setText(data.elementAt(4).toString());	//填充数学
    			text_eng.setText(data.elementAt(5).toString());		//填充英语
    			text_all.setText(data.elementAt(6).toString());		//填充总分
    			
    			//打开输入框编辑功能
    			modifyEnable();
    			
    			//记录当前索引位置,就是要修改的位置
    			modIndex =  tmpVector.indexOf(data);
    			return ;
    		}

    	}
    	//学生数据容器中没有匹配的学号
    	JOptionPane.showMessageDialog(this,"该生不存在","修改信息",JOptionPane.WARNING_MESSAGE);
	}
	
	
	//点击修改之后执行的方法
	public void btnModClickHandler(){
		
		System.out.println("修改索引为 " + modIndex);
		//新建保存输入数据的容器
		Vector<Object> inputData = new Vector<>();
		
		//拿出输入框上的文字
		String name = text_name.getText();
		String id = text_id.getText();
		String cn = text_cn.getText();
		String math = text_math.getText();
		String eng = text_eng.getText();
		
		//判断数据输入是否正确
		if(name.isEmpty() || id.isEmpty() || cn.isEmpty() || math.isEmpty() || eng.isEmpty()) {
			JOptionPane.showMessageDialog(this,"数据不完整","修改信息",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		//填充总分
		float all = Float.parseFloat(cn) + Float.parseFloat(math) + Float.parseFloat(eng);
		text_all.setText(Float.toString(all));
		
		//序号是索引+1  因为容器是从0开始
		inputData.addElement(modIndex+1);
		
		//把每个输入的数据都添加到容器中
		inputData.addElement(name);
		inputData.addElement(id);
		inputData.addElement(cn);
		inputData.addElement(math);
		inputData.addElement(eng);
		inputData.addElement(all);
		
		//将此向量指定 要修改的位置modIndex 处的组件设置为指定的对象 inputData。
		dataWindow.dataVector.setElementAt(inputData, modIndex); 
		
		//执行父窗体的重新加载table
		dataWindow.reloadTable(dataWindow.dataVector);
		//提示
		JOptionPane.showMessageDialog(this,"修改成功","修改信息",JOptionPane.WARNING_MESSAGE);
		
		//清空所有输入框
		text_name.setText("");
		text_id.setText("");
		text_cn.setText("");
		text_math.setText("");
		text_eng.setText("");
		text_all.setText("");
		
		//禁止修改,直到再次搜索学号
		modifyDisable();
	}

}
