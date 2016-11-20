package net.programania;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.*;

import java.io.File;
import java.io.IOException;

class GitCrawlerHelper {
  static final String DEVELOP = "develop";
  static final String MASTER = "master";
  private final Git fakeRepo;

  private GitCrawlerHelper(Git git) {
    this.fakeRepo = git;
  }

  String getParent() {
    return fakeRepo.getRepository().getDirectory().getParent();
  }

  void generateFakeCommits(int fakeCommitAmount) throws IOException, GitAPIException {
    for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
      String fakeMessage = "[SC-1000" + String.valueOf(dummyFileNumber) + "]release_1_commit_" + String.valueOf(dummyFileNumber);
      insertCommitMessage(fakeRepo, fakeMessage);
    }
    fakeRepo.branchCreate().setName(DEVELOP).call();
    fakeRepo.checkout().setName(DEVELOP).call();
    for (int dummyFileNumber = 1; dummyFileNumber <= fakeCommitAmount; dummyFileNumber++) {
      String fakeMessage = "[SC-1001" + String.valueOf(dummyFileNumber) + "]release_2_commit_" + String.valueOf(dummyFileNumber);
      insertCommitMessage(fakeRepo, fakeMessage);
    }
  }

  private void insertCommitMessage(Git git, String commitMessage) throws IOException, GitAPIException {
    git.commit().setMessage(commitMessage).call();
  }

  static GitCrawlerHelper initializeRepo() {
    try {
      File dir = File.createTempFile("TestGitRepository", "");
      if (!dir.delete()) {
        throw new IOException("Could not delete file " + dir);
      }
      try (Git git = Git.init().setDirectory(dir).call()) {
        return new GitCrawlerHelper(git);
      }
    } catch (GitAPIException | IOException e) {
      throw new RuntimeException(e);
    }
  }
}