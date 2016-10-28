package net.programania;

import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;

public class GitCrawler {

	public static void main(String[] args) throws GitAPIException, GitAPIException {
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
