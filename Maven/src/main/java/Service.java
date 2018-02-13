import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class Service {

	public String store(Bean j){
		System.out.println(j.getUname());
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter("data.txt",true));
			writer.newLine();
			writer.append(j.getUname()+":"+j.getPass()+":"+j.getEmail()); 
		    writer.close();
		    return "SUCCESS";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		}
	    
        
	      
		}
	
	
	public String validate(String uname,String pass){
		int flag = 0;
		File file = new File("data.txt");
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				String[] arr = line.split(":");
				if(arr[0].equalsIgnoreCase(uname)&&arr[1].equals(pass)){
					flag = 1;
					break;
				}
			}
			
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		}
		if(flag == 1){
			return "SUCCESS";
		}
		else{
			return "FAILURE";
		}

		
	}
	
	
	public String reset(String uname,String oldpass,String pass){
		
		String arr = "";
		File file = new File("data.txt");
		FileReader fileReader;
		FileWriter writer = null;
		try {
			fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				arr = arr+line+System.lineSeparator();
			}
			bufferedReader.close();
			System.out.println(arr);
			String old = ":"+oldpass+":";
			String newp = ":"+pass+":";
			String newfile = arr.replace(old, pass);
			writer = new FileWriter(new File("data.txt"));
			
			writer.write(newfile);
			writer.close();
			return "SUCCESS";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "FAILURE";
		}
		
	}
	}
