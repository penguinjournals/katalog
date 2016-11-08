package net.programania;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.errors.AmbiguousObjectException;
import org.eclipse.jgit.errors.IncorrectObjectTypeException;
import org.eclipse.jgit.errors.MissingObjectException;
import org.eclipse.jgit.errors.RevisionSyntaxException;
import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitCrawler {
	private Git git;
	
	public GitCrawler(String pathToRepository) throws IOException {
		FileRepositoryBuilder builder = new FileRepositoryBuilder();
		File repoDir = new File(pathToRepository+"/.git");
		try (Repository repository = builder.setGitDir(repoDir).readEnvironment().findGitDir().build()) {
            git = new Git(repository);
		}		
	}

	public Iterable<RevCommit> getCommitLog() throws IOException, NoHeadException, GitAPIException {
		return git.log().call();
	}
	
	public void PrintAllBranches() throws GitAPIException {
		List<Ref> branches = git.branchList().call();
		for (Ref ref : branches) {
			System.out.println(ref.getName());
		}
	}

	public String latestCommitOnBranch(String branchName) throws RevisionSyntaxException, NoHeadException, MissingObjectException, IncorrectObjectTypeException, AmbiguousObjectException, GitAPIException, IOException {
        Iterable<RevCommit> commits = git.log().add(git.getRepository().resolve(branchName)).call();
        RevCommit latestCommit = null;
        latestCommit = commits.iterator().next();
		return latestCommit.getName();
	}

}
