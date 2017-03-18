package lt.itakademija.exam;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public final class Project {

    private final String id;

    private final String summary;

    private final List<Issue> issues;

    public Project(String id, String summary) {
        this.id = id;
        this.summary = summary;
        this.issues = new LinkedList<>();
    }

    public String getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public void addIssue(Issue issue) {
        this.issues.add(issue);
    }

    public List<Issue> getIssues() {
        return issues;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Project [id=" + id + ", summary=" + summary + ", issues=" + issues + "]";
    }

}
