package net.programania;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
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

}
