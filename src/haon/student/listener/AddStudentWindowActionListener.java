package haon.student.listener;

import haon.student.window.AddStudentWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudentWindowActionListener implements ActionListener {

	
	AddStudentWindow addStudentWindow;
	
	public AddStudentWindowActionListener(AddStudentWindow addStudentWindow) {
		this.addStudentWindow = addStudentWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		if(addStudentWindow.COMMOND_ADD.equals(command)) {
			System.out.println("点击添加");
			addStudentWindow.btnAddClickHandler();
		}
	}

}
