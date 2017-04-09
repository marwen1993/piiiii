package model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



public class userModel  extends RecursiveTreeObject<userModel> {

    public StringProperty id,fullName,username,email,password,role,enabled,comment,create;

    public  userModel(String id,String fullName,String username,String email,String password,String role,String enabled,String comment,String create){
        this.id=new SimpleStringProperty(id);
        this.fullName=new SimpleStringProperty(fullName);
        this.username=new SimpleStringProperty(username);
        this.email=new SimpleStringProperty(email);
        this.password=new SimpleStringProperty(password);
        this.role=new SimpleStringProperty(role);
        this.enabled=new SimpleStringProperty(enabled);
        this.comment=new SimpleStringProperty(comment);
        this.create=new SimpleStringProperty(create);

    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setComment(String comment) {
        this.comment.set(comment);
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public void setCreate(String create) {
        this.create.set(create);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setEnabled(String enabled) {
        this.enabled.set(enabled);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getId() {
        return id.get();
    }

    public String getFullName() {
        return fullName.get();
    }

    public String getUsername() {
        return username.get();
    }

    public String getComment() {
        return comment.get();
    }

    public String getCreate() {
        return create.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getEnabled() {
        return enabled.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getRole() {
        return role.get();
    }
}
