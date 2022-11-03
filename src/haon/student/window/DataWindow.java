package haon.student.window;

import haon.student.listener.DataWindowActionListener;
import haon.student.tablemodel.StudentTableModel;
import haon.student.util.DimensionUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

public class DataWindow extends JFrame {

	//父窗口对象,也就是登录窗口
	LoginWindow parent;
	String identity;
	
	Container container;
	
	JPanel northPanel;	//上边主面板
	JPanel centerPanel;	//中间主面板
	JPanel southPanel;	//下边主面板
	JButton btn_add;	//增
	JButton btn_del;	//删
	JButton btn_mod;	//改
	JButton btn_find;	//查
	JButton btn_back;	//查
	JButton btn_pre;	//上一页
	JButton btn_next;	//下一页
	JTextField	text_input;
	
	JTable table;
	StudentTableModel studentTableModel;
	Vector<Vector<Object>> dataVector;	//学生数据
	JScrollPane scrollPane;

	//按钮注册的ActionCommand
	public final String COMMAND_ADD 	= "Add";
	public final String COMMAND_DEL 	= "Del";
	public final String COMMAND_MOD 	= "Mod";
	public final String COMMAND_FIND 	= "Find";
	public final String COMMAND_BACK	= "Back";
	public final String COMMAND_PRE		= "Pre";
	public final String COMMAND_NEXT	= "Next";

	//数据文件路径
	final static String FILE_PATH = "D:/Haon/student_information.txt";
	
	
	//构造方法
	public DataWindow(LoginWindow parent) {
		//this.setResizable(false);
		this.parent = parent;
		init();
	}
	
	//重载show方法
	void show(String identity) {
		this.identity = identity.equals(parent.TEACHER)?"教师":"学生";
		String ti = "学生数据管理页,您的身份是：";
		//设置标题
		this.setTitle(ti + this.identity);
		//判断身份
		if(identity.equals(parent.TEACHER)) {
			//如果是老师,增加、删除、修改可点击
			btn_add.setEnabled(true);
			btn_del.setEnabled(true);
			btn_mod.setEnabled(true);
		}else {
			//否则不可点击
			btn_add.setEnabled(false);
			btn_del.setEnabled(false);
			btn_mod.setEnabled(false);
		}
		//每次显示之前重新加载所有学生数据
		this.reloadTable(dataVector);
		//清空输入框数据,避免保留上一次的输入
		text_input.setText("");
		//可视化
		this.setVisible(true);
	}
	
	public void init() {
		
		//根据屏幕大小设置数据界面大小
		this.setBounds(DimensionUtil.getBounds());
		//设置窗体完全充满整个屏幕可见大小
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//关闭窗口结束进程
		this.setDefaultCloseOperation(LoginWindow.EXIT_ON_CLOSE);

		//设置窗口图标
		URL resource = DataWindow.class.getClassLoader().getResource("icon.png");
		Image image = new ImageIcon(resource).getImage();
		this.setIconImage(image);
		
		//获得数据页面主面板
		container = this.getContentPane();
		
		northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));	//流布局,内部组件靠左
		centerPanel = new JPanel(new BorderLayout());				//边界布局
		southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));	//南边面板,内部组件靠右
		
		//JButton
		btn_add = new JButton("增加");
		btn_add.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_add.setFocusPainted(false);		//取消焦点标志
		btn_add.setActionCommand(COMMAND_ADD);
		btn_del = new JButton("删除");
		btn_del.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_del.setFocusPainted(false);		//取消焦点标志
		btn_del.setActionCommand(COMMAND_DEL);
		btn_mod = new JButton("修改");
		btn_mod.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_mod.setFocusPainted(false);		//取消焦点标志
		btn_mod.setActionCommand(COMMAND_MOD);
		btn_find = new JButton("查询");
		btn_find.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_find.setFocusPainted(false);		//取消焦点标志
		btn_find.setActionCommand(COMMAND_FIND);
		btn_back = new JButton("返回");
		btn_back.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_back.setFocusPainted(false);		//取消焦点标志
		btn_back.setActionCommand(COMMAND_BACK);
		
		btn_pre  = new JButton("上一页");
		btn_pre.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_pre.setFocusPainted(false);	//取消焦点标志
		btn_pre.setActionCommand(COMMAND_PRE);
		btn_next = new JButton("下一页");
		btn_next.setFont(new Font("宋体", Font.PLAIN, 20));
		btn_next.setFocusPainted(false);	//取消焦点标志
		btn_next.setActionCommand(COMMAND_NEXT);
		
		//把按钮注册到事件监听器
		DataWindowActionListener listener = new DataWindowActionListener(this);
		btn_add.addActionListener(listener);
		btn_del.addActionListener(listener);
		btn_mod.addActionListener(listener);
		btn_find.addActionListener(listener);
		btn_back.addActionListener(listener);
		btn_pre.addActionListener(listener);
		btn_next.addActionListener(listener);
		
        //创建学生数据容器
        dataVector = new Vector<>();

        //从文件读取学生数据到vector中
        if(isFileExist(FILE_PATH)){
			readFile(FILE_PATH);
		}

        //全局唯一单例对象
        studentTableModel = StudentTableModel.assembleModel(dataVector);		
        // 创建一个表格，指定 表头 和 所有行数据
        table = new JTable(studentTableModel);
        

        // 设置表格内容颜色
        table.setForeground(Color.BLACK);                   // 字体颜色
        table.setFont(new Font(null, Font.PLAIN, 14));      // 字体样式
        table.setSelectionForeground(Color.DARK_GRAY);      // 选中后字体颜色
        table.setSelectionBackground(Color.LIGHT_GRAY);     // 选中后字体背景
        table.setGridColor(Color.BLACK);                     // 网格颜色
        //设置文字居中
        DefaultTableCellRenderer dc=new DefaultTableCellRenderer();
        dc.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
        table.setDefaultRenderer(Object.class, dc);


        // 设置表头
        table.getTableHeader().setFont(new Font(null, Font.BOLD, 14));  // 设置表头名称字体样式
        table.getTableHeader().setForeground(Color.RED);                // 设置表头名称字体颜色
        table.getTableHeader().setResizingAllowed(false);               // 设置不允许手动改变列宽
        table.getTableHeader().setReorderingAllowed(false);             // 设置不允许拖动重新排序各列

        // 设置行高
        table.setRowHeight(30);

        // 第一列列宽
        table.getColumnModel().getColumn(0).setPreferredWidth(10);

        // 设置滚动面板视口大小（超过该大小的行数据，需要拖动滚动条才能看到）
        table.setPreferredScrollableViewportSize(new Dimension(400, 300));

        // 把 表格 放到 滚动面板 中（表头将自动添加到滚动面板顶部）
        scrollPane = new JScrollPane(table);

        // 添加 滚动面板 到 内容面板
        centerPanel.add(scrollPane);
        container.add(centerPanel,BorderLayout.CENTER);

        //设置文本框
        text_input = new JTextField(16);
        text_input.setPreferredSize(new Dimension(0,35));
		//添加组件到北边的组件中
        northPanel.add(btn_add);
        northPanel.add(btn_del);
        northPanel.add(btn_mod);
        northPanel.add(text_input);
        northPanel.add(btn_find);
        container.add(northPanel,BorderLayout.NORTH);
        
        southPanel.add(btn_pre);
        southPanel.add(btn_next);
        southPanel.add(btn_back);
        container.add(southPanel,BorderLayout.SOUTH);
        
	
		//writeFile("D:/Haon/1.txt");
		//主窗体可视化
		//this.setVisible(true);
	}
	
	//重新加载表格
	public void reloadTable(Vector<Vector<Object>> data) {
		StudentTableModel.updateModel(data);
		table.getColumnModel().getColumn(0).setPreferredWidth(10);
	}
	
	//点击查询之后调用的方法
	public void search() {
		//获取输入框上的名字
		String name = text_input.getText();
		//System.out.println("name == " + name);
		//创建一个临时学生数据容器
		Vector<Vector<Object>> tmpVector = new Vector<>();
		//用于计数
		int lineCount = 0;
		
		if(!name.isEmpty()) {
			//创建迭代器指向原学生数据容器
			Iterator<Vector<Object>> it =  dataVector.iterator();
			//遍历原学生数据容器
        	while(it.hasNext()) {
        		Vector<Object> data = it.next();
        		//Vector<Object> tmpData = data;	//拷贝当前组件，避免影响原来的数据,这样还是指向原来的数据
        		Vector<Object> tmpData = new Vector<>();		//拷贝当前组件，避免影响原来的数据
        		tmpData = (Vector)data.clone();					//拷贝当前组件，避免影响原来的数据
        		//如果出现名字匹配
        		if(tmpData.elementAt(1).toString().contains(name)) {
        			//数量 +1
        			lineCount++;
        			System.out.println(tmpData.elementAt(1));
        			
        			//该生序号从1开始排序，用当前数量代替原来的序号
        			tmpData.setElementAt(Integer.toString(lineCount), 0);
        			//把修改后的数据加入到临时容器中
        			tmpVector.addElement(tmpData);
        		}
        	}
        	//用临时容器更新表格
        	reloadTable(tmpVector);
		}
	}
	
	//检查文件是否存在
	public boolean isFileExist(String strFile) {
		
		File file = new File(strFile);
		return file.exists();
	}
	
	//从文件中读取学生数据
	public void readFile(String strFile){
        try {
            File file = new File(strFile);
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String strLine = null;
            int lineCount = 1;
            while(null != (strLine = bufferedReader.readLine())){
                System.out.println("第[" + lineCount + "]行数据:[" + strLine + "]");
                String[] ite = strLine.split("    ");
                //System.out.println(ite.length);
                //StudentData data = new StudentData(ite[0],ite[1],ite[2],ite[3],ite[4]);
                Vector<Object> data = new Vector<>();
                data.addElement(ite[0]);
                data.addElement(ite[1]);
                data.addElement(ite[2]);
                data.addElement(ite[3]);
                data.addElement(ite[4]);
                data.addElement(ite[5]);
                data.addElement(ite[6]);
                dataVector.addElement(data);
                lineCount++;
            }
            //关闭句柄
            bufferedReader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
	
	//把list中的数据写入文件中
	public void writeFile(String strFile) {
        File file = new File(strFile);
        FileWriter fileWriter = null;
	    try {
        	fileWriter = new FileWriter(file);
        	fileWriter.write("");	//清空文件内容
        	System.out.println("有 " + dataVector.size() + " 项数据");
        	Iterator<Vector<Object>> it =  dataVector.iterator();
        	int i=1;
        	while(it.hasNext()) {
        		Vector<Object> data = it.next();
        		//System.out.println((Object)it.next());
        		System.out.println("data.size = " + data.size());
        		String s = data.elementAt(0)    + "    " 
        					+ data.elementAt(1) + "    " + data.elementAt(2) + "    "
        					+ data.elementAt(3) + "    " + data.elementAt(4) + "    "
        					+ data.elementAt(5) + "    " + data.elementAt(6) + "\n";
        		System.out.println("写入[" + i + "]" + " " + s);
        		fileWriter.write(s);
        		fileWriter.flush();
        		i++;
        	}
        	
        	//关闭写文件句柄
        	fileWriter.close();

	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
	}

	//返回登录界面
	public void back() {
		parent.setVisible(true);;
		this.setVisible(false);;
	}
	
}

