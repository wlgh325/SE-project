import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Save {
	JTextField text;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Save save = new Save();
	}

	Save() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		Load load = new Load();	//save���� load�� ������ �ִٰ� �����ϱ� ���ؼ� load�� new ���ش�.
		load.Load_file();
		
		text = new JTextField("example",30);
		text.setEditable(true);
		
		panel.add(text);
		frame.add(panel);
		frame.setSize(500, 100);
		frame.setVisible(true);
		saveFile(load.get_dir(), load.get_filename());
	}

	public void saveFile(String dir, String file_name) {
		
		try{
			FileWriter fw = new FileWriter(dir + file_name);
			BufferedWriter bw = new BufferedWriter(fw);
			
			bw.write(text.getText());	//textfiled�� �ִ� example�� ���Ͽ� ����
			bw.newLine();
			bw.close();
		}
		catch(IOException e){
			System.out.println(e);
		}
	}

}
