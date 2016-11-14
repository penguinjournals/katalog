package net.programania;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GitCrawlerTest {
  private static final GitCrawlerHelper GIT_HELPER = GitCrawlerHelper.initializeRepo();
  private static final int FAKE_COMMIT_AMOUNT = 8;
  private static final GitCrawler GIT_CRAWLER = new GitCrawler(GIT_HELPER.getParent());

  @Test
  public void testCommitMessage() throws IllegalStateException, IOException, GitAPIException {

    //GIVEN
    GIT_HELPER.generateFakeCommits(FAKE_COMMIT_AMOUNT);

    //WHEN
    List<GitChange> changelogBetweenMasterAndDevelop = GIT_CRAWLER.changelogBetweenTwoBranches(
            GitCrawlerHelper.MASTER,
            GitCrawlerHelper.DEVELOP
    );

    //THEN
    assertEquals(changelogBetweenMasterAndDevelop.size(), FAKE_COMMIT_AMOUNT);
  }
}