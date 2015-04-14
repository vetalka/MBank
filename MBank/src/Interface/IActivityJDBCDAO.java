package Interface;

import java.util.ArrayList;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;
import mainClasses.Activity;

public interface IActivityJDBCDAO {

	public abstract void addFirstActivity(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void CrateNewDeposit(Activity activityBean) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void ViewActivitiesDetails(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public ArrayList<Activity> ViewAllActivitiesDetails() throws CheckedExceptions, UncheckedRuntimeException;

	public Activity getActivityBean(long id) throws CheckedExceptions, UncheckedRuntimeException;
	
	public ArrayList<Activity> ViewActivityDetailsByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void removeActivityByClientId(long clientId) throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void addActivity(Activity activityBean, long clientId) throws CheckedExceptions, UncheckedRuntimeException;

}
