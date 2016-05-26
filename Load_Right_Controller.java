import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Load_Right_Controller extends JFrame implements ActionListener{
	RFileModel model;
	
	//constructor
	Load_Right_Controller(RFileModel model){
		this.model = model;
	}
	public void RFileLoad(){ //open�� ������ ��ġ�� ���� �� Ȯ��
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
		RFileLoad();	//���� �б�
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

