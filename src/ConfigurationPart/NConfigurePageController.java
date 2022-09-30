
package ConfigurationPart;

import gnu.io.CommPortIdentifier;

import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import toast.MyDialoug;
import toast.Openscreen;
import toast.Toast;
import Notification.Notification;
import application.DataStore;
import application.Database;
import application.Main;
import application.Myapp;

import com.jfoenix.controls.JFXToggleButton;

import Constants.MyContants;

public class NConfigurePageController implements Initializable {

	
	
	@FXML
	AnchorPane root,ancwifi;
	
	@FXML
    JFXToggleButton pg1,pg2,fm1,fm2,ab1,ab2,re1,re2,tgbkeyboard,curvefittgb,valvecpre,valvecflow;
	
	private Notification.Notifier notifier;
	
    @FXML
    Label comlab;

    @FXML
    ImageView imgdownarrow,imgback;
    
    @FXML
    private TextField ppg1,ppg2,pfm1,pfm2,ppr,pfc,txtthfirst,txtthmoderate,txtthcontinous;

    @FXML
    private Button applypro;

    @FXML
    private JFXToggleButton tgb215,tgb2111;

    @FXML
    private Button btndefaultsetting,comsave,back,btncalibration,testconfig,btnprefrance;

    @FXML
    ComboBox cmbcom;
    
    @FXML
    ComboBox<String> cmbpg1,cmbpg2,cmbpress, cmbflow, cmblenghth, cmbroundoff;

	String propg1="low",profm1="low",propg2="low",profm2="low";
	
	String pp1scaletype="false",pp2scaletype="absolute",curvefit="off",crospres="0",crosflov="0";

	static String selectedrad="",Por;
	
	public static boolean bolkey = false,bolkeylet = false;
	
	Database db=new Database();
	
	static ToggleGroup tgb5,tgb6,tgbconnection,tgbsplate;
	static String selectedrad4 = "",selectedrad5 = "",selectedradconnection = "";
	
	@FXML
    RadioButton pressregulator,flowcontrolcalibr,troubleshot,leaktest,boardcali,manual,autometed;

	/*Conneciotn tab*/
	
	@FXML
	RadioButton radiowifi,radiohospot;
	
	@FXML
    private RadioButton rdsmall;

    @FXML
    private RadioButton rdmedium;

    @FXML
    private RadioButton rdlarge;
	
	@FXML
	TextField txtwifiid,txtwifipass;
	
	@FXML
	Button btnsaveconnection;
	
	 @FXML
	    private TextField txtincrrate;

	    @FXML
	    private TextField initpr;

	    @FXML
	    private TextField incrpr;

	    @FXML
	    private TextField delp;
	    
	    @FXML
	    private TextField Faildrop;

	    @FXML
	    private Button btnsettingsave;
	
	
   MyDialoug mydia;
    
	void addShortCut() {
		
		 KeyCombination backevent = new KeyCodeCombination(KeyCode.B, KeyCombination.CONTROL_ANY);
			
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				
				if(backevent.match(ke))
				{

					Openscreen.open("/application/first.fxml");
				}
				
			}
		});
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		
		settest_setting();
		System.out.println("Incrate : "+MyContants.getincrate());
		System.out.println("IncPR : "+MyContants.getincpr());
		System.out.println("InitPR : "+MyContants.getinitpr());
		System.out.println("Delp : "+MyContants.getdelp());
		System.out.println("Fail drip : "+MyContants.getfaildrop());
		System.out.println("Ip ddress : -" +DataStore.getipaddress());
		
		btnsettingsave.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
			
				savesetting();
				
			}
		});
		
		
		btnsaveconnection.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
				String sql = "update connection set lastconnection='"+Myapp.lastinternet+"',id='"+txtwifiid.getText()+"',pass='"+txtwifipass.getText()+"',ip='"+"5"+"'"; 
				
		
				
				if(db.Insert(sql))
				{
					 Toast.makeText(Main.mainstage, "Successfully Save Internet Connection Data..", 1000, 200, 200);

				}
				else {
		//			System.out.println("Configration Data save d Eroorr.....");
				}
				
			}
		});
		
btndefaultsetting.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
			
				mydia = new MyDialoug(Main.mainstage,
						"/ConfigurationPart/defaultsettingpopup.fxml");
				mydia.showDialoug();
			
			}
		});
selectelowhigh();
setsampleplate();
	setlastconnectiondata();

			addShortCut();
	     setMainBtns();
	     setulastdata();
	     setTestLastunite();
			setLastunite();
		 setBtnClicks();
	
		setPortList();
		setCalibrationType();
		setChamerType();
		setValveselection();
		selectelowhigh();
		setconnection();
		
	
		DataStore.getthfirstbp();
		// cmbcom.getItems().addAll("Test", "Test2", "Test3");
		cmbpress.getItems().addAll("psi", "bar","mbar", "torr","kilopascal","cmH2O");
		cmbflow.getItems().addAll("sccm", "sccs","cfm");
		cmblenghth.getItems().addAll("nm", "µm");
		cmbroundoff.getItems().addAll("1", "2", "3","4","5");
		
		
		/*Test Config Unite and Pg Absolute or relative*/
		
		cmbpg1.getItems().addAll("psi", "bar", "torr");
		cmbpg2.getItems().addAll("psi", "bar", "torr");
		
			 setkeyboardmode();
		
		 }
	/* Material Type Selection */
	void setsampleplate() {

		tgbsplate = new ToggleGroup();

		rdsmall.setToggleGroup(tgbsplate);
		rdsmall.setUserData("1");
		rdmedium.setToggleGroup(tgbsplate);
		rdmedium.setUserData("2");
		rdlarge.setToggleGroup(tgbsplate);
		rdlarge.setUserData("3");

		selectedrad4 = "1";
		MyContants.splates = "5cm";

		tgbsplate.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0,
					Toggle arg1, Toggle arg2) {
				if (arg2 == null)
					arg1.setSelected(true);
				selectedrad4 = arg2.getUserData().toString();

				if (selectedrad4.equals("1")) {
					MyContants.splates = "5cm";
				}

				else if (selectedrad4.equals("2")) {
					MyContants.splates = "7.95cm";
				}

				else {
					MyContants.splates = "11.28cm";
				}

			}

		}

		);

	}

	
	void setToggleButtonProperty(ToggleButton tb, String title, String img,
			ToggleGroup group) {
		Image image = new Image(this.getClass().getResourceAsStream(img));
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(150);
		imageView.setFitHeight(160);
		tb.setGraphic(imageView);
		tb.setContentDisplay(ContentDisplay.TOP);
		tb.setToggleGroup(group);
		tb.setUserData(title);
		tb.setText(title);

	}


			void setMainBtns()
			{
					 Image image;
					 
					 image = new Image(this.getClass().getResourceAsStream("/ConfigurationPart/downarrow.png"));
				     imgdownarrow.setImage(image);
				
					 image = new Image(this.getClass().getResourceAsStream("/ConfigurationPart/back.png"));
				     imgback.setImage(image);
	
				
			}
	
			void setBtnClicks() {
				
				/* selected unite save in database */
				btnprefrance.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						unitesave();
					}
				});

		
				back.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						Openscreen.open("/application/first.fxml");
						
					}
				});
				
				comsave.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
					comsave();
					}
				});
				
				apllaypro();
			
				btncalibration.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						if (leaktest.isSelected()) {
							Openscreen.open("/calibration/leaktest_graph.fxml");
							
						}
						else if (flowcontrolcalibr.isSelected()) {
							Openscreen.open("/calibration/flowcalibration.fxml");
							
						}

						
					}
				});
				
				
				testconfig.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						setValveselection();
						
						
						DataStore.thfirtbp=txtthfirst.getText();
						DataStore.thmoderat=txtthmoderate.getText();
						DataStore.thcontinous=txtthcontinous.getText();
						
						
						
						String sql = "update configdata set chambertype='"+Myapp.chambertype+"',curvefittgb='"+curvefit+"' where type='"+"pro"+"'"; 
						String sql1 = "update admin_screen1 set pc='"+crospres+"',fc='"+crosflov+"'"; 
						String sqlthresold = "update configdata set thfirst='"+DataStore.thfirtbp+"',thmoderate='"+DataStore.thmoderat+"',thcontinous='"+DataStore.thcontinous+"'"; 
						
						
						if(db.Insert(sql) && db.Insert(sql1) && db.Insert(sqlthresold))
						{
							 Toast.makeText(Main.mainstage, "Successfully Save Configuration Data..", 1000, 200, 200);

						}
						else {
				//			System.out.println("Configration Data save d Eroorr.....");
						}
						
						
						
						
					}
				});
				
				
			
			}
											   
				void selectelowhigh()
				{
					if(pg1.isSelected())
					{
						propg1="high";
					}
					
					else 
					{
						propg1="low";
					}

			//system config Pressure Guage-2----------------------------
					
					if(pg2.isSelected())
						{
							propg2="high";	
						}
						
						else 
						{
							propg2="low";
						}
					
			//system config Flow Meter-1----------------------------							
				

					if(fm1.isSelected())
							{
								profm1="high";
							}
							
							else 
							{
								profm1="low";
							}
							

						if(fm2.isSelected())
								{
								profm2="high";
								}
								
								else 
								{

									profm2="low";
								}
								
						
						

								//Pro in absolute and relative in pg1
					
										
										
								

								
									//pro in absolute and relative in pg2
																							
												if(ab2.isSelected())
												{

													pp2scaletype="relative";
												}
												
												else 
												{

											
													pp2scaletype="absolute";
												}

				}
				
				void setValveselection()
				{

					//curve fit
					
					if(curvefittgb.isSelected())
					{

						curvefit="on";
					}
					
					else 
					{

				
						curvefit="off";
					}

					/*Cross over*/
					if(valvecpre.isSelected())
					{
						crospres="1";
					}
					
					else 
					{
						crospres="0";

					}

					
					if(valvecflow.isSelected())
					{
						crosflov="1";
					}
					
					else 
					{
						crosflov="0";
					}
				}
				
				public void setPortList()
				{
					System.out.println("Loading list of ports");
					
					Enumeration pList = CommPortIdentifier.getPortIdentifiers();
					

					while (pList.hasMoreElements()) {
						
						CommPortIdentifier cpi = (CommPortIdentifier) pList.nextElement();
					    System.out.print("Port " + cpi.getName() +" "+cpi.getPortType());
					    cmbcom.getItems().add(cpi.getName());
					    
					   
					    
					}
					   
					
				}
				
				void savesetting()
				{
					MyContants.incrate =""+txtincrrate.getText();
					MyContants.incpr =""+ incrpr.getText();
					MyContants.initpr =""+ initpr.getText();
					MyContants.delp =""+delp.getText();
					MyContants.fdrop =""+Faildrop.getText();
					
					String query = "update test_setting set incrate='"+MyContants.incrate+"',incpr='"+MyContants.incpr+"',initpr='"+MyContants.initpr+"',delp='"+MyContants.delp+"',fdrop='"+MyContants.fdrop+"',splate='"+MyContants.splates+"'"; 
					
					Database dd = new Database();
					dd.Insert(query);
					

				 Toast.makeText(Main.mainstage, "Successfully save test setting....", 1000, 200, 200);

					
				}
				
				
				void settest_setting()
				{
					Database d = new Database();
					List<List<String>> info = d.getData("select * from test_setting");
					
					System.out.println("All test setting"+info);
					
					MyContants.incrate =""+ info.get(0).get(0);
					MyContants.incpr =""+ info.get(0).get(1);
					MyContants.initpr =""+ info.get(0).get(2);
					MyContants.delp =""+ info.get(0).get(3);
					MyContants.fdrop =""+ info.get(0).get(4);
					MyContants.splates =""+ info.get(0).get(5);

					
					txtincrrate.setText(MyContants.incrate);
					incrpr.setText(MyContants.incpr);
					initpr.setText(MyContants.initpr);
					delp.setText(MyContants.delp);
					Faildrop.setText(MyContants.fdrop);
					
					
					/* splate */
					if (MyContants.splates.equals("5cm")) {
						rdsmall.selectedProperty().set(true);
							Myapp.splate = "5cm";
					} else if (MyContants.splates.equals("7.95cm")) {
						rdmedium.selectedProperty().set(true);
						Myapp.splate = "7.95cm";

					} else if (MyContants.splates.equals("11.28cm")) {
						rdlarge.selectedProperty().set(true);
						Myapp.splate = "11.28cm";

					}

					
				}
				
				void unitesave() {

					String upress, uflow, roundoff, ulenghth;

					upress = cmbpress.getSelectionModel().getSelectedItem();
					uflow = cmbflow.getSelectionModel().getSelectedItem();
					ulenghth = cmblenghth.getSelectionModel().getSelectedItem();
					roundoff = cmbroundoff.getSelectionModel().getSelectedItem();

					String unites = "update unite set pressure='" + upress + "',  flow='" + uflow + "',  length='" + ulenghth
							+ "',  roundoff='" + roundoff + "'";

					Database dd = new Database();

					if (dd.Insert(unites)) {
						Toast.makeText(Main.mainstage, "Successfully saved selected Unites", 1000, 200, 200);

					}

				}

				
				

				void Testunitesave() {

					String pg1s, pg2s;

					pg1s = cmbpg1.getSelectionModel().getSelectedItem();
					pg2s = cmbpg2.getSelectionModel().getSelectedItem();
					

					String testunites = "update testunite set pg1='" + pg1s + "',  pg2='" + pg2s + "'";

					Database dd = new Database();

					if (dd.Insert(testunites)) {
						

						//Toast.makeText(Main.mainstage, "Successfully Saved Selected Test Unites", 1000, 200, 200);

					}

				}

				
				
		void comsave()
		{
			try{
				if(cmbcom.getSelectionModel().getSelectedItem()==null)
				{
					System.out.println("No Comport found");
				}
				else
				{
				//	notifier.notify( NotificationBuilder.create().title("Save.").message(cmbcom.getSelectionModel().getSelectedItem()+" save.").image(Notification.SUCCESS_ICON).build());
					 Toast.makeText(Main.mainstage, "Successfully saved "+cmbcom.getSelectionModel().getSelectedItem(), 1000, 200, 200);

					System.out.println("Com set to:"+cmbcom.getSelectionModel().getSelectedItem());
					
					String query = "update configdata set comport='"+cmbcom.getSelectionModel().getSelectedItem()+"'"; 
				
					Database dd = new Database();
					dd.Insert(query);
					comlab.setText(""+cmbcom.getSelectionModel().getSelectedItem());
				  

				}
				}
				catch(Exception e)
				{
					
				}
			
			
			if (tgbkeyboard.isSelected()) {
				bolkey=true;
				Myapp.tabletmode="true";
				
	        } else {
	        	bolkey=false;
	        	Myapp.tabletmode="false";
			
	        }
			
			String query = "update keyboardmode set mode='"+Myapp.tabletmode+"'";

			db.Insert(query);
			
			
			
		}
		
		void apllaypro()
		{
			
			applypro.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					if(ab1.isSelected())
					{
						bolkeylet=true;
						Myapp.ab1scale="true";
					}
					
					else 
					{
						bolkeylet=false;
						Myapp.ab1scale="false";

					}
					
					
					System.out.println("applypro"+Myapp.ab1scale);
		/*			
					String type="Pro";
				
					try {
						String sql1 ="delete from configdata";
						db.Insert(sql1);
						
					} catch (Exception e)
					{
					
					}*/
					String sql = "update configdata set pg1='"+ppg1.getText()+"', pg2='"+ppg2.getText()+"',fm1='"+pfm1.getText()+"',fm2='"+pfm2.getText()+"',pr='"+ppr.getText()+"',fc='"+pfc.getText()+"',pg1type='"+propg1+"',pg2type='"+propg2+"',fc1type='"+profm1+"',fc2type='"+profm2+"',ch='"+""+"',p1scaletype='"+Myapp.ab1scale+"',p2scaletype='"+pp2scaletype+"'where type='"+"pro"+"'"; 

					
					if(db.Insert(sql))
					{
						 Toast.makeText(Main.mainstage, "Successfully Apply", 1000, 200, 200);

					}
					else {
						System.out.println("Configration Data save d Eroorr.....");
					}

				
					
				
					Testunitesave();
					
				}
			});
		}
		
		void setTestLastunite() {
			List<List<String>> ll = db.getData("select * from testunite");

			String pg1 = (ll.get(0).get(0));
			String pg2 = (ll.get(0).get(1));
		
		

				
			cmbpg1.setValue(pg1);
			cmbpg2.setValue(pg2);
			
		
		
		}
		
		void setLastunite() {
			List<List<String>> ll = db.getData("select * from unite");
			String upres = (ll.get(0).get(0));
			String uflow = (ll.get(0).get(1));
			String ulength = (ll.get(0).get(2));
			String uthicknes = (ll.get(0).get(3));

			cmbpress.setValue(upres);
			cmbflow.setValue(uflow);
			cmblenghth.setValue(ulength);
			cmbroundoff.setValue(uthicknes);

		}

		
		public void setlastconnectiondata()
		{	

			Database db=new Database();
		
				List<List<String>> ll=db.getData("select * from connection");
				String lastconectiontype =(ll.get(0).get(0));
				Myapp.lastinternet = lastconectiontype;
				
				System.out.println("Last Coonection type"+Myapp.lastinternet);
					txtwifiid.setText(ll.get(0).get(1));
					txtwifipass.setText(ll.get(0).get(2));
			
					
					/* chamber */
					if(lastconectiontype.equals("wifi"))
					{
						Myapp.lastinternet="wifi";
						radiowifi.selectedProperty().set(true);
						ancwifi.setVisible(true);
				
					}
					else
					{
						ancwifi.setVisible(false);
						Myapp.lastinternet="hospot";
						radiohospot.selectedProperty().set(true);
					}
			
			}	
		
		
		
		public void setulastdata()
		{	
			comlab.setText(DataStore.getCom());

			
			try{
				Database db=new Database();
		
				List<List<String>> ll=db.getData("select * from configdata");
				String type =(ll.get(0).get(0));
				
				System.out.println("type"+type);
					ppg1.setText(ll.get(0).get(1));
					ppg2.setText(ll.get(0).get(2));
					pfm1.setText(ll.get(0).get(3));
					pfm2.setText(ll.get(0).get(4));
					ppr.setText(ll.get(0).get(5));
					pfc.setText(ll.get(0).get(6));
			//		chamber.setValue(ll.get(0).get(7));
				
					String type1p =(ll.get(0).get(8));
					String type2p =(ll.get(0).get(9));
					String type3p =(ll.get(0).get(10));
					String type4p =(ll.get(0).get(11));
					String pscaletype4p =(ll.get(0).get(12));
					String pscaletype5p =(ll.get(0).get(13));
					String chambertype =(ll.get(0).get(15));
					String curvefittype =(ll.get(0).get(16));
					txtthfirst.setText(ll.get(0).get(17));
					txtthmoderate.setText(ll.get(0).get(18));
					txtthcontinous.setText(ll.get(0).get(19));
					
					if(type1p.equals("low"))
					{
						pg1.setSelected(true);	
						propg1="low";
					}
					else
					{
						pg1.setSelected(true);	
						propg1="high";
					}
					
					if(type2p.equals("low"))
					{
						pg2.setSelected(true);	
						profm1="low";
					}
					else
					{
						pg2.setSelected(true);	
						profm1="high";
					}
			
					if(type3p.equals("low")) 
					{
						fm1.setSelected(true);
						propg2="low";
					}
					else
					{
						propg2="high";
					}	
					
					if(type4p.equals("low"))
					{
						fm2.setSelected(true);	
						profm2="low";
					}
					else
					{
						profm2="high";
					}
				
						//absolute and relative pscaletype

					
					
					
					if (pscaletype4p.equals("true")) {
						bolkeylet=true;
						ab1.setSelected(true);
					} else {
						bolkeylet=false;
						Myapp.ab1scale="false";
					}

					if (pscaletype5p.equals("relative")) {
						ab2.setSelected(true);
						pp2scaletype = "relative";
					} else {
						pp2scaletype = "absolute";
					}
					
				
					
					
					/* chamber */
					if(chambertype.equals("Manual"))
					{
						Myapp.chambertype="Manual";
						manual.selectedProperty().set(true);
				
					}
					else
					{
						Myapp.chambertype="Autometed";
						autometed.selectedProperty().set(true);
					}
				/*Curve fit*/
					if(curvefittype.equals("on"))
					{
						curvefittgb.setSelected(true);	
						curvefit="on";
					}
					else
					{
						curvefittgb.setSelected(false);	
						curvefit="off";
					}
					
					
					List<String> data=DataStore.getAdmin_screen1();
					
					if(data.get(8).equals("1"))
					{
						valvecpre.setSelected(true);
					}
					if(data.get(9).equals("1"))
					{
						valvecflow.setSelected(true);
					}
					
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			
			}	
		void setkeyboardmode() {
			
			Database db=new Database();
			
			List<List<String>> ll=db.getData("select * from keyboardmode");
			String mode =(ll.get(0).get(0));
			
			if(mode.equals("true"))
			{
				tgbkeyboard.setSelected(true);	
				bolkey=true;
			}
			else
			{
				tgbkeyboard.setSelected(false);	
				bolkey=false;
			}
			
			
		}
		
		/*Internet Connection selection.*/
		
		void setconnection() {

			tgbconnection = new ToggleGroup();

			radiowifi.setToggleGroup(tgbconnection);
			radiowifi.setUserData("1");
			radiohospot.setToggleGroup(tgbconnection);
			radiohospot.setUserData("2");
		

			selectedradconnection = "1";
			Myapp.lastinternet = "wifi";
	
			tgbconnection.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

				@Override
				public void changed(ObservableValue<? extends Toggle> arg0,
						Toggle arg1, Toggle arg2) {
					if (arg2 == null)
						arg1.setSelected(true);
					selectedradconnection = arg2.getUserData().toString();

					if (selectedradconnection.equals("1")) {
						
						Myapp.lastinternet="wifi";
						ancwifi.setVisible(true);

					}
					else {
						ancwifi.setVisible(false);
						Myapp.lastinternet="hospot";
					}
				}

			}
		);

	}

		
		
		
		void setCalibrationType() {

			tgb5 = new ToggleGroup();

			pressregulator.setToggleGroup(tgb5);
			pressregulator.setUserData("1");
			flowcontrolcalibr.setToggleGroup(tgb5);
			flowcontrolcalibr.setUserData("2");
			troubleshot.setToggleGroup(tgb5);
			troubleshot.setUserData("3");
			leaktest.setToggleGroup(tgb5);
			leaktest.setUserData("4");
			boardcali.setToggleGroup(tgb5);
			boardcali.setUserData("5");

			selectedrad4 = "1";


			tgb5.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

				@Override
				public void changed(ObservableValue<? extends Toggle> arg0,
						Toggle arg1, Toggle arg2) {
					if (arg2 == null)
						arg1.setSelected(true);
					selectedrad4 = arg2.getUserData().toString();

					if (selectedrad4.equals("1")) {
						
						
					}

					else if (selectedrad4.equals("2")) {
						

					}


					else if (selectedrad4.equals("3")) {
						System.out.println("3");
					}

					else if (selectedrad4.equals("4")) {
					

					}
					else {

					}
				}

			}
		);

	}

		void setChamerType() {

			tgb6 = new ToggleGroup();

			manual.setToggleGroup(tgb6);
			manual.setUserData("1");
			autometed.setToggleGroup(tgb6);
			autometed.setUserData("2");
		

			selectedrad5 = "1";
			Myapp.testsequence = "Manual";
	
			tgb6.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

				@Override
				public void changed(ObservableValue<? extends Toggle> arg0,
						Toggle arg1, Toggle arg2) {
					if (arg2 == null)
						arg1.setSelected(true);
					selectedrad5 = arg2.getUserData().toString();

					if (selectedrad5.equals("1")) {
						
						Myapp.chambertype="Manual";
						
					}
					else {

						Myapp.chambertype="Autometed";
						
					}
				}

			}
		);

	}

}
