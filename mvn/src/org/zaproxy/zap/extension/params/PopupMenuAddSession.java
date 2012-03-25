/*
 * Zed Attack Proxy (ZAP) and its related class files.
 * 
 * ZAP is an HTTP/HTTPS proxy for assessing web application security.
 * 
 * Copyright 2010 psiinon@gmail.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0 
 *   
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.zaproxy.zap.extension.params;

import java.awt.Component;

import org.parosproxy.paros.Constant;
import org.parosproxy.paros.extension.ExtensionPopupMenuItem;
import org.parosproxy.paros.network.HtmlParameter;


/**
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class PopupMenuAddSession extends ExtensionPopupMenuItem {

	private static final long serialVersionUID = 1L;

    private ExtensionParams extension;

	/**
     * 
     */
    public PopupMenuAddSession() {
        super();
 		initialize();
    }

    /**
     * @param label
     */
    public PopupMenuAddSession(String label) {
        super(label);
    }

	public void setExtension(ExtensionParams extension) {
		this.extension = extension;
	}

    /**
	 * This method initialises this
	 * 
	 * @return void
	 */
	private void initialize() {
        this.setText(Constant.messages.getString("params.session.add.popup"));
        this.addActionListener(new java.awt.event.ActionListener() { 

        	public void actionPerformed(java.awt.event.ActionEvent e) {
        		
        		extension.addSessionToken();
        	}
        });

			
	}

    public boolean isEnableForComponent(Component invoker) {
        if (invoker.getName() != null && invoker.getName().equals(ParamsPanel.PANEL_NAME)) {
        	
        	HtmlParameterStats item = extension.getParamsPanel().getSelectedParam();
        	// Note that only url and cookie params are currently supported
        	if (item != null) {
        		if (HtmlParameter.Type.cookie.equals(item.getType()) ||
        				HtmlParameter.Type.url.equals(item.getType())) {
        			if (! item.getFlags().contains(HtmlParameter.Flags.session.name())) {
                		this.setEnabled(true);
                		return true;
        			}
        			return false;
        		}
            	this.setEnabled(false);
        		return true;
        	}
        }
        return false;
    }
    
}
