package mypackage;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EditField;
import net.rim.device.api.ui.component.PasswordEditField;
import net.rim.device.api.ui.container.MainScreen;

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
        setTitle("MyTitle");

        username = new EditField("User name : ", "");
        add( username );
        
        password = new PasswordEditField("Password : ", "");
        add( password );
        
        ButtonField loginButton = new ButtonField( "Login", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
        add( loginButton );
        loginButton.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
                login();
            }
        } );
    }
    private void login() {
        Dialog.inform( "Hello " + username.getText() + " password " +password.getText());
    }
}
