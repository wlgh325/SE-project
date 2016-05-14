import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class LoadAction extends JPanel{
	JScrollPane scroll;
	JTable table2;
	final static int MAX_COURSE = 10;	//�ִ� ���� ���Ǽ� 10���� ����
	String[] colname = { "�����", "����", "����", "���۱���", "�ð�" }; // table column�� �̸�
	private Course[] load_course = new Course[MAX_COURSE];
	int num = 0; // ���� ���� ����
	static int check_load = 0;	//load ��ư ���ȴ��� ���� check�ϴ� ����
	/* Constructor */
	LoadAction()
	{
		this.showSaveList();
	}
	
	public void makeTable()
	{
		DefaultTableModel model = new DefaultTableModel(this.colname, 0);	//table�� ǥ���� ��
		for(int i=0; i<num; i++)	// list�κ��� ���� course������ model�� �����ؼ� table�� ���
		{
			model.addRow(new Object[]{this.load_course[i].getCourse_name(), this.load_course[i].getCredit(), this.load_course[i].getday(), this.load_course[i].getStart_time(), this.load_course[i].getClass_time() });	
		}
		table2 = new JTable(model);	//table ����
		scroll = new JScrollPane(table2);	//table�� ��ũ�� ����� �ϱ�
		scroll.setSize(430,200);
		CourseLayout.p4.add(scroll);
		check_load++;
	}
	
	public void showSaveList()
	{
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		String[] buf1; // temp�� ����� ���� buf�� ����
		Scanner input = null; // input ����
		
		File file = new File("save.txt"); // �ؽ�Ʈ���� ����
		
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
			System.exit(0);
		}
		while (input.hasNext()) // hasNext()�� �̿��Ͽ� EOFã��
		{
			load_course[this.num] = new Course(null, 0, null, 0, 0);
			temp1 = input.nextLine(); // �� �� �ν�
			buf1 = temp1.split(" "); // ":"�����ڷ� ������ buf�迭�� �����ϱ�
			this.load_course[this.num].setCourse_name(buf1[0]);
			this.load_course[this.num].setCredit(Integer.parseInt(buf1[1])); // string�� ���ڷ� ��ȯ�� set
			this.load_course[this.num].setday(buf1[2]);
			this.load_course[this.num].setStart_time(Integer.parseInt(buf1[3])); // string�� ���ڷ� ��ȯ�� �� set
			this.load_course[this.num].setClass_time(Integer.parseInt(buf1[4])); // string�� ���ڷ� ��ȯ�� �� set
			num++;
		}//end of while

	}
}
