package com.company.jmix.task1.screen.connectedusers;

import io.jmix.ui.component.JavaScriptComponent;
import io.jmix.ui.component.TextArea;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("test_ConnectedUsers")
@UiDescriptor("connected-users.xml")
public class ConnectedUsers extends Screen {

    @Autowired
    private TextArea message;
    @Autowired
    private JavaScriptComponent websocket;



    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        websocket.addFunction("addMessage", javaScriptCallbackEvent -> {
            if( message.getValue() != null) {
                message.setValue((message.getValue() + "\n" + javaScriptCallbackEvent.getArguments().getString(0)));
            }else{
                message.setValue(("\n" + javaScriptCallbackEvent.getArguments().getString(0)));
            }
        });
    }
}