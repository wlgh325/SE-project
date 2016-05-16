import java.io.*;

public class GetFile {
	
	private char[] C_file1,C_file2;
	//생성자
	public GetFile() throws IOException{
		FileRead();
	}
	
	public void FileRead() throws IOException{
		
		File file1 = new File("text1.txt");
		File file2 = new File("text2.txt");
		
		
		System.out.println((int)file1.length());
		char[] C1 = new char[(int)file1.length()];
		char[] C2 = new char[(int)file2.length()];
	
		//file1읽고 Char배열에 저장
		BufferedReader br1 = new BufferedReader(new FileReader(file1));
		br1.read(C1);
		
		set_C_file1(C1);//C_file1 setting
		br1.close();
		
		//file2읽고 Char배열에저장
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		br2.read(C2);
		
		set_C_file2(C2);//C_file2 setting
		br2.close();
	}
	
	public void set_C_file1( char[] C_file1){
		this.C_file1 = C_file1;
	}
	public void set_C_file2( char[] C_file2){
		this.C_file2 = C_file2;
	}
	
	public char[] get_C_file1(){
		return this.C_file1;
	}
	public char[] get_C_file2(){
		return this.C_file2;
	}
}

