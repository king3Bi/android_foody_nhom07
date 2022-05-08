package hcmute.nhom7.foody.model;

public class Comment {
    private int userId;
    private int foodId;
    private int rate;
    private String avatar;
    private String username;
    private String title;
    private String content;

    public Comment(String avatar, String username, String title, String content) {
        this.avatar = avatar;
        this.username = username;
        this.title = title;
        this.content = content;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
