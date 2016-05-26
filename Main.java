import javax.swing.JFrame;


public class main {

	public static void main(String[] args) {
		LFileModel LFile_model = new LFileModel();
		RFileModel RFile_model = new RFileModel();

		NotePad gui = new NotePad(LFile_model, RFile_model);
		Load_Left_Controller L_controller = new Load_Left_Controller(LFile_model);
		Load_Right_Controller R_controller = new Load_Right_Controller(RFile_model);
		
		// swing에만 있는 X버튼 클릭시 종료
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// 프레임 크기 및 보이기 설정	
		gui.setSize(1000, 700);
		gui.setLocation(160, 25);
		gui.setVisible(true);


	}
}
