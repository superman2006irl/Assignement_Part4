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
public class activitylist implements Serializable {
	
	private ArrayList<Activity> activitylist;
	
	public activitylist() {
		
		activitylist = new ArrayList<Activity>();
	}

	public ArrayList<Activity> getActivitylist() {
		return activitylist;
	}
	
	
	public void add(Activity a ) {
		this.activitylist.add(a);
	}
	
}
	

