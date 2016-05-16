import java.io.IOException;


public class Main {

	public static void main(String[] args) throws IOException {
		
		GetFile get = new GetFile();
		
		for(int i=0 ; i<get.get_C_file1().length;i++){
			System.out.println(get.get_C_file1()[i]);
		}
		//ComputeLCS LCS = new ComputeLCS();
	
		}

}
