package mypackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

import org.json.me.JSONException;
import org.json.me.JSONObject;

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
            		httpConnectionPostMethod("https://www.beta.shukranrewards.com/mobile/ForgotPassword;interface=wifi",enterEmail.getText().toString());
				} catch (IOException e) {
					Dialog.alert("error Json: "+e.getMessage().toString());
					// TODO Auto-generated catch block
				}
            }
         });
	}
	
	public void httpConnectionPostMethod(String url, String email) throws IOException {

	    HttpConnection httpConn = null;
	    InputStream is = null;
	    OutputStream os = null;

	    try {
	        
	      // Open an HTTP Connection object
	      httpConn = (HttpConnection)Connector.open(url);
	      // Setup HTTP Request to POST
	      httpConn.setRequestMethod(HttpConnection.POST);

	      httpConn.setRequestProperty("User-Agent", "BlackBerry");
	      httpConn.setRequestProperty("Accept_Language","en-US");
	      //Content-Type is must to pass parameters in POST Request
	      httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

	      // This function retrieves the information of this connection
	      // getConnectionInformation(httpConn);

	      os = httpConn.openOutputStream();
	     

	      String params;
	      params = "lang=en&emailorcard=" + email;

	      os.write(params.getBytes());

	      /**Caution: os.flush() is controversial. It may create unexpected behavior
	            on certain mobile devices. Try it out for your mobile device **/

	      //os.flush();

	      // Read Response from the Server

	      StringBuffer sb = new StringBuffer();
	      is = httpConn.openDataInputStream();
	      int chr;
	      while ((chr = is.read()) != -1)
	        sb.append((char) chr);

	      // Web Server just returns the birthday in mm/dd/yy format.
	      //Dialog.alert(email+"'s Birthday is " + sb.toString());
	      JSONObject jsonObject;
		  try {
			  jsonObject = new JSONObject(sb.toString());
			  Dialog.alert("Message " + jsonObject.getString("message"));
		  } catch (JSONException e) {
		      // TODO Auto-generated catch block
			  e.printStackTrace();
		  }
	   } finally {
	        if(is!= null)
	           is.close();
	          if(os != null)
	            os.close();
	      if(httpConn != null)
	            httpConn.close();
	    }
	}
}
