package net.programania;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitCrawler{
	private Git git;
	
	public GitCrawler(String pathToRepository) throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		File repoDir = new File(pathToRepository+"/.git");
		try (Repository repository = builder.setGitDir(repoDir).readEnvironment().findGitDir().build()) {
            git = new Git(repository);
		}		
	}

	private String latestCommitHashOnBranch(String branchName) throws RevisionSyntaxException, NoHeadException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException {
        Iterable<RevCommit> commits = git.log().add(git.getRepository().resolve(branchName)).call();
        RevCommit latestCommit = null;
        latestCommit = commits.iterator().next();
		return latestCommit.getName();
	}

	public List<gitChange> changelogBetweenTwoBranches(String branchA, String branchB) throws RevisionSyntaxException, NoHeadException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException {
		// TODO Auto-generated method stub
		List<gitChange> changelog = new ArrayList<gitChange>();
		String latestCommitHashOnBranchA = latestCommitHashOnBranch(branchA);
		String latestCommitHashOnBranchB = latestCommitHashOnBranch(branchB);
		try (RevWalk walk = new RevWalk(git.getRepository())) {
			RevCommit commit = walk.parseCommit(git.getRepository().resolve(latestCommitHashOnBranchB));
			walk.markStart(commit);
			for(RevCommit rev: walk){
				if(rev.getId().getName().equals(latestCommitHashOnBranchA)){
					break;
				}
				gitChange currentChange = new gitChange(rev.name(), rev.getFullMessage());
				changelog.add(currentChange);
			}
			walk.dispose();
		}
		return changelog;
	}	
}
