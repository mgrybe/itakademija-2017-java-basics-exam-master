package lt.itakademija.exam;

import java.util.Collection;

/**
 * Manages projects and issues.
 */
public interface ProjectManager {

    /**
     * Creates project and returns it.
     *
     * @param id      project identifier
     * @param summary project summary (simple description)
     * @return a created project
     */
    Project createProject(String id, String summary);

    /**
     * Returns all created projects.
     *
     * @return projects
     */
    Collection<Project> getProjects();

    /**
     * Returns project by its id.
     *
     * @param id project identifier
     * @return a project
     */
    Project getProjectById(String id);

    /**
     * Creates issue and returns it.
     *
     * @param project project
     * @param summary issue summary (simple description)
     * @return a created issue
     */
    Issue createIssue(Project project, String summary);

    /**
     * Creates issue and returns it.
     *
     * @param projectId project identifier
     * @param summary   issue summary (simple description)
     * @return an issue
     */
    Issue createIssue(String projectId, String summary);

    /**
     * Returns all created issues in the project.
     *
     * @param project project
     * @return issues
     */
    Collection<Issue> getIssues(Project project);

    /**
     * Returns all created issues in the project.
     *
     * @param projectId project identifier
     * @return issues
     */
    Collection<Issue> getIssues(String projectId);

}
