import java.awt.FileDialog;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

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
	File_info info = new File_info();
	Load(){		//constructor
		
	}
	
	public void Load_file() {	//open�� ������ ��ġ�� ���� �� Ȯ��
		// ���� ���� â ����
		FileDialog dialog = new FileDialog(this, "Load", FileDialog.LOAD);
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
	
}
