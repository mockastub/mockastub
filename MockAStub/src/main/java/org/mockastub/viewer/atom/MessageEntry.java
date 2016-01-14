/**
 * 
 */
package org.mockastub.viewer.atom;

import java.util.Date;

/**
 * @author claus The wrapper for the messages to retrieve.
 *
 */
public class MessageEntry {
	private long id;
	private String title;
	private String message;
	private Date updated;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

}
