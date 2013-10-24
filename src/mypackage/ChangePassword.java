package mypackage;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ChangePassword extends MainScreen {
	
	private PasswordEditField currentPassword,newPassword,confirmPassword;

	/**
	 * 
	 */
	public ChangePassword() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Change password");
		
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
        vfm.setPadding(10, 10, 10, 10);
        add(vfm);
        
        currentPassword = new PasswordEditField("Current Password : ", "");
        vfm.add( currentPassword );
        newPassword = new PasswordEditField("New Password : ", "");
        vfm.add( newPassword );
        confirmPassword = new PasswordEditField("Confirm Password : ", "");
        vfm.add( confirmPassword );
        
        ButtonField submitButton = new ButtonField( "Submit", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        vfm.add( submitButton );
        submitButton.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	if(currentPassword.getText().length() == 0 || newPassword.getText().length() == 0 || confirmPassword.getText().length() == 0){
            		Dialog.inform("Please enter all fields");
            	}else{
            		Dialog.inform("new passwords = "+newPassword.getText().toString()+" , confirmpass = "+confirmPassword.getText().toString());
            		if((newPassword.getText().toString())!=(confirmPassword.getText().toString())){
            			Dialog.inform("Confirm password not matching");
            		}
            	}
            }
        } );
	}

}
