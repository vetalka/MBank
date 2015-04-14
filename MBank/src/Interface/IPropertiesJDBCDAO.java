package Interface;

import java.util.Map;

import Exepsions.CheckedExceptions;
import Exepsions.UncheckedRuntimeException;

public interface IPropertiesJDBCDAO {
	
	public Map<String, String> getProperties() throws CheckedExceptions, UncheckedRuntimeException;

	public abstract void ViewSystemProperty() throws CheckedExceptions, UncheckedRuntimeException;
	
	public abstract void updateSystemProperty(String propKey , String propValue) throws CheckedExceptions, UncheckedRuntimeException;

}
