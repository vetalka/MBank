package Exepsions;

@SuppressWarnings("serial")
public class CheckedExceptions extends Exception {

	private String exceptionMsg;

      public CheckedExceptions(String exceptionMsg) {
              this.exceptionMsg = exceptionMsg;
      }

      public String getExceptionMsg() {
              return this.exceptionMsg;
      }

      public void setExceptionMsg(String exceptionMsg) {
              this.exceptionMsg = exceptionMsg;
      }
}
