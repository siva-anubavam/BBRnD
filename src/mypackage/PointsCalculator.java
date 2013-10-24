package mypackage;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class PointsCalculator extends MainScreen {

	/**
	 * 
	 */
	public PointsCalculator() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		//setTitle("Points calculator");
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
        vfm.setPadding(10, 10, 10, 10);
        add(vfm);
        
        final VerticalFieldManager vfmResult = new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.FIELD_HCENTER);
        
        LabelField headingText = new LabelField("POINTS CALCULATOR",LabelField.FIELD_HCENTER);
        vfm.add(headingText);
        
        LabelField headerLabelText = new LabelField("Use the Points Calculator to find out how many points you can earn in relation to your spend. We have different Earning Schemes for brands and their location.",LabelField.FIELD_HCENTER);
        headerLabelText.setMargin(10, 0, 10, 0);
        vfm.add(headerLabelText);
        
	    String countryChoices[] = {"UAE1","UAE2","UAE3","UAE4",
	                          "UAE5","UAE6","UAE7"};
	    int iSetTo = 0;
	    vfm.add(new ObjectChoiceField("Select Country",countryChoices,iSetTo));
	    
	    String brandChoices[] = {"Brand1","Brand2","Brand3","Brand4",
                "Brand5","Brand6","Brand7"};
		vfm.add(new ObjectChoiceField("Select Brand",brandChoices,iSetTo));
		
		BasicEditField price = new BasicEditField(
                "Enter Price: ", "", 10, BasicEditField.FILTER_NUMERIC|Field.USE_ALL_WIDTH);
		vfm.add(price);
		price.setMargin(5, 5, 5, 5);
				
		ButtonField calcPoints = new ButtonField( "Calculate points", ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER );
		calcPoints.setMargin(10, 10, 10, 10);
		vfm.add( calcPoints );
        calcPoints.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	if(vfmResult != null){
            		add(vfmResult);
            	}
            }
        });
           
        LabelField nonSaleText = new LabelField("NON-SALE POINTS",LabelField.FIELD_HCENTER);
        vfmResult.add(nonSaleText);
        
        LabelField nonSaleValue = new LabelField("1",LabelField.FIELD_HCENTER);
        nonSaleValue.setMargin(10, 10, 10, 10);
        vfmResult.add(nonSaleValue);
        
        LabelField saleText = new LabelField("SALE POINTS",LabelField.FIELD_HCENTER);
        vfmResult.add(saleText);
        
        LabelField saleValue = new LabelField("0",LabelField.FIELD_HCENTER);
        saleValue.setMargin(10, 10, 10, 10);
        vfmResult.add(saleValue);
        
	}
	public boolean onSavePrompt()
    {
        return true;
    }

}
