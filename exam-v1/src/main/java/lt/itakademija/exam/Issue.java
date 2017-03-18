package lt.itakademija.exam;

import java.util.Objects;

public final class Issue {

    private final Project project;

    private final String summary;

    public Issue(Project project, String summary) {
        this.project = project;
        this.summary = summary;
    }

    public Project getProject() {
        return project;
    }

    public String getSummary() {
        return summary;
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, summary);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Issue other = (Issue) obj;
        if (project == null) {
            if (other.project != null)
                return false;
        } else if (!project.equals(other.project))
            return false;
        if (summary == null) {
            if (other.summary != null)
                return false;
        } else if (!summary.equals(other.summary))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Issue [project=" + project + ", summary=" + summary + "]";
    }

}
