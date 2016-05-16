import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;

public class NotePad
{
	// ������ ����
	JFrame jFrame = new JFrame("Simple WinMerge");
	
	// Swing������ �ؽ�Ʈ ����.. ��ũ�ѹٰ� �������� �ʴ´�.
	JPanel p1 = new JPanel();	
	
	JTextArea textArea = new JTextArea("", 0, 0);// ��ũ�ѹ� ����
	JTextArea textArea2 = new JTextArea("", 0,0);
	

	// Swing���� ��ũ�ѹٸ� �ֱ� ���� �Ʒ��� ���� ����Ѵ�.
	JScrollPane jp = new JScrollPane(textArea);// ��ũ�ѹ� �����
	
	JScrollPane jp2 = new JScrollPane(textArea2);

	// �޴� ����
	JMenuBar menuBar = new JMenuBar();
	JMenu fileMenu = new JMenu("File(F)");
	JMenu editMenu = new JMenu("Edit(E)");
	JMenu compareMenu = new JMenu("Compare(C)");
	JMenu mergeMenu = new JMenu("Merge(M)");	
	JMenu helpMenu = new JMenu("Help(I)");
	
	public NotePad()
	{
		//p1.add(textArea);
		//p1.add(textArea2);
		
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
		jFrame.setJMenuBar(menuBar);
		
		// �޴� ����Ű ����
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		compareMenu.setMnemonic('C');
		mergeMenu.setMnemonic('M');
		helpMenu.setMnemonic('I');
		
		jFrame.setLayout(new GridLayout(1,2));

		// �ؽ�Ʈ ���� �߰�
		//jFrame.add(jp, "Center");
		jFrame.add(jp);
		jFrame.add(jp2);

		
		// ������ ũ�� �� ���̱� ����
		jFrame.setSize(700, 700);
		jFrame.setVisible(true);

		// swing���� �ִ� X��ư Ŭ���� ����
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		//���� ���� ����
		fileMenu.getItem(0).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Load load = new Load();
				load.Load_file();
				print_Leftfile(load.info.get_dir(), load.info.get_filename());	  
 			}			
		});
		
		//������ ���� ����
		fileMenu.getItem(2).addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				Load load = new Load();
				load.Load_file();
				print_Rightfile(load.info.get_dir(), load.info.get_filename());
			}
		});
		
		/* Editmenu Listener */
		editMenu.getItem(2).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//���� ���̾�α� ����
				FileDialog save = new FileDialog(NotePad.this.jFrame, "Save mode", FileDialog.SAVE);
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
		
		editMenu.getItem(4).addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				//���� ���̾�α� ����
				FileDialog save = new FileDialog(NotePad.this.jFrame, "Save mode", FileDialog.SAVE);
				save.setVisible(true); 
				
				
				if(save.getFile() != null) //������ �������� ��쿡�� ����
				{
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;
					
					try
					{
						fileWriter = new FileWriter(save.getDirectory() + save.getFile());
						bufferedWriter = new BufferedWriter(fileWriter);
						
						bufferedWriter.write(NotePad.this.textArea2.getText());	
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
		fileMenu.getItem(4).addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//�ۼ����� ������ ������ ���� �� �˸�!
				if(NotePad.this.textArea.getText().length() > 0)
				{
					if( JOptionPane.showConfirmDialog(NotePad.this.jFrame, "�ۼ� ���� ������ ����ϰ� �����մϴ�.") == 0 )
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
		
		//���� �̺�Ʈ �߰�
		helpMenu.getItem(0).addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				//���� ���
				JOptionPane.showMessageDialog(NotePad.this.jFrame, "Version 0.0.2");
			}
			
		});

		//���� �̺�Ʈ �߰�
		helpMenu.getItem(1).addActionListener(new ActionListener()
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

	}
	
	public void print_Leftfile(String dir, String file_name){
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		Scanner input = null; // input ����
		File file = new File(dir + file_name);
		
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
		}
		while(input.hasNext()){
			temp1 = input.nextLine();
			NotePad.this.textArea.append(temp1);
			NotePad.this.textArea.append("\n");
		}
	}

	public void print_Rightfile(String dir, String file_name){
		String temp1; // Schedult.txt���� ���� �� �о �ӽ÷� �����ϱ�
		Scanner input = null; // input ����
		File file = new File(dir + file_name);
		
		try {
			input = new Scanner(file);
		} catch (IOException e) {
			// ���� �߻��� ��¹����� �˸���, �� �̻��� ���α׷� ������ �����ϵ��� Java ���� �߰�
			System.out.println("Unknown File");
		}
		while(input.hasNext()){
			temp1 = input.nextLine();
			NotePad.this.textArea2.append(temp1);
			NotePad.this.textArea2.append("\n");
		}
	}

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