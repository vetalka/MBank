package Exepsions;

@SuppressWarnings("serial")
public class UncheckedRuntimeException extends Exception{

	
	private String exceptionMsg;

    public UncheckedRuntimeException(String exceptionMsg) {
            this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
            return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
            this.exceptionMsg = exceptionMsg;
    }
	

}
