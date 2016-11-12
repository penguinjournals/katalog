package net.programania;

import static org.junit.Assert.*;

import java.util.List;
import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.CheckoutConflictException;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.InvalidRefNameException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.api.errors.RefAlreadyExistsException;
import org.eclipse.jgit.api.errors.RefNotFoundException;
import org.junit.Test;

public class GitCrawlerTest {
	
	@Test
	public void testCommitMessage() throws IllegalStateException, IOException, GitAPIException {
		// Bootstrap fake repo
		Git fakeRepo = initializeRepo();
		String repoPath = fakeRepo.getRepository().getDirectory().getParent();
		int fakeCommitAmount = 8;
		generateFakeCommits(fakeRepo, fakeCommitAmount);
		// Begin of testing
		GitCrawler repoUnderTesting = new GitCrawler(repoPath);
		List<String> changelogBetweenMasterAndDevelop = repoUnderTesting.changelogBetweenTwoBranches("master", "develop");
		assertEquals(changelogBetweenMasterAndDevelop.size(), fakeCommitAmount);
	}

	private void generateFakeCommits(Git fakeRepo, int fakeCommitAmount) throws IOException, NoFilepatternException, GitAPIException,
			RefAlreadyExistsException, RefNotFoundException, InvalidRefNameException, CheckoutConflictException {
		for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
			String fakeMessage = "[SC-000"+String.valueOf(dummyFileNumber)+"]release_1_commit_"+String.valueOf(dummyFileNumber);
			insertCommitMessage(fakeRepo, fakeMessage);	
		}
		fakeRepo.branchCreate().setName("develop").call();
		fakeRepo.checkout().setName("develop").call();
		for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
			String fakeMessage = "[SC-001"+String.valueOf(dummyFileNumber)+"]release_2_commit_"+String.valueOf(dummyFileNumber);
			insertCommitMessage(fakeRepo, fakeMessage);	
		}
	}
	
	private void insertCommitMessage(Git git, String commitMessage) throws IOException, NoFilepatternException, GitAPIException {
        git.commit().setMessage(commitMessage).call();
	}

	private Git initializeRepo() throws IOException, IllegalStateException, GitAPIException {
		File dir = File.createTempFile("TestGitRepository", "");
		if(!dir.delete()) {
		    throw new IOException("Could not delete file " + dir);
		}
		try (Git git = Git.init().setDirectory(dir).call()) {
			System.out.println(dir);
			return git;
		}
	}

}
