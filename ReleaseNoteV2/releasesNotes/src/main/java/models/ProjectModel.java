package models;

import java.util.ArrayList;
import java.util.List;

public class ProjectModel {

	private String name;
	private String version;
	List<IssueModel> issues;

	public ProjectModel() {
		issues = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;

	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;

	}

	public List<IssueModel> getIssues() {
		return issues;
	}

	public void setIssues(List<IssueModel> issues) {
		this.issues = issues;
	}

}
