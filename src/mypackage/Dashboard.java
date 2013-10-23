package mypackage;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class Dashboard extends MainScreen {

	/**
	 * 
	 */
	public Dashboard() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Home");
		
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
        vfm.setPadding(10, 10, 10, 10);
        add(vfm);
		
		ButtonField aboutUs = new ButtonField( "About Us", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        vfm.add(aboutUs);
        aboutUs.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	UiApplication.getUiApplication().pushScreen( new AboutUs() );
            }
        } );
        
        ButtonField changePassword = new ButtonField( "Change Password", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        vfm.add( changePassword );
        changePassword.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	UiApplication.getUiApplication().pushScreen( new ChangePassword() );
            }
        } );
	}

}
