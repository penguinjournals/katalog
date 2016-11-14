package net.programania;

class GitChange{
	private String uniqueIdentifier;
	private String message;
	
	GitChange(String uniqueIdentifier, String message){
		this.uniqueIdentifier = uniqueIdentifier;
		this.message = message;
	}

	public String changeUniqueIdentifier() {
		return uniqueIdentifier;
	}

	public String changeMessage() {
		return message;
	}

}
