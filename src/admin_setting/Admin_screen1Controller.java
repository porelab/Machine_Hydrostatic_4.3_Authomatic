package admin_setting;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXToggleButton;

import Constants.MyContants;
import application.DataStore;
import application.Database;
import application.Main;
import javafx.event.ActionEvent;



import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import toast.Toast;

public class Admin_screen1Controller implements Initializable {

	 	@FXML
	    JFXToggleButton valvela,valvelb,valvelc,valveld,valvele,valvelf,valvelg,valvelh;

	 	@FXML
	    JFXToggleButton valvecpre,valvecflow;

	    @FXML
	    private Button btnapply;
	    
	    String va="0",vb="low",vc="0",vd="0",ve="0",vf="0",vg="0",vh="0",pc="0",fc="0";
	
	    @FXML
	    private TextField txtincrrate;

	    @FXML
	    private TextField initpr;

	    @FXML
	    private TextField incrpr;

	    @FXML
	    private TextField delp;

	    @FXML
	    private Button btnsettingsave;
	
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		setData();
		settest_setting();
btnapply.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				setvalveselection();
				
				String query = "update admin_screen1 set va='"+va+"',vb='"+vb+"',vc='"+vc+"',vd='"+vd+"',ve='"+ve+"',vf='"+vf+"',vg='"+vg+"',vh='"+vh+"',pc='"+pc+"',fc='"+fc+"'"; 
				
				Database dd = new Database();
				dd.Insert(query);
				
				DataStore.hardReset();

			 Toast.makeText(Main.mainstage, "Successfully Set....", 1000, 200, 200);

			}
		});
		

	
	btnsettingsave.setOnAction(new EventHandler<ActionEvent>() {
		
		@Override
		public void handle(ActionEvent arg0) {
			// TODO Auto-generated method stub
		
			savesetting();
			
		}
	});
	
	}
	void savesetting()
	{
		MyContants.incrate =""+txtincrrate.getText();
		MyContants.incpr =""+ incrpr.getText();
		MyContants.initpr =""+ initpr.getText();
		MyContants.delp =""+delp.getText();
		
		String query = "update test_setting set incrate='"+MyContants.incrate+"',incpr='"+MyContants.incpr+"',initpr='"+MyContants.initpr+"',delp='"+MyContants.delp+"'"; 
		
		Database dd = new Database();
		dd.Insert(query);
		

	 Toast.makeText(Main.mainstage, "Successfully save test setting....", 1000, 200, 200);

		
	}




	void setvalveselection()
	{
		
			if(valvela.isSelected())
			{
				va="1";
			}
			
			else 
			{
				va="0";

			}
			
			if(valvelb.isSelected())
			{
				vb="1";
			}
			
			else 
			{
				vb="0";
			}
			
			if(valvelc.isSelected())
			{
				vc="1";
			}
			
			else 
			{
				vc="0";
			}

			
			if(valveld.isSelected())
			{
				vd="1";
			}
			
			else 
			{
				vd="0";
			}
			
			
			if(valvele.isSelected())
			{
				ve="1";
			}
			
			else 
			{
				ve="0";
			}
			
			if(valvelf.isSelected())
			{
				vf="1";
			}
			
			else 
			{
				vf="0";
			}
			
			if(valvelg.isSelected())
			{
				vg="1";
			}
			
			else 
			{
				vg="0";
			}

			
			if(valvelh.isSelected())
			{
				vh="1";
			}
			
			else 
			{
				vh="0";
			}
			
			if(valvecpre.isSelected())
			{
				pc="1";
			}
			
			else 
			{
				pc="0";
			}

			
			if(valvecflow.isSelected())
			{
				fc="1";
			}
			
			else 
			{
				fc="0";
			}
			

	
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

		
		txtincrrate.setText(MyContants.incrate);
		incrpr.setText(MyContants.incpr);
		initpr.setText(MyContants.initpr);
		delp.setText(MyContants.delp);

		
	}
	void setData()
	{
		List<String> data=DataStore.getAdmin_screen1();
		
		if(data.get(0).equals("1"))
		{
			valvela.setSelected(true);
		}
		if(data.get(1).equals("1"))
		{
			valvelb.setSelected(true);
		}
		if(data.get(2).equals("1"))
		{
			valvelc.setSelected(true);
		}
		if(data.get(3).equals("1"))
		{
			valveld.setSelected(true);
		}
		if(data.get(4).equals("1"))
		{
			valvele.setSelected(true);
		}
		if(data.get(5).equals("1"))
		{
			valvelf.setSelected(true);
		}
		if(data.get(6).equals("1"))
		{
			valvelg.setSelected(true);
		}
		if(data.get(7).equals("1"))
		{
			valvelh.setSelected(true);
		}
		
		
		if(data.get(8).equals("1"))
		{
			valvecpre.setSelected(true);
		}
		if(data.get(9).equals("1"))
		{
			valvecflow.setSelected(true);
		}
		
		
	}

	

}
