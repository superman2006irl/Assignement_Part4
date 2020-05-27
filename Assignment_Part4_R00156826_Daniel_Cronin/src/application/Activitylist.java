/**
 * 
 */
package application;

import java.util.ArrayList;

/**
 * @author Daniel
 *
 */
public class Activitylist {
	
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
	

