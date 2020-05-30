/**
 * 
 */
package Lists;

import java.util.ArrayList;

import application.Activity;

/**
 * @author Daniel
 *
 */

//structure of the arraylist and function to add to list
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
	

