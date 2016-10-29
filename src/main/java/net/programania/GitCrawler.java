package net.programania;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoHeadException;
import org.eclipse.jgit.internal.storage.file.FileRepository;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitCrawler {

	public static void main(String[] args) throws GitAPIException, GitAPIException, IOException {
		File dir = File.createTempFile("TestGitRepository", "");
        if(!dir.delete()) {
            throw new IOException("Could not delete file " + dir);
        }
        try (Git git = Git.init().setDirectory(dir).call()) {
        	System.out.println(git.getRepository().getDirectory().getParent());
        	for (int dummyFileNumber = 1; dummyFileNumber <= 8; dummyFileNumber++) {
	            File dummyFile = new File(git.getRepository().getDirectory().getParent()+"/dummyfile"+String.valueOf(dummyFileNumber));
	            if(!dummyFile.createNewFile()) {
	                throw new IOException("Could not create file " + dummyFile);
	            }
	            git.add().addFilepattern("dummyfile"+String.valueOf(dummyFileNumber)).call();                
	            git.commit().setMessage("Dummy commit "+String.valueOf(dummyFileNumber)).call();
        	}
        }
	}

	private static void patternFinder() throws GitAPIException, NoHeadException {
		try {
			String pattern = ".*[E|e]xercise.*";
			Pattern patternMatcher = Pattern.compile(pattern, Pattern.MULTILINE);
			Repository repo = new FileRepositoryBuilder().setGitDir(new File(".git")).build();
			Git git = new Git(repo);
			Iterable<RevCommit> log = git.log().call();
			for (RevCommit revCommit : log) {
				if (patternMatcher.matcher(revCommit.getFullMessage()).find()) {
					System.out.println(revCommit.getFullMessage());
				}
			}
			git.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
