/**
 * 
 */
package application;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Daniel
 *
 */
@SuppressWarnings("serial")
public class Activitylist implements Serializable {
	
	private ArrayList<Activity> activitylist;
	
	public Activitylist() {
		
		activitylist = new ArrayList<Activity>();
	}

	public ArrayList<Activity> getActivitylist() {
		return activitylist;
	}
	
	
	public void add(Activity a ) {
		this.activitylist.add(a);
	}
	
}
	

