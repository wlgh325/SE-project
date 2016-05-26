import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

class MyFilenameFilter implements FilenameFilter{
	@Override
    public boolean accept(File dir, String name) 
    { 
        StringTokenizer st = new StringTokenizer(name,"."); 
         
        String token = ""; 
         
        while (st.hasMoreTokens()) 
            token = st.nextToken(); 
             
        if (token.equals("java") || token.equals("txt") || token.equals("cpp") || token.equals("py") || token.equals("c")) 
            return true; 
        else
            return false; 
    } 
}

public class Load_Left_Controller extends JFrame implements ActionListener {
	LFileModel model;
	
	//constructor
	Load_Left_Controller(LFileModel model){
		this.model = model;
	}
	
	public void LFileLoad(){ //open할 파일의 위치와 파일 명 확인
			// 파일 저장 창 생성
			FileDialog dialog = new FileDialog(this, "Load mode", FileDialog.LOAD);
			MyFilenameFilter filter = new MyFilenameFilter();		
			boolean check = true;
			
			while(check){
				dialog.setFilenameFilter(filter);
				dialog.setVisible(true);
				String selectedFile = dialog.getFile();
				
				if(filter.accept(new File(selectedFile), selectedFile))
				{
					model.setDirectory( dialog.getDirectory() );
					model.setName( dialog.getFile());
					check = false;
				}
				else
					error_print();
			}

	}
	
	public void error_print(){	//error 메시지 창 출력
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Please choose a java file", "Error", JOptionPane.INFORMATION_MESSAGE);
	}

	public void actionPerformed(ActionEvent e){
		String temp1; // Schedult.txt파일 한줄 씩 읽어서 임시로 저장하기
		Scanner input = null; // input 선언
		LFileLoad();	//파일 읽기
		File file = new File(model.getDirectory() + model.getName());
		
		try {
			input = new Scanner(file);
		} catch (IOException ie) {
			// 예외 발생을 출력문으로 알리고, 더 이상의 프로그램 진행을 포기하도록 Java 문장 추가
			System.out.println("Unknown File");
		}
		model.setInfo(input);
	}
	
}

