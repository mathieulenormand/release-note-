package controllers;

import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;

import exception.ConfigurationException;
import views.IView;
import views.Pdf;

abstract public class AbstractControler implements IControler {

	protected IView view;

	protected Configuration config;

	protected Pdf pdf;

	protected JiraRestClient getClient(String endpoint, String username, String password) throws URISyntaxException {
		return new AsynchronousJiraRestClientFactory().createWithBasicHttpAuthentication(new URI(endpoint), username,
				password);
	}

	protected void setView(IView view) {
		this.view = view;
	}

	protected void setConfig(Configuration config) {
		this.config = config;
	}

	public void setPdf(Pdf pdf) {
		this.pdf = pdf;
	}

	@Override
	public void execute() throws ConfigurationException {
		if (config == null) {
			config = new Configuration();
		}
		if (pdf == null) {
			pdf = new Pdf();
		}
	}

}
