package Constants;

import java.util.List;

import application.Database;

public class MyContants {
	
	//in seconds
	public static int mode2time=200;
	
	public static String sampleid="MySample",lotno="MyLot";
	public static String stepsize="1";
	public static double maxPressure=4;
	
	public static String testmode,pressurerate,samplearea,maxpressure,smode;
	
	/*test settong*/
	
	public static String incrate,incpr,initpr,delp,fdrop;

	
	

	public static String getincrate()
	{
		String incrate="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select incrate from test_setting");
		incrate =(ll.get(0).get(0));

		return incrate;
	}
	public static String getincpr()
	{
		String incpr="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select incpr from test_setting");
		incpr =(ll.get(0).get(0));

		return incpr;
	}
	public static String getinitpr()
	{
		String initpr="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select initpr from test_setting");
		initpr =(ll.get(0).get(0));

		return initpr;
	}

	public static String getdelp()
	{
		String delp="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select delp from test_setting");
		delp =(ll.get(0).get(0));

		return delp;
	}
	
	public static String getfaildrop()
	{
		String fdrop="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select fdrop from test_setting");
		fdrop =(ll.get(0).get(0));

		return fdrop;
	}

	
	public static String getFillingPressureN()
	{
		String incrate="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select incrate from test_setting");
		incrate =(ll.get(0).get(0));

		return incrate;
	}
	public static String getInitprN()
	{
		String initpr="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select initpr from test_setting");
		initpr =(ll.get(0).get(0));

		return initpr;
	}
	public static String getX()
	{
		String incpr="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select incpr from test_setting");
		incpr =(ll.get(0).get(0));

		return incpr;
	}
	public static String getY()
	{
		String delp="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select delp from test_setting");
		delp =(ll.get(0).get(0));

		return delp;
	}
	public static String getDropN()
	{
		String fdrop="";
		Database db=new Database();		
		List<List<String>> ll=db.getData("select fdrop from test_setting");
		fdrop =(ll.get(0).get(0));

		return fdrop;
	}
}
