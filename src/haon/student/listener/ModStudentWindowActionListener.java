package haon.student.listener;

import haon.student.window.ModStudentWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModStudentWindowActionListener implements ActionListener {

	//用于删除窗口的对象,因为需要调用窗口的方法
	ModStudentWindow modStudentWindow;
	
	//构造方法
	public ModStudentWindowActionListener(ModStudentWindow modStudentWindow) {
		this.modStudentWindow = modStudentWindow;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		
		//修改按钮被点击
		if(modStudentWindow.COMMOND_MOD.equals(command)) {
			System.out.println("点击修改");
			//调用修改窗口的btnModClickHandler方法
			modStudentWindow.btnModClickHandler();
			
		//查找按钮被点击
		}else if(modStudentWindow.COMMOND_FIND.equals(command)) {
			System.out.println("点击查找");
			//调用修改窗口的btnFindClickHandler方法
			modStudentWindow.btnFindClickHandler();
		}
	}

}
