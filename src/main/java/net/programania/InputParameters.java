package net.programania;

public class InputParameters {
	private String repoPath;
	private String masterBranch = "master";
	private String releaseBranch = "develop";
	
	public String repoPath(){
		return repoPath;
	}

	public void repoPath(String name) {
		repoPath = name;
	}
	
	public String masterBranch(){
		return masterBranch;
	}
	
	public void masterBranch(String name){
		masterBranch = name;
	}
	
	public String releaseBranch(){
		return releaseBranch;
	}
	
	public void releaseBranch(String name){
		releaseBranch = name;
	}
}
