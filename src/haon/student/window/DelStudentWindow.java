package haon.student.window;

import haon.student.listener.DelStudentWindowActionListener;

import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.Vector;

public class DelStudentWindow extends JDialog {

	DataWindow dataWindow;
	
	Container container;
	JPanel panel;
	JLabel lb_id;
	JTextField text_id;
	JButton btn_del;
	
	public final String COMMOND_DEL = "Del";
	
	
	public DelStudentWindow(DataWindow dataWindow) {
		super(dataWindow,"删除学生",true);
		this.dataWindow = dataWindow;
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setResizable(false);
		
		uiInit();
		
		
		setVisible(true);
	}
	
	public void  uiInit() {
		container = this.getContentPane();
		
		panel = new JPanel(new FlowLayout(FlowLayout.CENTER,12,20));
		
		
		lb_id = new JLabel("输入要删除的学生学号",JLabel.CENTER);
		lb_id.setPreferredSize(new Dimension(300,30));
		lb_id.setFont(new Font("宋体", Font.PLAIN, 20));
		
		
		text_id = new JTextField();
		text_id.setPreferredSize(new Dimension(300,30));
		text_id.setFont(new Font("宋体", Font.PLAIN, 20));

		
		btn_del = new JButton("删除");
		btn_del.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_del.setFocusPainted(false);		//取消焦点标志
		btn_del.setActionCommand(COMMOND_DEL);	//绑定按钮按下命令
		//添加到事件监听器中
		DelStudentWindowActionListener listener = new DelStudentWindowActionListener(this);
		btn_del.addActionListener(listener);
		
		panel.add(lb_id);
		panel.add(text_id);
		panel.add(btn_del);
		
		container.add(panel);
	}
	
	//点击删除按钮之后执行的方法
	public void btnDelClickHandler() {
		
		//获取输入框数据
		String id = text_id.getText();
		//判断是否为空
		if(id.isEmpty()) {
			JOptionPane.showMessageDialog(this,"请输入学号","删除",JOptionPane.WARNING_MESSAGE);
			return ;
		}
		
		//定义一个Vector容器指向 数据界面的容器
		Vector<Vector<Object>> tmpVector = dataWindow.dataVector;
		//建立迭代器用于遍历数据容器
		Iterator<Vector<Object>> it =  tmpVector.iterator();
		//建立标志位，是否找到该学生
		boolean flag = false;
		//遍历学生数据
    	while(it.hasNext()) {
    		Vector<Object> data = it.next();
    		//如果data中的第二项和id匹配,也就是存在该学生
    		if(data.elementAt(2).equals(id) && flag == false) {
    			flag = true;
    			System.out.println(data.elementAt(0));
    			//从学生数据容器中删除这一项数据
    			//tmpVector.removeElement(data);	//此方法会导致除了删除倒数第二个正常，其他均会抛出异常
    			it.remove();		//改为这个
    		}
    		else if(flag) {
    			//删除之后把后面的序号全部 -1
				int no = Integer.parseInt(data.elementAt(0).toString())-1;
				//System.out.println("下一个序号是" + no);
				data.setElementAt(Integer.toString(no), 0);
    		}

    	}
    	
    	if(flag) {
    		//数据界面重新加载表格
    		dataWindow.reloadTable(tmpVector);
    		//弹框提示
    		JOptionPane.showMessageDialog(this,"删除成功","删除",JOptionPane.WARNING_MESSAGE);
    		//清空文本输入框
    		text_id.setText("");
    	}else {
    		//学生数据容器中没有匹配的学号
        	JOptionPane.showMessageDialog(this,"该生不存在","删除",JOptionPane.WARNING_MESSAGE);
    	}
	}
}
