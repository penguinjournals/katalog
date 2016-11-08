package net.programania;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Test;
import org.mockito.internal.util.collections.Iterables;

public class GitCrawlerTest {
	/**
	 * Que tiene que hacer la clase:
	 * Dado un repo tiene que sacar el commit log entre la rama master y la rama develop.
	 * 1º obtener el hash del latest commit de la rama master
	 * 2º obtener el hash del latest commit de la rama develop
	 * 3º obtener el commit log entre esos dos hashes
	 */
	
	@Test
	public void testCommitMessage() throws IllegalStateException, IOException, GitAPIException {
		Git fakeRepo = initializeRepo();
		int fakeCommitAmount = 8;
		for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
			String fakeMessage = "release_1_commit_"+String.valueOf(dummyFileNumber);
			insertCommitMessage(fakeRepo, fakeMessage);	
		}
		fakeRepo.branchCreate().setName("develop").call();
		fakeRepo.checkout().setName("develop").call();
		for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
			String fakeMessage = "release_2_commit_"+String.valueOf(dummyFileNumber);
			insertCommitMessage(fakeRepo, fakeMessage);	
		}
		GitCrawler gitCrawler = new GitCrawler(fakeRepo.getRepository().getDirectory().getParent());
		String latestCommitOnMaster = gitCrawler.latestCommitOnBranch("master");
		String latestCommitOnDevelop = gitCrawler.latestCommitOnBranch("develop");
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
