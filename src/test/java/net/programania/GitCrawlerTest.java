package net.programania;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.revwalk.RevCommit;
import org.junit.Test;

public class GitCrawlerTest {

	@Test
	public void test() throws IllegalStateException, IOException, GitAPIException {
		Git fakeRepo = initializeRepo();
		String fakeMessage = "cocotero";
		insertCommitMessage(fakeRepo, fakeMessage);
		GitCrawler gitCrawler = new GitCrawler(fakeRepo.getRepository().getDirectory().getParent());
		Iterable<RevCommit> commitLog = gitCrawler.getCommitLog();
		for (RevCommit commitMessage : commitLog) {
			assertEquals(fakeMessage, commitMessage.getFullMessage());
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
			return git;
		}
	}

}
