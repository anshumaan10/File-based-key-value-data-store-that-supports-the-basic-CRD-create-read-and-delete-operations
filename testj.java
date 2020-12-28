package keystore;
import java.io.*;
import java.sql.Date;
import java.sql.Timestamp;
import org.json.JSONObject;
import java.util.Scanner;

class resource{
	          String path;
	JSONObject database = new JSONObject();    //JSON object 
	
	void path(){//Creates the file in a default location or create file in the location provided by the user
		
		String location;
		System.out.println("Do you want to give Location for the file yes/no");
		Scanner sc=new Scanner(System.in);
		location=sc.nextLine();
		if(location.contentEquals("yes")) {
			System.out.println("Enter the path");
			path=sc.nextLine();                          //Creates file in location provided by user
			 try {
			      File myObj = new File(path);
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());   
			      } else {
			        System.out.println("File already exists.");
			        
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}else {
			path="C:\\Users\\Admin\\Desktop\\freshworks\\default\\file.txt";  //Default path for file
			 try {
			      File myObj = new File(path);
			      if (myObj.createNewFile()) {
			        System.out.println("File created: " + myObj.getName());
			      } else {
			        System.out.println("File already exists.");
			      }
			    } catch (IOException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
			    }
		}
		
	}
	String pop() {
		return path;                     //Just return the file location
	}
	}
public class testj extends resource{
	
	void create(String key,int value)throws Exception {
		
		if(database.has(key)) {    //If Create is invoked for an existing key, an appropriate error must be returned
			
			
			System.out.println("Error: already has the exisiting key");  
			}
		else{
			if(key.length()<=32 && key!= null && value<(16*1024*1024)) { //Key(string) capped at 32chars
if(pop().length() < (1024*1020*1024)) {        // file storing data must never exceed 1GB

					try {
					      FileWriter myWriter = new FileWriter(pop(),true);
					     
                          myWriter.write(database.put(key,value).toString());
                          myWriter.write("\n");
					      myWriter.flush();
					      myWriter.close();
					      System.out.println("Successfully wrote to the file.");
					    } catch (IOException e) {
					      System.out.println("An error occurred.");
					      e.printStackTrace();
					    }
}else{
						System.out.println("size of file is greater than 1 Gb:"+ pop().length());
					}

		}else{
			System.out.println("Error:- Invalid key length");
		}
	
	}}

	void read(String key) {   // Read operation read data through key
		if(database.length()==0) {
			System.out.println("Empty database\n Start again");
			return;
		}
		else if(!database.has(key)) {
			System.out.println("Key is not present in database");
			
		}
		else {
		 try {
		      File myObj = new File(pop());
		      Scanner myReader = new Scanner(myObj);
		     
		        
		        System.out.println(key+":"+database.get(key));
		      
		      myReader.close();
		    } catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		
		}
	}
	void delete(String key) {// // Delete operation Deletes data through key
		
		if(database.length()==0) {
			System.out.println("Empty database cannot delete \n Start again");
			
			return;
		}
		else {
			database.remove(key);
		}
	}
	
	public static void main(String[] args)throws Exception {
	
        Scanner sc=new Scanner(System.in);
	   testj kv=new testj();
	   
	   String key; 
	   int value;
	  boolean a=true,b=true;
	  kv.path();
	  


	
while(b) {
	  System.out.println("Enter the operation you want to perform 1)create 2)Read 3)Delete");
		 
	  String operation=sc.nextLine();
	 if(operation.equals("create")) {
		 a=true;
	      while(a){
	      System.out.println("Enter the key");
	     
		 key=sc.nextLine();
		
		 System.out.println("Enter the data");
		 value=sc.nextInt();
		 kv.create(key,value);
		 System.out.println("Do you want to enter the data again yes/no");
		 sc.nextLine();
		String entry2=sc.nextLine();
	 if(entry2.equals("yes")) {
			 a=true;
		 }else {
			 a=false;
			  }
	     }
	      }
	 else if(operation.equals("read")) {
		 System.out.println("Enter the value of key");
		 String vr=sc.nextLine();
		 kv.read(vr);
	 }
	 else if(operation.contentEquals("delete")) {
		 System.out.println("Enter the value of key to delete the data");
		 String vd=sc.nextLine();
		 kv.delete(vd);
	 }
	 else {
		 System.out.println("Invalid operation\nStart again");
		 return;
	 }
			System.out.println("Do you want to Perform some operation again");
			  String perform=sc.nextLine();
			 if(perform.equals("yes")) {
				 b=true;
			 }
			 else {
				 b=false;
				 System.out.println("program terminated");
				 return;
			 }
}
			 }
}




