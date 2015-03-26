package nl.inbergict.releaser;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;


/**
 * Release: main page.
 *
 */
public class App 
{	
	private static List<Repository> repos = new ArrayList<>();
	private static Properties properties = new Properties();
	private static String user;
	private static String pwd;
	private static String url;
	
	public static void main(String[] args) {
		readProperties();
		connect();

		get("/releaser", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();			
			attributes.put("repos", repos);
			attributes.put("message", "This app can be used to make a release for a certain SVN repository");
			return new ModelAndView(attributes, "releaser.ftl");
			}, 
		new FreeMarkerEngine());
		
		post("/submit", (request, response) -> { 
			 Map<String, Object> attributes = new HashMap<>();
			 //TODO get release number
			 attributes.put("message", "The request has been submitted");
			 return new ModelAndView(attributes, "submitted.ftl");
			 }, 
		new FreeMarkerEngine()); 
	}

	private static void connect() {
		SVNRepository repository = null;
        try {
	        repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded(url));
	        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(user, pwd);
	        repository.setAuthenticationManager(authManager);
	        Repository repo = new Repository(repository.getRepositoryRoot(true), repository.getRepositoryUUID(true), repository.getLatestRevision());
	        repos.add(repo);	        
	        System.out.println( "Repository Root: " + repository.getRepositoryRoot(true ));
	        System.out.println(  "Repository UUID: " + repository.getRepositoryUUID(true));
	        System.out.println(  "Repository revision: " + repository.getLatestRevision());
        }
        catch(SVNException e){
    	    System.out.println(e.getMessage());
        }
	}
	
	private static void readProperties(){
		try {
			properties.load(App.class.getResourceAsStream("/config.properties"));
			// get the property value and print it out
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			pwd = properties.getProperty("password");
		} catch (IOException ex) {
			ex.printStackTrace();
			//TODO error page
		}
	}
		
}
