package data;

import java.util.ArrayList;

public class ListUsersClackData extends ClackData {
    private ArrayList<String> usernames;

    public ListUsersClackData(String userName, ArrayList<String> usernames) {
        super(userName, CONSTANT_LISTUSERS);
        this.usernames = usernames;
    }

    public ListUsersClackData(String userName) {
        super(userName, CONSTANT_LISTUSERS);
    }

    @Override
    public String getData() {
        return usernames.toString();
    }

    // TODO E2E connect client encryption?
    @Override
    public String getData(String key) {
        return getData();
    }
}
