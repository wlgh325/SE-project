import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class RTextView extends JPanel implements Observer{

	private JTextArea Right_textArea;
	private JScrollPane Right_scroll;
	private JButton RightFile_open;
	private JButton RightFile_save;
	private JButton edit;
	private JPanel panel;

	RTextView(Observable observable){
		Right_textArea = new JTextArea("", 0, 0);	//scroll없는 textarea
		Right_textArea.setEditable(false);	//수정불가
		Right_scroll = new JScrollPane(Right_textArea);
		RightFile_open = new JButton("RightFile Load");
		RightFile_save = new JButton("RightFile Save");
		panel = new JPanel();
		edit = new JButton("Edit");
		
		observable.addObserver(this);
		
		setLayout(new BorderLayout());
		panel.add(RightFile_open);
		panel.add(RightFile_save);
		panel.add(edit);
		
		add(panel, BorderLayout.NORTH);
		add(Right_scroll, BorderLayout.CENTER);
		
		/* action listener */
		RightFile_open.addActionListener(new Load_Right_Controller( (RFileModel)observable ) );
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Right_textArea.setEditable(true);
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		Scanner input;
		String temp;
		input = ((RFileModel)o).getInfo();
		
		while(input.hasNext()){
			temp = input.nextLine();
			Right_textArea.append(temp);
			Right_textArea.append("\n");
		}
	}
}
