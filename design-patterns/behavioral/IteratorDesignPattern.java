import java.util.*;

class Video{
    private String title;
    public Video(String title){
        this.title = title; 
    }
    public String getTitle(){
        return title;
    }
}

class VideoCollection{
    private List<Video> videos;
    public VideoCollection(){
        videos = new ArrayList<>();
    }
    public void addVideo(Video video){
        videos.add(video);
    }

    List<Video> getVideos(){
        return videos;
    }
}

interface Iterator{
    boolean hasNext();
    Video next();
}

class VideoIterator implements Iterator{
    List<Video> videos;
    int index;
    public VideoIterator(List<Video> videos){
        this.videos = videos;
        this.index = 0;
    }
    @Override
    public boolean hasNext() {
        return index < videos.size();
    }
    @Override
    public Video next() {
        if(hasNext()){
            return videos.get(index++);
        }
        return null;
    }
}

class IteratorDesignPattern{
    public static void main(String[] args) {
        VideoCollection videoCollection = new VideoCollection();
        videoCollection.addVideo(new Video("Video 1"));
        videoCollection.addVideo(new Video("Video 2"));
        videoCollection.addVideo(new Video("Video 3"));
        Iterator iterator = new VideoIterator(videoCollection.getVideos());
        while(iterator.hasNext()){
            System.out.println(iterator.next().getTitle());
        }
    }
}
