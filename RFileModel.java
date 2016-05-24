import java.util.ArrayList;


public class RFileModel {
	private String dir, file_name;
	private int length;
	private ArrayList<String> info;
	
	RFileModel(){
		info = new ArrayList<String>();
	}
	
	/* set method */
	public void setLength(int length){
		this.length = length;
	}
	
	public void setName(String file_name){
		this.file_name = file_name;
	}
	
	public void setDirectory(String dir){
		this.dir = dir;
	}
	
	public void setInfo(String info){
		this.info.add(info);
	}
	
	/* get method */
	public int getLength(){
		return this.length;
	}
	
	public String getName(){
		return this.file_name;
	}
	public String getDirectory(){
		return this.dir;
	}
	
	public ArrayList<String> getInfo(){
		return this.info;
	}
}
