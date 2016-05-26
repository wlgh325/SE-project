import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class LTextView extends JPanel implements Observer{
	
	private JTextArea Left_textArea;
	private JScrollPane Left_scroll;
	private JButton LeftFile_open;
	private JButton LeftFile_save;
	private JButton edit;
	private JPanel panel;
	
	LTextView(Observable observable){
		Left_textArea = new JTextArea("", 0, 0);	//scroll 없는 textarea
		Left_textArea.setEditable(false);	//수정 불가
		Left_scroll = new JScrollPane(Left_textArea);
		LeftFile_open = new JButton("LeftFile Load");
		LeftFile_save = new JButton("LeftFile Save");
		panel = new JPanel();
		edit = new JButton("Edit");
	
		observable.addObserver(this);
		
		setLayout(new BorderLayout());
		panel.add(LeftFile_open);
		panel.add(LeftFile_save);
		panel.add(edit);
		
		add(panel, BorderLayout.NORTH);
		add(Left_scroll, BorderLayout.CENTER);
		
		/* action listener */
		LeftFile_open.addActionListener(new Load_Left_Controller( (LFileModel)observable) );
		edit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Left_textArea.setEditable(true);
			}
		});
		
		
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Scanner input;
		String temp;
		input = ((LFileModel)o).getInfo();
		
		while(input.hasNext()){
			temp = input.nextLine();
			Left_textArea.append(temp);
			Left_textArea.append("\n");
		}
	}
}
