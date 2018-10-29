//package model;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import com.atlassian.jira.rest.client.api.JiraRestClient;
//import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
//import com.atlassian.jira.rest.client.api.domain.Issue;
//import com.atlassian.jira.rest.client.api.domain.IssueType;
//import com.atlassian.jira.rest.client.api.domain.SearchResult;
//import com.atlassian.jira.rest.client.api.domain.Version;
//import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
//import com.atlassian.util.concurrent.Promise;
//import com.itextpdf.text.Document;
//import com.itextpdf.text.DocumentException;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//
//import configuration.Configuration;
//import configuration.ConfigurationExeception;
//
//public class ReleaseNote {
//	
//	private Configuration confProperties;
//	
//	private String project;
//	private double version;
//	private String issue;
//	private String commentaire;
//
//    public ReleaseNote(String project) throws URISyntaxException, ConfigurationExeception {
//
//		this.confProperties = new Configuration();
//		this.project= this.jrProject(project);
//		this.version = version;
//		this.issue= issue;
//		this.commentaire=commentaire;
//	}
//    
//	public String getProject() {
//		return project;
//	}
//	
//	public double getVersion() {
//		return version;
//	}
//
//	public String getIssue() {
//		return issue;
//	}
//
//	public String jrProject (String project) throws URISyntaxException {
//		final JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//		  final URI uri = new URI(confProperties.getUrl());
//	      final JiraRestClient client = factory.createWithBasicHttpAuthentication(uri, confProperties.getUsername(), confProperties.getPassword() );
//	      System.out.println("project" + " " + project);
//	      Promise<SearchResult> searchJqlPromise = client.getSearchClient().searchJql("project ="+project);
//	      
//	        for (Issue issue : searchJqlPromise.claim().getIssues()) {
//	            System.out.println(issue.getSummary());
//
//		     
//		            System.out.println(issue.getIssueType().getName());
//		        
//		        for (Version version : issue.getFixVersions()) {
//
//		            System.out.println(version.getName());
//		        	
//		        }
//	        }
//	     return project;
//	}
//	
//	public void generate() {
//		Document document = new Document();
//		
//		  try {
//	          PdfWriter.getInstance(document,
//	              new FileOutputStream("ReleasesNotes.pdf"));
//
//	          document.open();
//	          document.add(new Paragraph(this.getProject()));
//	          document.close(); // no need to close PDFwriter?
//
//	      } catch (DocumentException e) {
//	          e.printStackTrace();
//	      } catch (FileNotFoundException e) {
//	          e.printStackTrace();
//	      }
//		}
//}
