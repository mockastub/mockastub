/**
 * 
 */
package org.mockastub.viewer.atom;

import java.util.Date;

import org.apache.abdera.Abdera;
import org.apache.abdera.model.Document;
import org.apache.abdera.model.Entry;
import org.apache.abdera.model.Feed;
import org.apache.abdera.protocol.Response.ResponseType;
import org.apache.abdera.protocol.client.AbderaClient;
import org.apache.abdera.protocol.client.ClientResponse;

/**
 * @author claus
 * This class is intended to interface the Atom server.
 */
public class Client {
	private AbderaClient client;
	private Abdera abdera;
	/**
	 * 
	 */
	public Client() {
		abdera = new Abdera();
		client = new AbderaClient(abdera);	
		}
	public ClientResponse post(String id, String title, String payload){
		 
		Entry entry = abdera.newEntry();       
		entry.setId(id);
		entry.setTitle(title);
		entry.setUpdated(new Date());
		entry.setContent(payload);
		 
		ClientResponse resp = client.post("http://localhost:8081/viewer",entry);
		 
		if (resp.getType() == ResponseType.SUCCESS) {
		  System.out.println("SUCCES " + entry );
		} else {
			  System.out.println("Failure to post" + entry );
			  System.out.println(resp.getType().toString());
		}
		return resp;
	}
	
	public String get(){
		ClientResponse resp = client.get("http://localhost:8081/viewer");
		if (resp.getType() == ResponseType.SUCCESS) {
		  Document<Feed> doc = resp.getDocument();
		  return doc.toString();
		} else {
			String s = "Error while getting data from atom server.";
		  System.out.println(s);
		  return s;
		}
	}
}
