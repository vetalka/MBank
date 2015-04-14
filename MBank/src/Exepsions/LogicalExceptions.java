package Exepsions;

@SuppressWarnings("serial")
public class LogicalExceptions extends Exception {
	
	private String exceptionMsg;

    public LogicalExceptions(String exceptionMsg) {
            this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
            return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
            this.exceptionMsg = exceptionMsg;
    }

}
