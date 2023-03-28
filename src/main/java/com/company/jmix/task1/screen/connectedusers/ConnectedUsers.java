package com.company.jmix.task1.screen.connectedusers;

import io.jmix.core.Messages;
import io.jmix.ui.Notifications;
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
    private Messages messages;
    @Autowired
    private JavaScriptComponent websocket;
    @Autowired
    private Notifications notifications;



    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        websocket.addFunction("addMessage", javaScriptCallbackEvent -> {
            if( message.getValue() != null) {
                message.setValue((message.getValue() + "\n" + javaScriptCallbackEvent.getArguments().getString(0)));
            }else{
                message.setValue(("\n" + javaScriptCallbackEvent.getArguments().getString(0)));
            }
        });

        websocket.addFunction("onErrorConnect", javaScriptCallbackEvent -> notifications.create(Notifications.NotificationType.ERROR)
                .withCaption(messages.getMessage(getClass(), "webSocketError"))
                .withDescription(messages.getMessage(getClass(), "webSocketError.description"))
                .show());
    }
}