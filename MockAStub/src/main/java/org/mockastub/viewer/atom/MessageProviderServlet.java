/**
 * 
 */
package org.mockastub.viewer.atom;

import org.apache.abdera.protocol.server.Provider;
import org.apache.abdera.protocol.server.impl.DefaultProvider;
import org.apache.abdera.protocol.server.impl.SimpleWorkspaceInfo;
import org.apache.abdera.protocol.server.servlet.AbderaServlet;


/**
 * @author claus
 *
 */
public class MessageProviderServlet extends AbderaServlet {

	/**
	 * 
	 */
    protected Provider createProvider() {
         MessageInboundCollectionAdapter ca = new MessageInboundCollectionAdapter();
         ca.setHref("message");

         SimpleWorkspaceInfo wi = new SimpleWorkspaceInfo();
         wi.setTitle("Stub message Directory Workspace");
         wi.addCollection(ca);

         DefaultProvider provider = new DefaultProvider("/");
         provider.addWorkspace(wi);

         provider.init(getAbdera(), null);
         return provider;
     }
}
