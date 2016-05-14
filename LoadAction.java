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
	final static int MAX_COURSE = 10;	//최대 가능 강의수 10개로 설정
	String[] colname = { "과목명", "학점", "요일", "시작교시", "시간" }; // table column의 이름
	private Course[] load_course = new Course[MAX_COURSE];
	int num = 0; // 강의 갯수 새기
	static int check_load = 0;	//load 버튼 눌렸는지 여부 check하는 변수
	/* Constructor */
	LoadAction()
	{
		this.showSaveList();
	}
	
	public void makeTable()
	{
		DefaultTableModel model = new DefaultTableModel(this.colname, 0);	//table에 표시할 값
		for(int i=0; i<num; i++)	// list로부터 받은 course정보를 model에 저장해서 table에 출력
		{
			model.addRow(new Object[]{this.load_course[i].getCourse_name(), this.load_course[i].getCredit(), this.load_course[i].getday(), this.load_course[i].getStart_time(), this.load_course[i].getClass_time() });	
		}
		table2 = new JTable(model);	//table 생성
		scroll = new JScrollPane(table2);	//table에 스크롤 생기게 하기
		scroll.setSize(430,200);
		CourseLayout.p4.add(scroll);
		check_load++;
	}
	
	public void showSaveList()
	{
		String temp1; // Schedult.txt파일 한줄 씩 읽어서 임시로 저장하기
		String[] buf1; // temp에 저장된 것을 buf에 저장
		Scanner input = null; // input 선언
		
		File file = new File("save.txt"); // 텍스트파일 열기
		
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			// 예외 발생을 출력문으로 알리고, 더 이상의 프로그램 진행을 포기하도록 Java 문장 추가
			System.out.println("Unknown File");
			System.exit(0);
		}
		while (input.hasNext()) // hasNext()를 이용하여 EOF찾기
		{
			load_course[this.num] = new Course(null, 0, null, 0, 0);
			temp1 = input.nextLine(); // 한 줄 인식
			buf1 = temp1.split(" "); // ":"구분자로 나누어 buf배열에 저장하기
			this.load_course[this.num].setCourse_name(buf1[0]);
			this.load_course[this.num].setCredit(Integer.parseInt(buf1[1])); // string을 숫자로 변환후 set
			this.load_course[this.num].setday(buf1[2]);
			this.load_course[this.num].setStart_time(Integer.parseInt(buf1[3])); // string을 숫자로 변환한 후 set
			this.load_course[this.num].setClass_time(Integer.parseInt(buf1[4])); // string을 숫자로 변환한 후 set
			num++;
		}//end of while

	}
}
