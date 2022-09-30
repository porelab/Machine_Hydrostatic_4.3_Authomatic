package application;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.Flags.Flag;

import com.jfoenix.controls.JFXSlider;

import Constants.MyContants;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import toast.MyDialoug;
import toast.Openscreen;
import toast.Toast;

public class QuicktestController implements Initializable {

	static String selectedrad1 = "", selectedrad2 = "", modeselectedrad = "";

	static ToggleGroup tgb1, tgb2,tgbmode;

	@FXML
	Button starttest, btncancel,btnaddsamplearea,btndelete,btndelete1;

	@FXML
	RadioButton rdmanual, rdautometed, rdregular, rdfast;

	@FXML
	ComboBox<String> cmbsampleid,cmbsamplearea;

	@FXML
	Label lblnote;

	@FXML
	JFXSlider stepsizeslider;

	@FXML
	AnchorPane ancaddsamplearea;
	
	@FXML
	TextField txtsamplearea,txtlotno,txtmaxpres,txtfixedtime,txtsamplethickness;
	
	@FXML
	RadioButton rdmode1,rdmode2;
	
	@FXML
	Rectangle recfixedtime;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		btndelete.setVisible(false);
		stepsizeslider.setMin(1);
		stepsizeslider.setValue(15);
		setTestMode();
		setStepSize();
		LoadSamples();
		setSamplemode();

		stepsizeslider.valueProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				double v = (double) newValue;

				MyContants.stepsize = "" + (int) v;

				lblnote.setText("Note : This will plot " + (60 / (int) v) + " Readings in 1 Minutes.");

			}

		});

		btncancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub

				MyDialoug.closeDialoug();

			}
		});
		

		starttest.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				if(!cmbsampleid.getValue().equals(""))
					{
					teststart();
			} else {
				Toast.makeText(Main.mainstage,
						"Enter Sample name...!", 1500, 500, 500);

			}

			}
		});

		getSampleareadata();
		
	/*	cmbsamplearea.valueProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable,
					String oldValue, String newValue) {
				// TODO Auto-generated method stub

				try {
					if (newValue.equals("Add Fluid")) {
						ancaddsamplearea.setVisible(true);

					} else {

						ancaddsamplearea.setVisible(false);

						String deld = "select flag from samplearea where name='"
								+ newValue+ "'";

						Database d = new Database();
						List<List<String>> data = d.getData(deld);

						MyContants.samplearea = "" + newValue;
						
						//if (data.get(0).get(0).equals("0")) {
							btndelete.setVisible(true);
						//} else {
							//btndelete.setVisible(false);

						//}

					}
				} catch (Exception e) {

					e.printStackTrace();
					ancaddsamplearea.setVisible(false);
				}
			}
		});*/
		
		btnaddsamplearea.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub

				setNewsamplearea();

			}
		});
		
		btndelete.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Deletesamplearea();
			}
		});
		
		
		btndelete1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				setDeletesample();
			}
		});
		
	}
	
	void setDeletesample() {
		String ss = cmbsampleid.getSelectionModel().getSelectedItem();
		String del = "delete from quicktest where sampleid='"
				+ ss+ "'";
		Database database = new Database();
		if (database.Insert(del)) {
			cmbsampleid.getItems().remove(ss);
			txtlotno.setText("");
			txtfixedtime.setText("");
			txtsamplethickness.setText("");
			txtmaxpres.setText("");
		} else {

		}
	}
	
	void setSamplemode() {

		tgbmode = new ToggleGroup();

		
		rdmode1.setToggleGroup(tgbmode);
		rdmode1.setUserData("1");
		rdmode2.setToggleGroup(tgbmode);
		rdmode2.setUserData("2");
		
		modeselectedrad = "1";
		MyContants.smode = "mode1";

		tgbmode.selectedToggleProperty().addListener(
				new ChangeListener<Toggle>() {

					@Override
					public void changed(ObservableValue<? extends Toggle> arg0,
							Toggle arg1, Toggle arg2) {

						modeselectedrad = arg2.getUserData().toString();

						if (modeselectedrad.equals("1")) {
							MyContants.smode = "mode1";
							 recfixedtime.setVisible(false);
							 txtfixedtime.setVisible(false);
						}

						 else {
							 MyContants.smode = "mode2";
							 recfixedtime.setVisible(true);
							 txtfixedtime.setVisible(true);
						}

					}
				});

	}
	

	void setTestMode() {

		tgb1 = new ToggleGroup();

		rdmanual.setToggleGroup(tgb1);
		rdmanual.setUserData("1");
		rdautometed.setToggleGroup(tgb1);
		rdautometed.setUserData("2");

		selectedrad1 = "1";
		MyContants.testmode = "manual";

		tgb1.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {

				selectedrad1 = arg2.getUserData().toString();

				if (selectedrad1.equals("1")) {
					MyContants.testmode = "manual";
				}

				else {
					MyContants.testmode = "automated";
				}

			}
		});

	}

	void setStepSize() {

		tgb2 = new ToggleGroup();

		rdregular.setToggleGroup(tgb2);
		rdregular.setUserData("1");
		rdfast.setToggleGroup(tgb2);
		rdfast.setUserData("2");

		selectedrad2 = "1";
		MyContants.pressurerate = "regular";

		tgb2.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {

				selectedrad2 = arg2.getUserData().toString();

				if (selectedrad2.equals("1")) {
					MyContants.pressurerate = "regular";
				}

				else {
					MyContants.pressurerate = "fast";
				}

			}
		});

	}

	void teststart() {

		MyContants.sampleid = "" + cmbsampleid.getValue();
		MyContants.lotno= ""+txtlotno.getText();
		MyContants.samplethickness= ""+txtsamplethickness.getText();
		MyContants.fixtime= ""+txtfixedtime.getText();
		MyContants.maxpressure= ""+txtmaxpres.getText();
		//MyContants.samplearea=""+cmbsamplearea.getValue();

		Database d1 = new Database();
		

		String laststatue = "update quicktest set last='false'";
		d1.Insert(laststatue);
		//System.out.println("ASLASLLLLLLLLLLLLLL"+laststatue);

		if (d1.isExist("select * from quicktest where sampleid='"+MyContants.sampleid+"'")) {

			
			
			
				
				String updatequick = "update quicktest set sampleid='" + cmbsampleid.getValue() +"', testmode='0', stepsize='"+MyContants.stepsize+"' ,pressurerate='0',lotno='"+MyContants.lotno+"',sarea='0',maxpres='"+MyContants.maxpressure+"',smode='"+MyContants.smode+"',fixtime='"+MyContants.fixtime+"',sthickness='"+MyContants.samplethickness+"',last='true' where sampleid='" + MyContants.sampleid + "'";

				if(d1.Insert(updatequick))
				{
					//System.out.println("Update Sample Data");
				//	lastsample();
					MyDialoug.closeDialoug();
					Openscreen.open("/userinput/Nlivetest.fxml");

				}
				else {
				
				//	System.out.println("Not Update Sample Data");
				}
				
				
			}
			
	

		

		else {
			
			if (d1.Insert("INSERT INTO quicktest VALUES('" + MyContants.sampleid + "','" + MyContants.testmode + "','"
					+ MyContants.stepsize + "','" + MyContants.pressurerate + "','" + MyContants.lotno + "','" + MyContants.samplearea + "','" + Myapp.email + "','true','" + MyContants.maxpressure + "','" + MyContants.smode + "','" + MyContants.fixtime + "','" + MyContants.samplethickness + "')")) {
			//	System.out.println("Insert data New Sample");
				//lastsample();
				MyDialoug.closeDialoug();
				Openscreen.open("/userinput/Nlivetest.fxml");

			}

			else {
				System.out.println("Not Insert data New Sample");

			}
			}

	}

	void LoadSamples() {

		Database d = new Database();
		
		List<List<String>> laststatue = d.getData("select * from quicktest where last='" + true + "' ");


		List<List<String>> info = d.getData("select sampleid from quicktest");
		try {

		cmbsampleid.getItems().add(""+laststatue.get(0).get(0));
			
			for (int i = 0; i < info.size(); i++) {
				cmbsampleid.getItems().add(info.get(i).get(0));

			}
		} catch (Exception e) {
			Exception ss;
		}


		
		if (cmbsampleid.getItems().size() > 0) {
			btndelete.setVisible(true);
			cmbsampleid.valueProperty().addListener(new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
					// TODO Auto-generated method stub
try {
					if (newValue.equals(""+laststatue.get(0).get(0))) {

							setLastData();

					} else {
						LoadSampleData(newValue);
					}
					
				} catch (Exception e) {
					// TODO: handle exception
				}

				}

			});

			cmbsampleid.getSelectionModel().select(0);

		}

	}

	void LoadSampleData(String samplename) {
		Database d = new Database();

		List<List<String>> alldata = d.getData("select * from quicktest where sampleid='" + samplename + "' ");

		System.out.println("AllData "+alldata);

		String testmode = "" + alldata.get(0).get(1);

		String stepsizesliders = (alldata.get(0).get(2));
		MyContants.stepsize = (stepsizesliders);
		stepsizeslider.setValue(Double.parseDouble(stepsizesliders));

		String pressurerate = "" + alldata.get(0).get(3);
		
		
		MyContants.lotno =""+ alldata.get(0).get(4);
		MyContants.fixtime =""+ alldata.get(0).get(10);
		MyContants.samplethickness =""+ alldata.get(0).get(11);
		System.out.println("Fix Time-------------->"+MyContants.fixtime);
		MyContants.maxpressure =""+ alldata.get(0).get(8);
		String smode =""+ alldata.get(0).get(9);
		txtlotno.setText(MyContants.lotno);
		txtsamplethickness.setText(MyContants.samplethickness);
		txtfixedtime.setText(MyContants.fixtime);
		txtmaxpres.setText(MyContants.maxpressure);

		//cmbsamplearea.setValue(""+ alldata.get(0).get(5));
		
		
		
		if (smode.equals("mode1")) {
			rdmode1.selectedProperty().set(true);
			MyContants.smode = "mode1";
			txtfixedtime.setVisible(false);
			recfixedtime.setVisible(false);

		} else {
			rdmode2.selectedProperty().set(true);
			MyContants.smode = "mode2";
			txtfixedtime.setVisible(true);
			recfixedtime.setVisible(true);
		}
	
		
	
		/* Test Mode */

		if (testmode.equals("manual")) {
			rdmanual.selectedProperty().set(true);
			MyContants.testmode = "manual";

		} else {
			rdautometed.selectedProperty().set(true);
			MyContants.testmode = "automated";
		}

		/* Pressure Rate */

		if (pressurerate.equals("regular")) {
			rdregular.selectedProperty().set(true);
			MyContants.pressurerate = "regular";

		} else {
			rdfast.selectedProperty().set(true);
			MyContants.pressurerate = "fast";

		}

	}

	
	void setLastData() {

		Database d1 = new Database();
		List<List<String>> alldata = d1
				.getData("select * from quicktest where emailid='" + Myapp.email
						+ "' and  last='true' ");
		
		System.out.println("All Data Quicktest"+alldata);

		String testmode = "" + alldata.get(0).get(1);

		String stepsizesliders = (alldata.get(0).get(2));
		MyContants.stepsize = (stepsizesliders);
		
		stepsizeslider.setValue(Double.parseDouble(stepsizesliders));

		String pressurerate = "" + alldata.get(0).get(3);
		
		MyContants.lotno =""+ alldata.get(0).get(4);
		MyContants.samplethickness =""+ alldata.get(0).get(11);		
		MyContants.fixtime =""+ alldata.get(0).get(10);
		MyContants.samplearea =""+ alldata.get(0).get(5);
		MyContants.maxpressure =""+ alldata.get(0).get(8);
		String smode =""+ alldata.get(0).get(9);
		
		txtsamplethickness.setText(MyContants.samplethickness);
		txtlotno.setText(MyContants.lotno);
		txtmaxpres.setText(MyContants.maxpressure);
		txtfixedtime.setText(MyContants.fixtime);

		cmbsamplearea.setValue(MyContants.samplearea);

		/* sample Mode */

		if (smode.equals("mode1")) {
			rdmode1.selectedProperty().set(true);
			MyContants.smode = "mode1";
			txtfixedtime.setVisible(false);
			recfixedtime.setVisible(false);

		} else {
			rdmode2.selectedProperty().set(true);
			MyContants.smode = "mode2";
			txtfixedtime.setVisible(true);
			recfixedtime.setVisible(true);
		}
		
		/* Test Mode */

		if (testmode.equals("manual")) {
			rdmanual.selectedProperty().set(true);
			MyContants.testmode = "manual";

		} else {
			rdautometed.selectedProperty().set(true);
			MyContants.testmode = "automated";
		}

		/* Pressure Rate */

		if (pressurerate.equals("regular")) {
			rdregular.selectedProperty().set(true);
			MyContants.pressurerate = "regular";

		} else {
			rdfast.selectedProperty().set(true);
			MyContants.pressurerate = "fast";
		}


	}
	
	/* Insert and update last data */
	void lastsample() {
		Database d1 = new Database();
		try {
			
			MyContants.sampleid = ""+cmbsampleid.getValue();
			MyContants.lotno= ""+txtlotno.getText();
			MyContants.samplethickness= ""+txtsamplethickness.getText();
			MyContants.samplearea=""+cmbsamplearea.getValue();
			MyContants.maxpressure= ""+txtmaxpres.getText();
			MyContants.fixtime =""+txtfixedtime.getText();
			

			if (!d1.isExist("select * from quicklastsample where emailid='"
					+ Myapp.email + "'")) {

				if (d1.Insert("INSERT INTO quicklastsample VALUES( '"+ Myapp.email + "','"+MyContants.sampleid+"', '"+MyContants.testmode+"', '"+MyContants.stepsize+"', '"+MyContants.pressurerate+"', '"+MyContants.lotno+"', '"+MyContants.samplearea+"', '"+MyContants.maxpressure+"', '"+MyContants.smode+"', '"+MyContants.fixtime+"', '"+MyContants.samplethickness+"')")) 
				
				{
					// System.out.println("Last Project Insert data");
				} else {
					// System.out.println(" Last Project  not insertetd");
				}
			} else {

				String updatequry = "update quicklastsample set sampleid='"
						+ MyContants.sampleid + "',testmode='"
						+ MyContants.testmode + "',stepsize='"+MyContants.stepsize+"',pressurerate='"+MyContants.pressurerate+"',lotno='"+MyContants.lotno+"',sarea='"+MyContants.samplearea+"',maxpres='"+MyContants.maxpressure+"',smode='"+MyContants.smode+"',fixtime='"+MyContants.fixtime+"',sthickness='"+MyContants.samplethickness+"'  where emailid='" + Myapp.email
						+ "'";

				if (d1.Insert(updatequry)) {
					// System.out.println(" Updated");
				} else {

					// System.out.println("Not Updated");
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	/* Add new Fluid ame and value */
	void setNewsamplearea() {

		Database d1 = new Database();

		if (d1.Insert("INSERT INTO samplearea VALUES('" + txtsamplearea.getText()
				+ "','0')")) {
			cmbsamplearea.getItems().add(
					txtsamplearea.getText());
			cmbsamplearea.getSelectionModel().select(
					txtsamplearea.getText());

			Toast.makeText(Main.mainstage,
					"Successfully New Sample Area Added.", 1500, 500, 500);
		} else {
		}

	}
	/* Fluid delete */
	void Deletesamplearea() {
		String ss = cmbsamplearea.getSelectionModel().getSelectedItem();
		String del = "delete from samplearea where name='"
				+ ss+ "'";
		Database database = new Database();
		if (database.Insert(del)) {
			cmbsamplearea.getItems().remove(ss);
		} else {

		}
	}
	
	/* Get Fluid in Databse */
	void getSampleareadata() {
		Database d = new Database();
		List<List<String>> info = d.getData("select * from samplearea");

		try {

			cmbsamplearea.getItems().add("Add Fluid");
			for (int i = 0; i < info.size(); i++) {
				cmbsamplearea.getItems().add(
						info.get(i).get(0));

				MyContants.samplearea = "" + info.get(i).get(0);
			}
			cmbsamplearea.getSelectionModel().select(1);
		} catch (Exception e) {
			Exception ss;
		}

	}

	
}
