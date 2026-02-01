import java.util.*;

interface ISubscriber{
    void update();
}

class EmailSubsriber implements ISubscriber{
    private String email;
    public EmailSubsriber(String email){
        this.email = email;
    }
    @Override
    public void update(){
        System.out.println(email + " received notification");
    }
}

class MobileAppSubsriber implements ISubscriber{
    private String username;
    public MobileAppSubsriber(String username){
        this.username = username;
    }
    @Override
    public void update(){
        System.out.println(username + " received App Notification");
    }
}

interface IChannel{
    void addSubscriber(ISubscriber observer);
    void removeSubscriber(ISubscriber observer);
    void notifySubscribers();
}


class Channel implements IChannel{
    private List<ISubscriber> subscribers;
    private String channelName;
    public Channel(String channelName){
        this.channelName = channelName;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void addSubscriber(ISubscriber observer) {
        subscribers.add(observer);
    }

    @Override
    public void removeSubscriber(ISubscriber observer) {
        subscribers.remove(observer);
    }

    @Override
    public void notifySubscribers() {
        for(ISubscriber observer : subscribers){
            observer.update();
        }
    }

    public void uploadVide(String videoTitle){
        System.out.println(channelName + " uploaded video : " + videoTitle);
        notifySubscribers();
    }
    
}

public class OberserverDesignPatter {
    public static void main(String[] args) {
        Channel channel = new Channel("Tech Channel");
        EmailSubsriber emailSubsriber = new EmailSubsriber("tech@gmail.com");
        MobileAppSubsriber mobileAppSubsriber = new MobileAppSubsriber("tech");
        channel.addSubscriber(emailSubsriber);
        channel.addSubscriber(mobileAppSubsriber);
        channel.uploadVide("Video 1");
    }
}
