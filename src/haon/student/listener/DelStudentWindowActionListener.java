package haon.student.listener;

import haon.student.window.DelStudentWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DelStudentWindowActionListener implements ActionListener {

	//用于删除窗口的对象,因为需要调用窗口的方法
	DelStudentWindow delStudentWindow;
	
	//构造方法
	public DelStudentWindowActionListener(DelStudentWindow delStudentWindow) {
		this.delStudentWindow = delStudentWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		if(delStudentWindow.COMMOND_DEL.equals(command)) {
			System.out.println("点击删除");
			//调用删除窗口的btnDelClickHandler方法
			delStudentWindow.btnDelClickHandler();
		}
		
	}

}
