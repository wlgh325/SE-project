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
		
		// 프레임 선언
		//JFrame jFrame = new JFrame("Simple WinMerge");
		
		// Swing에서의 텍스트 영역.. 스크롤바가 존재하지 않는다.
		private JPanel p1;	
		
		static JTextArea textArea;
		static JTextArea textArea2;
		
		// Swing에서 스크롤바를 넣기 위해 아래와 같이 사용한다.
		private JScrollPane jp;
		
		private JScrollPane jp2;

		// 메뉴 선언
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
			
			// scroll 없는 textArea
			textArea = new JTextArea("", 0, 0);
			textArea2 = new JTextArea("", 0, 0);
			
			// textArea에 scroll 추가
			jp = new JScrollPane(textArea);
			jp2 = new JScrollPane(textArea2);
			
			//Menu 생
			menuBar = new JMenuBar();
			fileMenu = new JMenu("File(F)");
			editMenu = new JMenu("Edit(E)");
			compareMenu = new JMenu("Compare(C)");
			mergeMenu = new JMenu("Merge(M)");
			helpMenu = new JMenu("Help(I)");
			//처음에 수정 불가능
			textArea.setEditable(false);
			textArea2.setEditable(false);
			
			// 파일 메뉴 생성
			fileMenu.add(new JMenuItem("Left File Open"));
			fileMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.ALT_MASK));
			
			fileMenu.addSeparator(); //구분선 추가
			fileMenu.add(new JMenuItem("Right File Open"));
			fileMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK));
			
			fileMenu.addSeparator(); //구분선 추가
			fileMenu.add(new JMenuItem("Exit"));
			fileMenu.getItem(4).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));
			
			// 편집 메뉴 생성
			editMenu.add(new JMenuItem("Start Edit"));
			editMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			
			editMenu.addSeparator(); //구분선 추가
			editMenu.add(new JMenuItem("Left file Save"));
			editMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('L', InputEvent.ALT_MASK));

			editMenu.addSeparator(); //구분선 추가
			editMenu.add(new JMenuItem("Right file Save"));
			editMenu.getItem(4).setAccelerator(KeyStroke.getKeyStroke('R', InputEvent.ALT_MASK));
			// 비교 메뉴 생성
			compareMenu.add(new JMenuItem("Start comparing"));
			
			//compareMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			//compareMenu.addSeparator(); //구분선 추가
			//compareMenu.add(new JMenuItem("종료"));
			//compareMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));

			// 머지 메뉴 생성
			mergeMenu.add(new JMenuItem("> (Merge to right)"));
			//mergeMenu.getItem(0).setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.ALT_MASK));
			
			mergeMenu.addSeparator(); //구분선 추가
			mergeMenu.add(new JMenuItem("< (Merge to left)"));
			//mergeMenu.add(new JMenuItem("종료"));
			//mergeMenu.getItem(2).setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.ALT_MASK));

			// 도움 메뉴 생성
			helpMenu.add(new JMenuItem("Information"));

			// 메뉴를 메뉴바에 등록
			menuBar.add(fileMenu);
			menuBar.add(editMenu);
			menuBar.add(compareMenu);
			menuBar.add(mergeMenu);
			menuBar.add(helpMenu);

			// 메뉴바 추가
			setJMenuBar(menuBar);
			
			// 메뉴 단축키 지정
			fileMenu.setMnemonic('F');
			editMenu.setMnemonic('E');
			compareMenu.setMnemonic('C');
			mergeMenu.setMnemonic('M');
			helpMenu.setMnemonic('I');
			
			setLayout(new GridLayout(1,2));

			// 텍스트 영역 추가
			add(jp);
			add(jp2);
			
			/* fileMenu Listener */
			//왼쪽 파일 열기
			fileMenu.getItem(0).addActionListener(new Load_Left_Controller());
			//오른쪽 파일 열기
			fileMenu.getItem(2).addActionListener(new Load_Right_Controller());  		
			//프로그램 종료
			fileMenu.getItem(4).addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					//작성중인 내용이 있으면 종료 전 알림!
					if(NotePad.textArea.getText().length() > 0)
					{
						if( JOptionPane.showConfirmDialog(NotePad.this, "작성 중인 내용을 취소하고 종료합니다.") == 0 )
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
			//왼쪽 파일 저장
			editMenu.getItem(2).addActionListener(new LeftFileSaveListenerClass());
			//오른쪽 파일 저장
			editMenu.getItem(4).addActionListener(new RightFileSaveListenerClass());
			
			/*
			//파일 열기 이벤트 추가
			fileMenu.getItem(1).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//열기 다이얼로그 띄우기
					FileDialog load = new FileDialog(NotePad.this.jFrame, "열기모드");
					load.setVisible(true); 
					
					
					if(load.getFile() != null) //파일을 선택했을 경우에만 열기
					{
						FileReader fileReader = null;
						BufferedReader bufferedReader = null;
						
						NotePad.this.textArea.setText(null); //메모장 내용 초기화
						
						try
						{
							
							fileReader = new FileReader(load.getDirectory() + load.getFile());
							bufferedReader = new BufferedReader(fileReader);
							
							String string = new String(); //임시 변수
							 
							do
							{
								//한줄씩 읽기
								string = bufferedReader.readLine();
								
								if( string != null )
									NotePad.this.textArea.append(string + "\n");
							}
							while(string != null); //파일 끝이면 null을 반환한다.
							
						}
						catch (Exception fileReadError)
						{
							System.out.println("오류" + fileReadError);
						}
						finally
						{
							try
							{
								bufferedReader.close();
							}
							catch (Exception fileCloseError)
							{
								System.out.println("파일 닫기 오류" + fileCloseError);
							}
						}
							
						NotePad.this.jFrame.setTitle(load.getFile()); //타이틀바에 파일명 나타내기
						NotePad.this.textArea.setCaretPosition(0); //커서 위치 처음으로!
							
					}
				}
				
			});
			*/
			
			//버전 이벤트 추가
			
			helpMenu.getItem(0).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//버전 출력
					JOptionPane.showMessageDialog(NotePad.this, "Version 0.0.4");
				}
				
			});

			
			//정보 이벤트 추가
			
			/*
			helpMenu.getItem(0).addActionListener(new ActionListener()
			{
				JDialog jDialog = new JDialog(NotePad.this.jFrame, "메모장 정보");
				JPanel jPanel = new JPanel(new GridLayout(2,1));
				JLabel jLabel01 = new JLabel("작성일 : 2012년 8월 01일");
				JLabel jLabel02 = new JLabel("이름 : 홍길동");
				JButton jButton = new JButton("확인");

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//패널에 라벨 추가
					jPanel.add(jLabel01);
					jPanel.add(jLabel02);
					
					// 다이얼로그에 패널 추가
					jDialog.add(jPanel, "Center");
					
					// 다이얼로그에 버튼 추가
					jDialog.add(jButton, "South");
			 
					// 다이얼로그의 크기와 보기 설정
					jDialog.setSize(300, 200);
					jDialog.setVisible(true);
					
					//X버튼 누르면 다이얼로그만 종료
					jDialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					
					jButton.addActionListener(new ActionListener()
					{

						@Override
						public void actionPerformed(ActionEvent e)
						{
							jDialog.dispose(); //닫기
						}
						
					});
				}
				
			});
			 */
			/*
			if(NotePad.this.textArea.getText().length() > 0)
			{
				if( JOptionPane.showConfirmDialog(NotePad.this.jFrame, "작성 중인 내용을 취소합니다.") == 0 )
				{
					NotePad.this.textArea.setText(null);
					NotePad.this.jFrame.setTitle("제목없음"); //타이틀바 초기화
					NotePad.this.textArea.setCaretPosition(0); //커서 위치 처음으로!
				}
			}
			else
			{
				NotePad.this.textArea.setText(null);
				NotePad.this.jFrame.setTitle("제목없음"); //타이틀바 초기화
				NotePad.this.textArea.setCaretPosition(0); //커서 위치 처음으로!
			}
			*/
				
			/*
			//파일 저장 이벤트 추가
			fileMenu.getItem(2).addActionListener(new ActionListener()
			{

				@Override
				public void actionPerformed(ActionEvent e)
				{
					//저장 다이얼로그 띄우기
					FileDialog save = new FileDialog(NotePad.this.jFrame, "저장모드", FileDialog.SAVE);
					save.setVisible(true); 
					
					
					if(save.getFile() != null) //파일을 선택했을 경우에만 저장
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
							System.out.println("오류" + fileReadError);
						}
						finally
						{
							try
							{
								bufferedWriter.close();
							}
							catch (Exception fileCloseError)
							{
								System.out.println("파일 닫기 오류" + fileCloseError);
							}
						}
						
						NotePad.this.jFrame.setTitle(save.getFile()); //타이틀바에 파일명 나타내기
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

