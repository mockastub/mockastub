/**
 * 
 */
package org.mockastub.viewer.atom;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.abdera.i18n.iri.IRI;
import org.apache.abdera.model.Content;
import org.apache.abdera.model.Person;
import org.apache.abdera.protocol.server.RequestContext;
import org.apache.abdera.protocol.server.context.ResponseContextException;
import org.apache.abdera.protocol.server.impl.AbstractEntityCollectionAdapter;;

/**
 * @author claus
 *
 */
public class MessageInboundCollectionAdapter extends AbstractEntityCollectionAdapter<MessageEntry> {
	/**
	 * The collection of recorded messages.
	 */
	private Map<Long, MessageEntry> messages = new HashMap<Long, MessageEntry>();
	private long nextId = 0;
	private long getAndIncrementNextId(){
		return nextId++;
	}
	public String getTitle(RequestContext arg0) {
		return "MockAStub messages";
	}

	@Override
	public void deleteEntry(String id, RequestContext request) throws ResponseContextException {
		messages.remove(Long.parseLong(id));
	}

	@Override
	public Object getContent(MessageEntry messageEntry, RequestContext requestContext) throws ResponseContextException {
		Content content = requestContext.getAbdera().getFactory().newContent(Content.Type.TEXT);
		content.setText(messageEntry.getMessage());
		return content;
	}

	@Override
	public Iterable getEntries(RequestContext arg0) throws ResponseContextException {	
		return messages.keySet();
	}

	@Override
	public MessageEntry getEntry(String entryId, RequestContext arg1) throws ResponseContextException {
		return messages.get(entryId);
	}

	@Override
	public String getId(MessageEntry messageEntry) throws ResponseContextException {
		if (messageEntry != null)
			return Long.toString(messageEntry.getId());
		return null;
	}

	@Override
	public String getName(MessageEntry messageEntry) throws ResponseContextException {
		if (messageEntry!=null)
			return "Name "+messageEntry.getTitle();
		return null;
	}

	@Override
	public String getTitle(MessageEntry messageEntry) throws ResponseContextException {
		if (messageEntry!=null)
			return "Title: " + messageEntry.getTitle();
		return null;
	}

	@Override
	public Date getUpdated(MessageEntry messageEntry) throws ResponseContextException {
		if (messageEntry!=null )
			return  messageEntry.getUpdated();
		return null;
	}

	@Override
	public MessageEntry postEntry(String title, IRI id,String summary, Date updated, List<Person> authors, Content content, RequestContext request)
			throws ResponseContextException {
		MessageEntry messageEntry = new MessageEntry();
		messageEntry.setTitle(content.getText().trim());
		messageEntry.setId(getAndIncrementNextId());
		messages.put(messageEntry.getId(), messageEntry);
		return messageEntry;		
	}

	@Override
	public void putEntry(MessageEntry messageEntry, String title, Date updateTime, List<Person> authors, String Summery, Content content, RequestContext request)
			throws ResponseContextException {
		MessageEntry entryToUpdate = messages.get(messageEntry.getId());
		entryToUpdate.setMessage(messageEntry.getMessage());
		entryToUpdate.setTitle(messageEntry.getTitle());
		entryToUpdate.setUpdated(messageEntry.getUpdated());
	}

	@Override
	public String getAuthor(RequestContext arg0) throws ResponseContextException {	
		return "MockAStub development team";
	}

	@Override
	public String getId(RequestContext arg0) {
		return "tag:mockastub.org,2016:message:feed";
	}

}
