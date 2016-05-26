import javax.swing.JFrame;


public class main {

	public static void main(String[] args) {
		LFileModel LFile_model = new LFileModel();
		RFileModel RFile_model = new RFileModel();

		NotePad gui = new NotePad(LFile_model, RFile_model);
		Load_Left_Controller L_controller = new Load_Left_Controller(LFile_model);
		Load_Right_Controller R_controller = new Load_Right_Controller(RFile_model);
		
		// swing���� �ִ� X��ư Ŭ���� ����
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ������ ũ�� �� ���̱� ����	
		gui.setSize(1000, 700);
		gui.setLocation(160, 25);
		gui.setVisible(true);


	}
}
