package CustomException;

public class InvalidChoiceException extends RuntimeException{
	
	private String message;
	
	public InvalidChoiceException(String meaasge) {
		this.message=meaasge;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}