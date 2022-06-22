package myconstant;

import java.util.List;

import application.Database;

public class Myconstant 
{
	
	public static String sampleid,testmode,stepsize,pressurerate,lotno,samplearea;

	public static String getipaddress()
	{
		String ip="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select ip from connection");
		ip =(ll.get(0).get(0));

		return ip;
	}
}


