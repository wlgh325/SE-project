import java.io.IOException;


public class ComputeLCS {

	private int LCS_length ;
	private int[][] FilledOpt;//�ϼ��� ǥ
	
	public char[] file1,file2;
	public int M,N;//file1,2 ������ ����
	public int[][] opt;//�ʱ� ǥ
	
	//������
	public ComputeLCS() throws IOException{
		setString();
		LCSLength();
		PrintDiff(get_FilledOpt(),file1,file2,M,N);
	}
	
	//Getclass �ν��Ͻ��� �޾ƿͼ� ǥ setting
	public void setString() throws IOException{
		
		GetFile file= new GetFile();

		file1 = file.get_C_file1();
		file2 = file.get_C_file2();
		
		M=file1.length;
		N=file2.length;
		opt = new int[M+1][N+1];

	}
	
	//LCS���̱��ϱ�
	public void LCSLength(){

		//ǥ ����
		for(int i = 0;i<=M;i++)
			opt[i][0]=0;
		for(int j = 0;j<=N;j++)
			opt[j][0]=0;
		//ǥ LCS�˰��� ���� ä��������
		for(int i = 1;i<=M;i++){
			for(int j =1;j<=N;j++){
				if(file1[i-1]==file2[j-1]) //���ڰ� ���� �κ��� ���� ���
					opt[i][j] = opt[i-1][j-1] +1;
				else//���ڰ� �ٸ� �κ��� ���� ���
					opt[i][j] = Math.max(opt[i][j-1], opt[i-1][j]);
			}
		}
		//LCS�� ����, ä���� ǥ�� ����.
		set_LCS_Len(opt[M][N]);
		set_FilledOpt(opt);
		System.out.println(get_LCS_Len());
	}
	
	//LCS���
	public void LCS(){
	
	}
	
	//���� �ٸ��κ� ���
	public void PrintDiff(int[][] Fillopt, char[] file1, char[] file2,int m,int n){
		
		//���ٸ�
		if(m>0 && n>0 && file1[m] == file2[n]){
			PrintDiff(Fillopt,file1,file2,m-1,n-1);
			System.out.println(" "+ file1[m]);
		}
		//file
		else if(n>0 && (m==0||(Fillopt[m][n-1]>=Fillopt[m-1][n]))){
			PrintDiff(Fillopt,file1,file2,m,n-1);
			System.out.println("+"+ file2[n]);
		}
		else if(m>0 && (n==0 ||(Fillopt[m][n-1]<Fillopt[m-1][n]))){
			PrintDiff(Fillopt,file1,file2,m-1,n);
			System.out.println("-"+file1[m]);
		}
		else{
			System.out.println("");
		}
			

	}
	public void set_LCS_Len(int length){
		this.LCS_length = length;
	}
	public void set_FilledOpt(int[][]FilledOpti){
		this.FilledOpt = FilledOpti ;
	}
	public int get_LCS_Len(){
		return this.LCS_length;
	}
	public int[][] get_FilledOpt(){
		return this.FilledOpt;
	}
}

