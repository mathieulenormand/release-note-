package exception;

public class ConfigurationException extends Exception{
	
private static final long serialVersionUID = 1L;
	
	private String message;
	
	public ConfigurationException(String string) {
		this.message = string;
	}

	public String getMessage() {
		return this.message;
	}

}
