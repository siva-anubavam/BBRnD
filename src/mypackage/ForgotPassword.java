package mypackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.EmailAddressEditField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ForgotPassword extends MainScreen {
	private EmailAddressEditField enterEmail;
	private HttpConnection httpConn;
	private InputStream is;

	/**
	 * 
	 */
	public ForgotPassword() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		 setTitle("Forgot password");
		 
		 VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
	     vfm.setPadding(10, 10, 10, 10);
	     add(vfm);
	     
		 enterEmail = new EmailAddressEditField("Email address: ", "");
	     vfm.add(enterEmail);
	     
	     ButtonField loginButton = new ButtonField( "Submit", ButtonField.CONSUME_CLICK | ButtonField.FIELD_HCENTER );
         vfm.add( loginButton );
         loginButton.setChangeListener( new FieldChangeListener() {
            public void fieldChanged( Field arg0, int arg1 ) {
            	try {
					postViaHttpConnection("https://beta.shukranrewards.com/mobile/ForgotPassword",enterEmail.getText().toString());
				} catch (IOException e) {
					Dialog.alert("error Json: "+e.getMessage().toString());
					// TODO Auto-generated catch block
				}
            	//Dialog.inform("Email sent successfully");
            }
         });
	}

	void postViaHttpConnection(String url, String email) throws IOException {
		StringBuffer postData = new StringBuffer();

        httpConn = (HttpConnection) Connector.open(url);
        httpConn.setRequestMethod(HttpConnection.POST);

        postData.append("?emailorcard="+email);
        postData.append("lang=en");
        postData.append(";deviceside=true");
        
        String encodedData = postData.toString();

        httpConn.setRequestProperty("Content-Language", "en-US");
        httpConn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
        httpConn.setRequestProperty("Content-Length",(new Integer(encodedData.length())).toString());
        byte[] postDataByte = postData.toString().getBytes("UTF-8");

        OutputStream out = httpConn.openOutputStream(); 
        out.write(postDataByte);
        out.close();

        httpConn.getResponseCode();

        is = httpConn.openInputStream(); 

        StringBuffer buffer = new StringBuffer();
        int ch = 0;
        
        while (ch != -1) {
            ch = is.read();
            buffer.append((char) ch);
        }

        String json = buffer.toString();

        Dialog.alert("Received Json: "+json);


    }

}
