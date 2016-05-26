import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;


public class LFileModel extends Observable{
	
	private String dir, file_name;
	private int length;
	private ArrayList<String> info;
	private Scanner input;
	
	LFileModel(){
		//info = new ArrayList<String>();
		input = null;
	}
	
	/* set method */
	public void setLength(int length){
		this.length = length;
		changedValue();
	}
	
	public void setName(String file_name){
		this.file_name = file_name;
	}
	
	public void setDirectory(String dir){
		this.dir = dir;
	}
	
	public void setInfo(Scanner input){
		this.input = input;
		changedValue();
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
	
	public Scanner getInfo(){
		return this.input;
	}
	
	public void changedValue() {
        setChanged();
        notifyObservers();
      
    }
}
