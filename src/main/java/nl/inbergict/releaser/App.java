package nl.inbergict.releaser;

import static spark.Spark.get;

import java.util.HashMap;
import java.util.Map;

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
	public static void main(String[] args) {
		get("/hello", (req, res) -> "Hello World");
		
		get("/releaser", (request, response) -> {
			Map<String, Object> attributes = new HashMap<>();
			attributes.put("message", "Hello World!");
			return new ModelAndView(attributes, "releaser.ftl");
			}, 
		new FreeMarkerEngine());
        
        String url = "http://redmine.knmi.nl/svn/wp11-svn";
        String name = "test";
        String password = "test";
                
        SVNRepository repository = null;
        try {
	        repository = SVNRepositoryFactory.create( SVNURL.parseURIDecoded( url ) );
	        ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager( name , password );
	        repository.setAuthenticationManager( authManager );
	        System.out.println( "Repository Root: " + repository.getRepositoryRoot( true ) );
	        System.out.println(  "Repository UUID: " + repository.getRepositoryUUID( true ) );
        }
        catch(SVNException e){
    	    System.out.println(e.getMessage());
        }
	}
}
