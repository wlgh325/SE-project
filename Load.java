import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

class MyFilenameFilter implements FilenameFilter 
{ 
	@Override
    public boolean accept(File dir, String name) 
    { 
        StringTokenizer st = new StringTokenizer(name,"."); 
         
        String token = ""; 
         
        while (st.hasMoreTokens()) 
            token = st.nextToken(); 
             
        if (token.equals("java")) 
            return true; 
        else
            return false; 
    } 
} 

public class Load extends JFrame {
	private String dir, file_name;
	
	public static void main(String[] args) {
		Load load = new Load();
		load.Load_file();
		load.print(load.get_dir(), load.get_filename());
	}
	
	public void Load_file() {
		// 파일 저장 창 생성
		FileDialog dialog = new FileDialog(this, "Load", FileDialog.LOAD);
		MyFilenameFilter filter = new MyFilenameFilter();
		boolean check = true;
		
		while(check){
			dialog.setFilenameFilter(filter);
			dialog.setVisible(true);
			String selectedFile = dialog.getFile();
			
			if(filter.accept(new File(selectedFile), selectedFile))
			{
				set_dir(dialog.getDirectory());
				set_filename(dialog.getFile());
				check = false;
			}
			else
				error_print();
		}
	}

	public void print(String dir, String file_name) {
		String temp1; // Schedult.txt파일 한줄 씩 읽어서 임시로 저장하기
		Scanner input = null; // input 선언

		File file = new File(dir + file_name); // 텍스트파일 열기
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			// 예외 발생을 출력문으로 알리고, 더 이상의 프로그램 진행을 포기하도록 Java 문장 추가
			System.out.println("Unknown File");
			System.exit(0);
		}
		while(input.hasNext())
		{
			temp1 = input.nextLine();
			System.out.println(temp1);
		}
	}
	public void error_print(){
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Please choose a java file", "Error", JOptionPane.INFORMATION_MESSAGE);
	}
	/* set method */
	public void set_dir(String dir) {
		this.dir = dir;
	}

	public void set_filename(String file_name) {
		this.file_name = file_name;
	}

	/* get method */
	public String get_dir() {
		return this.dir;
	}

	public String get_filename() {
		return this.file_name;
	}
}
