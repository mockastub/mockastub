/**
 * 
 */
package org.mockastub.stub;

import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.mockastub.viewer.atom.MessageEntry;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author claus Handle storage of messages.
 */
@RestController
public class MessageStore {
    private static Map<String, MessageEntry> messages = new HashMap<String, MessageEntry>();
    private static long nextId = 0;

    public static synchronized String getAndIncrementNextId() {
        String result = Long.toString(nextId++);
        return result;
    }

    public MessageEntry getEntry(String entryId) {
        return messages.get(entryId);
    }

    public Collection<MessageEntry> getEntities() {
        return messages.values();
    }

    @RequestMapping(value = "/view", method = RequestMethod.PUT)
    public MessageEntry put(@RequestParam(value = "title") String title, @RequestParam(value = "summary") String summary,
            @RequestParam(value = "updated") Date updated, @RequestParam(value = "payload") String payload) {
        MessageEntry messageEntry = new MessageEntry();
        messageEntry.setTitle(title);
        messageEntry.setMessage(payload);
        messageEntry.setId(getAndIncrementNextId());
        if (updated == null)
            updated = new Date();
        messageEntry.setUpdated(updated);
        return put(messageEntry);
    }

    public MessageEntry put(MessageEntry messageEntry) {
        messages.put(messageEntry.getId(), messageEntry);
        return messageEntry;
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public Collection<MessageEntry> get() {
        return getEntities()
            .stream()
            .sorted((a,b) -> a.getUpdated().compareTo(b.getUpdated()))
            .collect(Collectors.toList());
    }

    @RequestMapping(value = "/view/{messageId}", method = RequestMethod.POST)
    public void post(@RequestParam(value = "messageId") String messageId, @RequestParam(value = "title") String title,
            @RequestParam(value = "summary") String summary, @RequestParam(value = "updated") Date updated,
            @RequestParam(value = "payload") String payload) {
        MessageEntry entryToUpdate = messages.get(messageId);
        entryToUpdate.setMessage(payload);
        entryToUpdate.setTitle(title);
        entryToUpdate.setUpdated(updated);
    }

    public void post(MessageEntry messageEntry) {
        MessageEntry entryToUpdate = messages.get(messageEntry.getId());
        entryToUpdate.setMessage(messageEntry.getMessage());
        entryToUpdate.setTitle(messageEntry.getTitle());
        entryToUpdate.setUpdated(messageEntry.getUpdated());
    }

    @RequestMapping(value = "/view/{messageId}", method = RequestMethod.DELETE)
    public void delete(String messageId) {
        messages.remove(messageId);
    }
}
