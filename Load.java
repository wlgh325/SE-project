import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Load extends JFrame {
	File_info info = new File_info();
	
	public Load() {	//open�� ������ ��ġ�� ���� �� Ȯ��
		// ���� ���� â ����
		FileDialog dialog = new FileDialog(this, "Load mode", FileDialog.LOAD);
		MyFilenameFilter filter = new MyFilenameFilter();
		boolean check = true;
		
		while(check){
			dialog.setFilenameFilter(filter);
			dialog.setVisible(true);
			String selectedFile = dialog.getFile();
			
			if(filter.accept(new File(selectedFile), selectedFile))
			{
				info.set_dir(dialog.getDirectory());
				info.set_filename(dialog.getFile());
				check = false;
			}
			else
				error_print();
		}
	}
	
	public void error_print(){	//error �޽��� â ���
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, "Please choose a java file", "Error", JOptionPane.INFORMATION_MESSAGE);
	}
	
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

}

class LeftFileLoadListenerClass implements ActionListener{
	public void actionPerformed(ActionEvent e){
		Load load = new Load();
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		Scanner input = null; // input ����
		File file = new File(load.info.get_dir() + load.info.get_filename());
		
		try {
			input = new Scanner(file);
		} catch (IOException ie) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
		}
		while(input.hasNext()){
			temp1 = input.nextLine();
			NotePad.textArea.append(temp1);
			NotePad.textArea.append("\n");
		}
	}
}

class RightFileLoadListenerClass implements ActionListener{
	public void actionPerformed(ActionEvent e){
		Load load = new Load();
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		Scanner input = null; // input ����
		File file = new File(load.info.get_dir() + load.info.get_filename());
		
		try {
			input = new Scanner(file);
		} catch (IOException ie) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
		}
		while(input.hasNext()){
			temp1 = input.nextLine();
			NotePad.textArea2.append(temp1);
			NotePad.textArea2.append("\n");
		}
	}
}