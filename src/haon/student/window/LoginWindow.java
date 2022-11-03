package haon.student.window;

import haon.student.listener.LoginWindowActionListener;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class LoginWindow extends JFrame {

	//数据窗口对象
	DataWindow datawin;
	
	JPanel panel;
	JLabel lb_usr;
	JLabel lb_pwd;
	JLabel lb_mode;
	
	JButton btn_login;
	JButton btn_reset;
	JButton btn_exit;
	
	JTextField 		text_usr;
	JPasswordField 	text_pwd;
	
	JRadioButton sel_teacher;
	JRadioButton sel_student;
	ButtonGroup  btnGroup;
	
	String identity;
	
	final static String TITLE	= "学生管理系统 202160930222";
	public final String COMMAND_LOGIN 	= "Login";
	public final String COMMAND_RESET 	= "Reset";
	public final String COMMAND_EXIT 	= "Exit";
	//final String COMMAND_ENTER 	= "Enter";
	final String USERNAME 		= "admin";
	final String PASSWORD		= "admin";
	final String TEACHER		= "Teacher";
	final String STUDENT		= "Student";
	
	//构造方法
	LoginWindow(){
		super(TITLE);
		this.setResizable(false);
		init();
		datawin = new DataWindow(this);
	}
	
	//组件初始化
	public void init() {
		//设置窗体大小
		this.setSize(480, 400);
		int windowWidth = this.getWidth(); //获得窗口宽
		int windowHeight = this.getHeight(); //获得窗口高
		Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
		Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
		int screenWidth = screenSize.width; //获取屏幕的宽
		int screenHeight = screenSize.height; //获取屏幕的高
		this.setLocation((screenWidth - windowWidth)/2, (screenHeight - windowHeight)/2);//设置窗口居中显示
		//居中显示方法2
		//this.setLocationRelativeTo(null);

		//退出关闭结束进程
		this.setDefaultCloseOperation(LoginWindow.EXIT_ON_CLOSE);

		//设置窗口图标
		URL resource = LoginWindow.class.getClassLoader().getResource("icon.png");
		Image image = new ImageIcon(resource).getImage();
		this.setIconImage(image);

		//主面板
		panel = new JPanel();
		panel.setLayout(null);		//使用绝对布局 ***否则添加文本框之后组件全消失
		this.setContentPane(panel);
		
		//JLabel
		lb_usr = new JLabel("用户名:");
		lb_usr.setFont(new  Font("宋体",Font.PLAIN,20));
		lb_usr.setBounds(80, 50, 80, 30);
		lb_pwd = new JLabel("密  码:");
		lb_pwd.setFont(new  Font("宋体",Font.PLAIN,20));
		lb_pwd.setBounds(80, 116, 80, 30);
		lb_mode = new JLabel("权  限:");
		lb_mode.setFont(new  Font("宋体",Font.PLAIN,20));
		lb_mode.setBounds(80, 177, 80, 30);
		
		//文本输入框
		text_usr = new JTextField(11);
		text_usr.setFont(new Font("宋体", Font.PLAIN, 20));
		text_usr.setBounds(160, 50, 181, 30);
		text_pwd = new JPasswordField(16);
		text_pwd.setFont(new Font("宋体", Font.PLAIN, 20));
		text_pwd.setBounds(160, 116, 181, 30);
		
		//JButton
		btn_login = new JButton("登录");
		btn_login.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_login.setBounds(64, 247,102, 30);
		btn_login.setFocusPainted(false);		//取消焦点标志
		btn_login.setActionCommand(COMMAND_LOGIN);		//按钮添加动作命令
		btn_reset = new JButton("重置");
		btn_reset.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_reset.setBounds(176, 247, 98, 30);
		btn_reset.setFocusPainted(false);
		btn_reset.setActionCommand(COMMAND_RESET);	//按钮添加动作命令
		btn_exit = new JButton("退出");
		btn_exit.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_exit.setBounds(284, 247, 98, 30);
		btn_exit.setFocusPainted(false);
		btn_exit.setActionCommand(COMMAND_EXIT);	//按钮添加动作命令

		//把按钮添加到事件监听器中,监听是否按下
		LoginWindowActionListener listener = new LoginWindowActionListener(this);
		btn_login.addActionListener(listener);
		btn_reset.addActionListener(listener);
		btn_exit.addActionListener(listener);
		
		//单选按钮
		sel_teacher = new JRadioButton("教师");
		sel_teacher.setFont(new Font("宋体", Font.PLAIN, 20));
		sel_teacher.setBounds(172, 177, 69, 23);
		
		sel_student = new JRadioButton("学生");
		sel_student.setSelected(true);
		sel_student.setFont(new Font("宋体", Font.PLAIN, 20));
		sel_student.setBounds(272, 177, 69, 23);
		
		// 创建按钮组，把两个单选按钮添加到该组
        btnGroup = new ButtonGroup();
        btnGroup.add(sel_teacher);
        btnGroup.add(sel_student);

		
		//添加组件到Panel中
		panel.add(lb_usr);
		panel.add(lb_pwd);
		panel.add(lb_mode);
		panel.add(btn_login);
		panel.add(btn_reset);
		panel.add(btn_exit);
		panel.add(text_usr);
		panel.add(text_pwd);
		panel.add(sel_teacher);
		panel.add(sel_student);
		
		//主窗体可视化
		//this.setVisible(true);
	}
	
	//检查账号密码是否正确
	public boolean checkAccount() {
		//获取输入
		String usr = text_usr.getText();
		char []pwd = text_pwd.getPassword();
		String password = String.valueOf(pwd);
		//账号密码均正确
		if(USERNAME.equals(usr)&&PASSWORD.equals(password)) {
			return true;
		}else {
			JOptionPane.showMessageDialog(this,"账号或密码错误","提示",JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}
	//登录到数据界面
	public void login() {
		
		//检查账号密码是否正确
		boolean result = checkAccount();
		if(result) {
			//确认登录身份
			if(sel_student.isSelected()) {
				this.identity = this.STUDENT;
			}else {
				this.identity = this.TEACHER;
			}
			//隐藏登录窗口
			this.setVisible(false);
			//以对应身份显示数据窗口
			datawin.show(identity);
		}
		
	}
	
	//重置登录界面数据
	public void reset() {
		text_usr.setText("");
		text_pwd.setText("");
		sel_student.setSelected(true);
	}
	
	//退出
	public void exit() {
		this.dispose();
	}
	
}