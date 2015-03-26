package nl.inbergict.releaser;

import org.tmatesoft.svn.core.SVNURL;

public class Repository {
	
	private SVNURL svnURL;
	private long latestRevision;
	private String repoUuid;
	
	public Repository(SVNURL svnURL, String repoUuid, long latestRevision){
		this.svnURL = svnURL;
		this.latestRevision = latestRevision;
		this.repoUuid = repoUuid;
	}

	public SVNURL getSvnURL() {
		return svnURL;
	}

	public void setSvnURL(SVNURL svnURL) {
		this.svnURL = svnURL;
	}

	public long getLatestRevision() {
		return latestRevision;
	}

	public void setLatestRevision(long latestRevision) {
		this.latestRevision = latestRevision;
	}

	public String getRepoUuid() {
		return repoUuid;
	}

	public void setRepoUuid(String repoUuid) {
		this.repoUuid = repoUuid;
	}	
	

}
