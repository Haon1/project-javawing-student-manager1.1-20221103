package haon.student.listener;

import haon.student.window.AddStudentWindow;
import haon.student.window.DataWindow;
import haon.student.window.DelStudentWindow;
import haon.student.window.ModStudentWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DataWindowActionListener implements ActionListener {

	
	private DataWindow dataWindow;
	
	public DataWindowActionListener(DataWindow dataWindow) {
		this.dataWindow = dataWindow;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();	//获取命令得到对应的按钮
		
		//增加按钮被点击
		if(dataWindow.COMMAND_ADD.equals(command)) {
			System.out.println("增加");
			new AddStudentWindow(dataWindow);
		//删除按钮被点击
		}else if(dataWindow.COMMAND_DEL.equals(command)) {
			System.out.println("删除");
			new DelStudentWindow(dataWindow);
		//修改按钮被点击
		}else if(dataWindow.COMMAND_MOD.equals(command)) {
			System.out.println("修改");
			new ModStudentWindow(dataWindow);
		//查询按钮被点击
		}else if(dataWindow.COMMAND_FIND.equals(command)) {
			System.out.println("查询");
			dataWindow.search();
		//返回按钮被点击
		}else if(dataWindow.COMMAND_BACK.equals(command)) {
			System.out.println("返回");
			dataWindow.back();
		}else if(dataWindow.COMMAND_PRE.equals(command)) {
			System.out.println("上一页");
		//返回按钮被点击
		}else if(dataWindow.COMMAND_NEXT.equals(command)) {
			System.out.println("下一页");
		}
		
	}
		

}
