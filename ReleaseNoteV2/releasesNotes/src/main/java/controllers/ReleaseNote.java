package controllers;

import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.api.domain.Version;
import com.atlassian.util.concurrent.Promise;

import exception.ConfigurationException;
import models.IssueModel;
import models.ProjectModel;
import views.JsonReleaseNote;
import views.Pdf;

public class ReleaseNote extends AbstractControler {

	private ProjectModel project;

	public ReleaseNote(String projectName, String projectVersion) {
		project = new ProjectModel();
		project.setName(projectName);
		project.setVersion(projectVersion);
	}

	@Override
	public void execute() throws ConfigurationException {
		super.execute();
		try {
			JiraRestClient client = getClient(config.getUrl(), config.getUsername(), config.getPassword());
			// VersionRestClient versionClient = client.getVersionRestClient();
			// versionClient.getVersion("???")
			Promise<SearchResult> searchJqlPromise = client.getSearchClient()
					.searchJql("Project = " + project.getName());
			System.out.println("projet : " + " " + project.getName());
			for (Issue issue : searchJqlPromise.claim().getIssues()) {
				for (Version version : issue.getFixVersions()) {
					if (project.getVersion().equals(version.getName())) {
						IssueModel issueModel = new IssueModel();
						issueModel.setState(issue.getStatus().getName());
						issueModel.setType(issue.getIssueType().getName());
						issueModel.setDescription(issue.getDescription());
						issueModel.setPriority(issue.getPriority().getName());
						issueModel.setIssue(issue.getSummary());
						project.getIssues().add(issueModel);

						// System.err.println("key : " + " " + issue.getKey());
						System.out.println("issue : " + " " + issue.getSummary());
						System.out.println("issue status : " + " " + issue.getStatus().getName());
						System.out.println("issue state : " + " " + issue.getIssueType().getName());
						System.out.println("issue description : " + " " + issue.getDescription());
						System.out.println("issue priority : " + " " + issue.getPriority().getName());

					}
				}
			}
			if (project.getIssues().size() == 0) {
				throw new RestClientException("no issues for this version", null);
			}

			// view.setModel(project);
			// System.out.println(project.getName());
			// view.render();

			Pdf pdf = new Pdf();
			pdf.setModel(project);
			pdf.render();

			// System.out.println("Format JSON");
			JsonReleaseNote json = new JsonReleaseNote();
			json.setProject(project);
			// json.json();

		} catch (URISyntaxException e) {
			throw new ConfigurationException(e.getMessage());
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new ConfigurationException("HTTP error " + e.getStatusCode().get());
		}
	}

}