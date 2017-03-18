package lt.itakademija.exam.test;

import lt.itakademija.exam.Issue;
import lt.itakademija.exam.Project;
import lt.itakademija.exam.ProjectManager;
import lt.itakademija.exam.grader.ExamTask;
import lt.itakademija.exam.grader.GradeEvaluatingRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(GradeEvaluatingRunner.class)
public abstract class BaseTest {

    private ProjectManager sut;

    protected abstract ProjectManager createProjectTracker();

    @Before
    public void setUp() {
        this.sut = createProjectTracker();
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void projectTrackerStartsCorrectly() {
        Assert.assertThat(this.sut, notNullValue());
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void projectTrackerRespondsWithNullPointerExceptionWhenCreatingProjectWithNullArgs() {
        try {
            this.sut.createProject(null, "Test summary");
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }

        try {
            this.sut.createProject("PROJ-1", null);
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void projectTrackerRespondsWithIllegalArgumentExceptionWhenCreatingProjectWithEmptyArgs() {
        try {
            this.sut.createProject("", "Test summary");
            Assert.fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            this.sut.createProject("PROJ-1", "");
            Assert.fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void projectTrackerRespondsWithNullPointerExceptionWhenCreatingIssueWithNullArgs() {
        String summary = "Test summary";
        try {
            this.sut.createIssue((Project) null, summary);
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }
        try {
            this.sut.createIssue((String) null, summary);
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }

        try {
            this.sut.createIssue(new Project("PROJ-1", summary), null);
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }
        try {
            this.sut.createIssue("PROJ-1", null);
            Assert.fail("NullPointerException was expected");
        } catch (NullPointerException ignored) {
        }
    }

    @Test
    @ExamTask(grade = 0.1)
    public final void projectTrackerRespondsWithIllegalArgumentExceptionWhenCreatingIssueWithEmptyArgs() {
        String summary = "Test summary";
        try {
            this.sut.createIssue("", summary);
            Assert.fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignored) {
        }

        try {
            this.sut.createIssue(new Project("PROJ-1", summary), "");
            Assert.fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignored) {
        }
        try {
            this.sut.createIssue("PROJ-1", "");
            Assert.fail("IllegalArgumentException was expected");
        } catch (IllegalArgumentException ignored) {
        }
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void projectTrackerCanCreateProjects() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";
        Project project = new Project(projectId, projectSummary);

        // Exercise
        Project createdProject = this.sut.createProject(projectId, projectSummary);

        // Verify
        assertThat(createdProject, is(project));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void projectTrackerCanReturnProjectById() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";
        Project project = new Project(projectId, projectSummary);

        // Exercise
        this.sut.createProject(projectId, projectSummary);
        Project createdProject = this.sut.getProjectById(projectId);

        // Verify
        assertThat(createdProject, is(project));
    }

    @Test
    @ExamTask(grade = 0.3)
    public final void projectTrackerCanReturnAllCreatedProjects() {
        // Setup
        List<Project> projects = new LinkedList<>();
        for (int i = 0; i < random(100); i++) {
            String projectId = "PROJ-" + i;
            String projectSummary = "Test project: " + i;
            projects.add(new Project(projectId, projectSummary));
            this.sut.createProject(projectId, projectSummary);
        }

        // Exercise
        Collection<Project> createdProjects = this.sut.getProjects();

        // Verify
        assertThat(createdProjects, hasItems(projects.toArray(new Project[0])));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void projectTrackerAllowsToCreateIssuesInTheProject() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";
        Project project = new Project(projectId, projectSummary);

        String issueSummary = "Test issue";
        Issue issue = new Issue(project, issueSummary);

        // Exercise
        Project createdProject = this.sut.createProject(projectId, projectSummary);
        Issue createdIssue = this.sut.createIssue(createdProject, issueSummary);

        // Verify
        assertThat(createdIssue, is(issue));
    }

    @Test
    @ExamTask(grade = 0.7)
    public final void projectTrackerAllowsToCreateIssuesInTheProjectByProjectId() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";
        Project project = new Project(projectId, projectSummary);

        String issueSummary = "Test issue";
        Issue issue = new Issue(project, issueSummary);

        // Exercise
        this.sut.createProject(projectId, projectSummary);
        Issue createdIssue = this.sut.createIssue(projectId, issueSummary);

        // Verify
        assertThat(createdIssue, is(issue));
    }

    @Test
    @ExamTask(grade = 0.6)
    public final void projectTrackerAllowsToRetrieveIssuesInTheProject() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";

        List<Issue> issues = new LinkedList<>();
        Project project = this.sut.createProject(projectId, projectSummary);
        for (int i = 0; i < random(100); i++) {
            String issueSummary = "Test project: " + i;
            issues.add(new Issue(project, issueSummary));
            this.sut.createIssue(project, issueSummary);
        }

        // Exercise
        Collection<Issue> createdIssues = this.sut.getIssues(project);

        // Verify
        assertThat(createdIssues, hasItems(issues.toArray(new Issue[0])));
    }

    @Test
    @ExamTask(grade = 0.9)
    public final void projectTrackerAllowsToRetrieveIssuesInTheProjectByProjectId() {
        // Setup
        String projectId = "PROJ-1";
        String projectSummary = "Test project";

        List<Issue> issues = new LinkedList<>();
        Project project = this.sut.createProject(projectId, projectSummary);
        for (int i = 0; i < random(100); i++) {
            String issueSummary = "Test project: " + i;
            issues.add(new Issue(project, issueSummary));
            this.sut.createIssue(project, issueSummary);
        }

        // Exercise
        Collection<Issue> createdIssues = this.sut.getIssues(projectId);

        // Verify
        assertThat(createdIssues, hasItems(issues.toArray(new Issue[0])));
    }

    private int random(int maxValue) {
        return 1 + (int) (Math.random() * maxValue);
    }

}
