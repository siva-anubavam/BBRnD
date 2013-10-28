package mypackage;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class AboutUs extends MainScreen {

	/**
	 * 
	 */
	public AboutUs() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		
		VerticalFieldManager vfm = new VerticalFieldManager(Manager.VERTICAL_SCROLL);
        vfm.setPadding(10, 10, 10, 10);
        add(vfm);
        
        final CustomEditField ef = new CustomEditField("Destination");
		
        vfm.add(ef);
		
		final CustomEditField ef1 = new CustomEditField("Distance from your city");
		
		vfm.add(ef1);
	}
}
