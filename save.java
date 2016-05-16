import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

class LeftFileSaveListenerClass implements ActionListener{
	JFrame frame;
	public void actionPerformed(ActionEvent e){
		// 저장 다이얼로그 띄우기
		frame = new JFrame();
				FileDialog save = new FileDialog(this.frame, "Save mode",
						FileDialog.SAVE);
				save.setVisible(true);

				if (save.getFile() != null) // 파일을 선택했을 경우에만 저장
				{
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;

					try {
						fileWriter = new FileWriter(save.getDirectory()
								+ save.getFile());
						bufferedWriter = new BufferedWriter(fileWriter);

						bufferedWriter.write(NotePad.textArea.getText());
					} catch (Exception fileReadError) {
						System.out.println("오류" + fileReadError);
					} finally {
						try {
							bufferedWriter.close();
						} catch (Exception fileCloseError) {
							System.out.println("파일 닫기 오류" + fileCloseError);
						}
					}

					this.frame.setTitle(save.getFile()); // 타이틀바에 파일명 나타내기
				}
			}

	}

class RightFileSaveListenerClass implements ActionListener{
	JFrame frame;
	public void actionPerformed(ActionEvent e){
		// 저장 다이얼로그 띄우기
		frame = new JFrame();
				FileDialog save = new FileDialog(this.frame, "Save mode",
						FileDialog.SAVE);
				save.setVisible(true);

				if (save.getFile() != null) // 파일을 선택했을 경우에만 저장
				{
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;

					try {
						fileWriter = new FileWriter(save.getDirectory()
								+ save.getFile());
						bufferedWriter = new BufferedWriter(fileWriter);

						bufferedWriter.write(NotePad.textArea2.getText());
					} catch (Exception fileReadError) {
						System.out.println("오류" + fileReadError);
					} finally {
						try {
							bufferedWriter.close();
						} catch (Exception fileCloseError) {
							System.out.println("파일 닫기 오류" + fileCloseError);
						}
					}

					this.frame.setTitle(save.getFile()); // 타이틀바에 파일명 나타내기
				}
			}

	}
