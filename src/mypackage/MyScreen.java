package mypackage;

import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class MyScreen extends MainScreen
{
	private EditField username;
	private PasswordEditField password;
    /**
     * Creates a new MyScreen object
     */
    public MyScreen()
    {
    	super( MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR );
        // Set the displayed title of the screen       
        setTitle("Login");
        
        VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
        vfm.setPadding(10, 10, 10, 10);
        add(vfm);
        
        username = new EditField("User name : ", "");
        vfm.add( username );
        
        password = new PasswordEditField("Password : ", "");
        vfm.add( password );
        
        LabelField forgotpassword = new LabelField("Forgot password",LabelField.FIELD_RIGHT | LabelField.FOCUSABLE){
            public void paint(Graphics g){
                g.setColor(Color.BLUE);
                g.clear();
                super.paint(g);
            }
            protected boolean navigationClick(int status, int time){
            	UiApplication.getUiApplication().pushScreen( new ForgotPassword() );
	            return true;
            }
        };
        vfm.add(forgotpassword);
        
        ButtonField loginButton = new ButtonField( "Login", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        vfm.add( loginButton );
        loginButton.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
                login();
            }
        } );
        
    }
    private void login() {
    	if(username.getText().length() == 0 || password.getText().length() == 0){
    		Dialog.inform("Please enter username and password");
    	}else{
    		UiApplication.getUiApplication().pushScreen( new Dashboard() );
    	}
    }
}
