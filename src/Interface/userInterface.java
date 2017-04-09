package Interface;

import Main.Main;
import controller.signInController;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import model.userModel;

import java.util.ArrayList;


public interface userInterface {

    public void insertUser(userModel um);
    public ArrayList<userModel> selectAll();
    public void deleteUser(int index, TreeItem<userModel> treeItem, ObservableList<userModel> userList);
    public void ChangePermissionEnabled(TreeItem<userModel> treeItem);
    public void ChangeRole(TreeItem<userModel> treeItem);
    public void ChangeComunityCreationPermission(TreeItem<userModel> treeItem);
    public void ChangeCommentPermission(TreeItem<userModel> treeItem);
    public void signin(String username, String password, Main main, signInController signInController);


}
