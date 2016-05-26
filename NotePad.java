import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class NotePad extends JFrame implements Observer{

		
		//private Observable observable;
		
		private JPanel p1;	
		private JPanel p2;
		private JPanel p3;
		private JPanel p4;
		
		private JButton compare;
		private JButton toLeft_merge;
		private JButton toRight_merge;
		private JButton help;
		
		// Main window
		public NotePad(Observable LFile_model, Observable RFile_model)
		{	
			p1 = new JPanel();
			p2 = new JPanel();
			p3 = new JPanel();
			p4 = new JPanel();
			
			compare = new JButton("Compare");
			toLeft_merge = new JButton("<");
			toRight_merge = new JButton(">");
			help = new JButton("Help");
			
			// textArea에 scroll 추가

			p1.setLayout(new GridLayout(1,4));
			p1.add(compare);
			p1.add(toLeft_merge);
			p1.add(toRight_merge);
			p1.add(help);
			
			add(p1, BorderLayout.NORTH);
			LTextView ltextView = new LTextView(LFile_model);
			RTextView rtextView = new RTextView(RFile_model);
			
			p2 = ltextView;	//왼쪽 panel
			p3 = rtextView;	//오른쪽 panel
			p4.setLayout(new GridLayout(1,2));	//왼쪽과 오른쪽 panel합치기
			p4.add(p2);
			p4.add(p3);
			
			add(p4, BorderLayout.CENTER);
			
			help.addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//버전 출력
					JOptionPane.showMessageDialog(NotePad.this, "Version 0.0.4");
				}
				
			});
		}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof LFileModel || o instanceof RFileModel){
		}
	}
}

