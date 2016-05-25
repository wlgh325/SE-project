import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;


public class NotePad extends JFrame implements Observer{

		private LFileModel LFile_model;
		private RFileModel RFile_model;
		
		// ������ ����
		//JFrame jFrame = new JFrame("Simple WinMerge");
		
		// Swing������ �ؽ�Ʈ ����.. ��ũ�ѹٰ� �������� �ʴ´�.
		private JPanel p1;	
		
		static JTextArea textArea;
		static JTextArea textArea2;
		
		// Swing���� ��ũ�ѹٸ� �ֱ� ���� �Ʒ��� ���� ����Ѵ�.
		private JScrollPane jp;
		
		private JScrollPane jp2;

		// �޴� ����
		private JMenuBar menuBar;
		private JMenu fileMenu;
		private JMenu editMenu;
		private JMenu compareMenu;
		private JMenu mergeMenu;
		private JMenu helpMenu;
		
		public NotePad(LFileModel LFile_model, RFileModel RFile_model)
		{
			this.LFile_model = LFile_model;
			this.RFile_model = RFile_model;
			
			p1 = new JPanel();
			
			// scroll ���� textArea
			textArea = new JTextArea("", 0, 0);
			textArea2 = new JTextArea("", 0, 0);
			
			// textArea�� scroll �߰�
			jp = new JScrollPane(textArea);
			jp2 = new JScrollPane(textArea2);
			
			//Menu ��
			menuBar = new JMenuBar();
			fileMenu = new JMenu("File(F)");
			editMenu = new JMenu("Edit(E)");
			compareMenu = new JMenu("Compare(C)");
			mergeMenu = new JMenu("Merge(M)");
			helpMenu = new JMenu("Help(I)");
			//ó���� ���� �Ұ���
			textArea.setEditable(false);
			textArea2.setEditable(false);
			
			// ���� �޴� ����
			fileMenu.add(new JMenuItem("Left File Open"));
			fileMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.ALT_MASK));
			
			fileMenu.addSeparator(); //���м� �߰�
			fileMenu.add(new JMenuItem("Right File Open"));
			fileMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK));
			
			fileMenu.addSeparator(); //���м� �߰�
			fileMenu.add(new JMenuItem("Exit"));
			fileMenu.getItem(4).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
			
			// ���� �޴� ����
			editMenu.add(new JMenuItem("Start Edit"));
			editMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			
			editMenu.addSeparator(); //���м� �߰�
			editMenu.add(new JMenuItem("Left file Save"));
			editMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.ALT_MASK));

			editMenu.addSeparator(); //���м� �߰�
			editMenu.add(new JMenuItem("Right file Save"));
			editMenu.getItem(4).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK));
			// �� �޴� ����
			compareMenu.add(new JMenuItem("Start comparing"));
			
			//compareMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			//compareMenu.addSeparator(); //���м� �߰�
			//compareMenu.add(new JMenuItem("����"));
			//compareMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));

			// ���� �޴� ����
			mergeMenu.add(new JMenuItem("> (Merge to right)"));
			//mergeMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			
			mergeMenu.addSeparator(); //���м� �߰�
			mergeMenu.add(new JMenuItem("< (Merge to left)"));
			//mergeMenu.add(new JMenuItem("����"));
			//mergeMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));

			// ���� �޴� ����
			helpMenu.add(new JMenuItem("Information"));

			// �޴��� �޴��ٿ� ���
			menuBar.add(fileMenu);
			menuBar.add(editMenu);
			menuBar.add(compareMenu);
			menuBar.add(mergeMenu);
			menuBar.add(helpMenu);

			// �޴��� �߰�
			setJMenuBar(menuBar);
			
			// �޴� ����Ű ����
			fileMenu.setMnemonic('F');
			editMenu.setMnemonic('E');
			compareMenu.setMnemonic('C');
			mergeMenu.setMnemonic('M');
			helpMenu.setMnemonic('I');
			
			setLayout(new GridLayout(1,2));

			// �ؽ�Ʈ ���� �߰�
			add(jp);
			add(jp2);
			
			/* fileMenu Listener */
			//���� ���� ����
			fileMenu.getItem(0).addActionListener(new Load_Left_Controller());
			//������ ���� ����
			fileMenu.getItem(2).addActionListener(new Load_Right_Controller());  		
			//���α׷� ����
			fileMenu.getItem(4).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//�ۼ����� ������ ������ ���� �� �˸�!
					if(NotePad.textArea.getText().length() > 0)
					{
						if( JOptionPane.showConfirmDialog(NotePad.this, "�ۼ� ���� ������ ����ϰ� �����մϴ�.") == 0 )
						{
							System.exit(0);
						}
					}
					else
					{
						System.exit(0);
					}
				}
			});  
			
			
			/* Editmenu Listener */
			editMenu.getItem(0).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					textArea.setEditable(true);
					textArea2.setEditable(true);
				}
			});
			//���� ���� ����
			editMenu.getItem(2).addActionListener(new LeftFileSaveListenerClass());
			//������ ���� ����
			editMenu.getItem(4).addActionListener(new RightFileSaveListenerClass());
			
			/*
			//���� ���� �̺�Ʈ �߰�
			fileMenu.getItem(1).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//���� ���̾�α� ����
					FileDialog load = new FileDialog(NotePad.this.jFrame, "������");
					load.setVisible(true); 
					
					
					if(load.getFile() != null) //������ �������� ��쿡�� ����
					{
						FileReader fileReader = null;
						BufferedReader bufferedReader = null;
						
						NotePad.this.textArea.setText(null); //�޸��� ���� �ʱ�ȭ
						
						try
						{
							
							fileReader = new FileReader(load.getDirectory() + load.getFile());
							bufferedReader = new BufferedReader(fileReader);
							
							String string = new String(); //�ӽ� ����
							 
							do
							{
								//���پ� �б�
								string = bufferedReader.readLine();
								
								if( string != null )
									NotePad.this.textArea.append(string + "\n");
							}
							while(string != null); //���� ���̸� null�� ��ȯ�Ѵ�.
							
						}
						catch (Exception fileReadError)
						{
							System.out.println("����" + fileReadError);
						}
						finally
						{
							try
							{
								bufferedReader.close();
							}
							catch (Exception fileCloseError)
							{
								System.out.println("���� �ݱ� ����" + fileCloseError);
							}
						}
							
						NotePad.this.jFrame.setTitle(load.getFile()); //Ÿ��Ʋ�ٿ� ���ϸ� ��Ÿ����
						NotePad.this.textArea.setCaretPosition(0); //Ŀ�� ��ġ ó������!
							
					}
				}
				
			});
			*/
			
			//���� �̺�Ʈ �߰�
			
			helpMenu.getItem(0).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//���� ���
					JOptionPane.showMessageDialog(NotePad.this, "Version 0.0.4");
				}
				
			});

			
			//���� �̺�Ʈ �߰�
			
			/*
			helpMenu.getItem(0).addActionListener(new ActionListener()
			{
				JDialog jDialog = new JDialog(NotePad.this.jFrame, "�޸��� ����");
				JPanel jPanel = new JPanel(new GridLayout(2,1));
				JLabel jLabel01 = new JLabel("�ۼ��� : 2012�� 8�� 01��");
				JLabel jLabel02 = new JLabel("�̸� : ȫ�浿");
				JButton jButton = new JButton("Ȯ��");

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//�гο� �� �߰�
					jPanel.add(jLabel01);
					jPanel.add(jLabel02);
					
					// ���̾�α׿� �г� �߰�
					jDialog.add(jPanel, "Center");
					
					// ���̾�α׿� ��ư �߰�
					jDialog.add(jButton, "South");
			 
					// ���̾�α��� ũ��� ���� ����
					jDialog.setSize(300, 200);
					jDialog.setVisible(true);
					
					//X��ư ������ ���̾�α׸� ����
					jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					jButton.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e)
						{
							jDialog.dispose(); //�ݱ�
						}
						
					});
				}
				
			});
			 */
			/*
			if(NotePad.this.textArea.getText().length() > 0)
			{
				if( JOptionPane.showConfirmDialog(NotePad.this.jFrame, "�ۼ� ���� ������ ����մϴ�.") == 0 )
				{
					NotePad.this.textArea.setText(null);
					NotePad.this.jFrame.setTitle("�������"); //Ÿ��Ʋ�� �ʱ�ȭ
					NotePad.this.textArea.setCaretPosition(0); //Ŀ�� ��ġ ó������!
				}
			}
			else
			{
				NotePad.this.textArea.setText(null);
				NotePad.this.jFrame.setTitle("�������"); //Ÿ��Ʋ�� �ʱ�ȭ
				NotePad.this.textArea.setCaretPosition(0); //Ŀ�� ��ġ ó������!
			}
			*/
				
			/*
			//���� ���� �̺�Ʈ �߰�
			fileMenu.getItem(2).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//���� ���̾�α� ����
					FileDialog save = new FileDialog(NotePad.this.jFrame, "������", FileDialog.SAVE);
					save.setVisible(true); 
					
					
					if(save.getFile() != null) //������ �������� ��쿡�� ����
					{
						FileWriter fileWriter = null;
						BufferedWriter bufferedWriter = null;
						
						try
						{
							
							fileWriter = new FileWriter(save.getDirectory() + save.getFile());
							bufferedWriter = new BufferedWriter(fileWriter);
							
							bufferedWriter.write(NotePad.this.textArea.getText());
							
						}
						catch (Exception fileReadError)
						{
							System.out.println("����" + fileReadError);
						}
						finally
						{
							try
							{
								bufferedWriter.close();
							}
							catch (Exception fileCloseError)
							{
								System.out.println("���� �ݱ� ����" + fileCloseError);
							}
						}
						
						NotePad.this.jFrame.setTitle(save.getFile()); //Ÿ��Ʋ�ٿ� ���ϸ� ��Ÿ����
					}
				}
				
			});
			*/
		}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		System.out.println("hi");
	}
}

