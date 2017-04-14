package atc.gui.admin.zk.viewmodel;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.util.resource.Labels;

@Slf4j
public class NavbarVM
{
    @Getter
    private String label;
    @Getter
    private String toolTipText;

    @Getter
    @Setter
    private String message;

    // catch message in all active sessions

    /**
     * Show notification in all desktops of all user sessions
     */
    /*
    @Init
    public void abstractInit() {
        EventQueues.lookup("globalMessage", EventQueues.APPLICATION, true).subscribe(event -> {
            if ("onSendMessage".equals(event.getName())) {
                Clients.showNotification(String.valueOf(event.getData()));
            }
        });
    }
*/
    @AfterCompose
    public void postInit()
    {
        StringBuffer message = new StringBuffer();
        StringBuffer toolTip = new StringBuffer();
        org.springframework.security.core.Authentication authentification = org.zkoss.spring.security.SecurityUtil.getAuthentication();
        if (authentification != null)
        {
            message.append(Labels.getLabel("message.welcome")).append(", ").append(authentification.getName());
            toolTip.append(authentification.getName()).append(Labels.getLabel("message.avatar.personalization"));
        }
        else 
        {
            message.append(Labels.getLabel("message.guest"));
            toolTip.append(Labels.getLabel("yours.avatar"));
        }
        label = message.toString();
        toolTipText = toolTip.toString();
    }

    /**
     * Send message
     */
    /*
    @Command
    public void sendMessage()
    {
        if (StringUtils.isBlank(message)) {
            Clients.showNotification(Labels.getLabel("message.enter.something"));
        } else {
            log.trace("posting event");
            EventQueues.lookup("globalMessage", EventQueues.APPLICATION, true).publish(new Event("onSendMessage", null, message));
        }
    }
    */

}
