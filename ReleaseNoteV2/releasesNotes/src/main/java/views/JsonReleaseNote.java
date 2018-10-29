package views;

import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import models.IssueModel;
import models.ProjectModel;

public class JsonReleaseNote implements IView {

	private ProjectModel model;

	public void setProject(ProjectModel project) {
		this.model = project;
	}

	public ProjectModel getProject() {
		return model;
	}

	@Override
	@SuppressWarnings("unchecked")
	public void json() {
		// First issue
		JSONObject project = new JSONObject();

		JSONObject projectObject = new JSONObject();
		projectObject.put("Projet", project);

		JSONArray projectList = new JSONArray();
		projectList.add(projectObject);

		try (FileWriter file = new FileWriter("releaseNote.json")) {

			file.write(projectList.toJSONString());
			file.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}

		project.put("projet", model.getName());
		project.put("version", model.getVersion());
		for (IssueModel issues : model.getIssues()) {
			project.put("issueType", issues.getType());
			project.put("issueState", issues.getState());
			project.put("issue", issues.getIssue());
			project.put("description", issues.getDescription());
			project.put("priority", issues.getPriority());

			System.out.println(projectList);

		}

	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}
}
