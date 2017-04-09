package model;


public class recycleViewModel {

    String name,username,comment,warning,follow;

    public void setWarning(String warning) {
        this.warning = warning;
    }

    public void setFollow(String follow) {
        this.follow = follow;
    }

    public String getFollow() {
        return follow;
    }

    public String getWarning() {
        return warning;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

}
