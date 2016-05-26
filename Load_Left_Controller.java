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
	
	public void LFileLoad(){ //open�� ������ ��ġ�� ���� �� Ȯ��
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
					model.setDirectory( dialog.getDirectory() );
					model.setName( dialog.getFile());
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

	public void actionPerformed(ActionEvent e){
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		Scanner input = null; // input ����
		LFileLoad();	//���� �б�
		File file = new File(model.getDirectory() + model.getName());
		
		try {
			input = new Scanner(file);
		} catch (IOException ie) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
		}
		model.setInfo(input);
	}
	
}

