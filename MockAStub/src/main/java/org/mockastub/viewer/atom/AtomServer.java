/**
 * 
 */
package org.mockastub.viewer.atom;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;

/**
 * @author claus
 *
 */
public class AtomServer {
	private Abdera abdera = null;
	private Feed feed = null;
	public static String HOST = "localhost";
	public static String FEED = "mockastub";
	public static String FEED_SUBTITLE = "subtitle";
	public AtomServer(){
		setUp();
	}
	
	public String getLink(){
		return "http://"+HOST+":/"+feed;
	}
	
	public void setUp (){
	  feed = abdera.newFeed();	   
	  feed.setId("tag:example.org,2007:/foo");
	  feed.setTitle("Test Feed");
	  feed.setSubtitle("Feed subtitle");
	  feed.setUpdated(new Date());
	  feed.addAuthor("James Snell");
	  feed.addLink("http://example.com");
	  feed.addLink("http://example.com/foo","self");
	}
	
	public void addEntry(){
	  Entry entry = feed.addEntry();
	  entry.setId("tag:example.org,2007:/foo/entries/1");
	  entry.setTitle("Entry title");
	  entry.setSummaryAsHtml("<p>This is the entry title</p>");
	  entry.setUpdated(new Date());
	  entry.setPublished(new Date());
	  entry.addLink("http://example.com/foo/entries/1");
	}
}
