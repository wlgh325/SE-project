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
		// ���� ���̾�α� ����
		frame = new JFrame();
				FileDialog save = new FileDialog(this.frame, "Save mode",
						FileDialog.SAVE);
				save.setVisible(true);

				if (save.getFile() != null) // ������ �������� ��쿡�� ����
				{
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;

					try {
						fileWriter = new FileWriter(save.getDirectory()
								+ save.getFile());
						bufferedWriter = new BufferedWriter(fileWriter);

						bufferedWriter.write(NotePad.textArea.getText());
					} catch (Exception fileReadError) {
						System.out.println("����" + fileReadError);
					} finally {
						try {
							bufferedWriter.close();
						} catch (Exception fileCloseError) {
							System.out.println("���� �ݱ� ����" + fileCloseError);
						}
					}

					this.frame.setTitle(save.getFile()); // Ÿ��Ʋ�ٿ� ���ϸ� ��Ÿ����
				}
			}

	}

class RightFileSaveListenerClass implements ActionListener{
	JFrame frame;
	public void actionPerformed(ActionEvent e){
		// ���� ���̾�α� ����
		frame = new JFrame();
				FileDialog save = new FileDialog(this.frame, "Save mode",
						FileDialog.SAVE);
				save.setVisible(true);

				if (save.getFile() != null) // ������ �������� ��쿡�� ����
				{
					FileWriter fileWriter = null;
					BufferedWriter bufferedWriter = null;

					try {
						fileWriter = new FileWriter(save.getDirectory()
								+ save.getFile());
						bufferedWriter = new BufferedWriter(fileWriter);

						bufferedWriter.write(NotePad.textArea2.getText());
					} catch (Exception fileReadError) {
						System.out.println("����" + fileReadError);
					} finally {
						try {
							bufferedWriter.close();
						} catch (Exception fileCloseError) {
							System.out.println("���� �ݱ� ����" + fileCloseError);
						}
					}

					this.frame.setTitle(save.getFile()); // Ÿ��Ʋ�ٿ� ���ϸ� ��Ÿ����
				}
			}

	}
