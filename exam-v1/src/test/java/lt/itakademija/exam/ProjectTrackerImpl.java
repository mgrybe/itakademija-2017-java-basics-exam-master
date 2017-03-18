package lt.itakademija.exam;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

public class ProjectTrackerImpl implements ProjectManager {

    private Collection<Project> projects = new LinkedList<>();

    @Override
    public Project createProject(String id, String summary) {
        Objects.requireNonNull(id);
        Objects.requireNonNull(summary);
        if (id.isEmpty() || summary.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Project project = new Project(id, summary);
        this.projects.add(project);
        return project;
    }

    @Override
    public Project getProjectById(String id) {
        Project foundProject = null;
        for (Project project : projects) {
            if (project.getId().equals(id)) {
                foundProject = project;
                break;
            }
        }
        return foundProject;
    }

    @Override
    public Collection<Project> getProjects() {
        return Collections.unmodifiableCollection(projects);
    }

    @Override
    public Issue createIssue(Project project, String summary) {
        Objects.requireNonNull(project);
        Objects.requireNonNull(summary);
        if (summary.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Issue issue = new Issue(project, summary);

        project.addIssue(issue);

        return issue;
    }

    @Override
    public Issue createIssue(String projectId, String summary) {
        Objects.requireNonNull(projectId);
        Objects.requireNonNull(summary);

        if (projectId.isEmpty() || summary.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return createIssue(getProjectById(projectId), summary);
    }

    @Override
    public Collection<Issue> getIssues(Project project) {
        return project.getIssues();
    }

    @Override
    public Collection<Issue> getIssues(String projectId) {
        return getIssues(getProjectById(projectId));
    }

}
