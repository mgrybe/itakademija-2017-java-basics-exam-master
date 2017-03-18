package lt.itakademija.pavelas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import lt.itakademija.exam.Issue;
import lt.itakademija.exam.Project;
import lt.itakademija.exam.ProjectManager;
/**
 * 
 * @author pavelas
 *Si klase implementuoja egzamine gauta jar
 *Egzamino versija 1 
 */
public class ProjectManagerImplementation implements ProjectManager {

	private List<Project> prList = new ArrayList<Project>();
/**
 * Metodas sukuria Issue pagal projekto objekta
 */
	@Override
	public Issue createIssue(Project project, String summary) {
		if (project.equals(null)) {
			throw new NullPointerException();
		}
		if (summary.isEmpty()) {

			throw new IllegalArgumentException();
		}
		int listSize = prList.size();
		int temp = 0;
		while (temp < listSize) {
			if (prList.get(temp).equals(project)) {
				prList.get(temp).addIssue(new Issue(project, summary));
			}
			++temp;
		}
		return new Issue(project, summary);

	}
/**
 * Metodas sukuria Issue pagal projekto id ir summary
 */
	@Override
	public Issue createIssue(String projectId, String summary) {
		if (projectId.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (summary.isEmpty()) {
			throw new IllegalArgumentException();
		}
		Project project = new Project(projectId, summary);
		Issue issue = new Issue(project, summary);
		return issue;
	}
/**
 * Metodas sukuria projekta pagal id ir summary
 */
	@Override
	public Project createProject(String id, String summary) {
		if (id.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (summary.isEmpty()) {
			throw new IllegalArgumentException();
		}

		prList.add(new Project(id, summary));
		return new Project(id, summary);
	}
/**
 * Metodas gauna projekto issu sarasa
 */
	@Override
	public Collection<Issue> getIssues(Project project) {		
		int listSize = prList.size();
		int temp = 0;
		while (temp < listSize) {
			Project tempProject = prList.get(temp);
			if (tempProject.equals(project)) {
				return tempProject.getIssues();
			}
			++temp;
		}
		return project.getIssues();
	}
/**
 * Metodas grazina issue pagal projekto id
 */
	@Override
	public Collection<Issue> getIssues(String projectId) {
		int listSize = prList.size();
		int temp = 0;
		while (temp < listSize) {
			Project project = prList.get(temp);
			if (project.getId().equals(projectId)) {
				return project.getIssues();
			}
			++temp;
		}
		return null;
	}
/**
 * Metodas grazina projekta is saraso pagal id, jei neranda grazina null
 */
	@Override
	public Project getProjectById(String id) {
		int listSize = prList.size();
		int temp = 0;
		while (temp < listSize) {
			Project project = prList.get(temp);
			if (project.getId().equals(id)) {
				return project;
			}
			++temp;
		}
		return null;
	}
/**
 * Metodas grazina visa sukurtu projektu sarasa
 */
	@Override
	public Collection<Project> getProjects() {		
		return prList;
	}

}
