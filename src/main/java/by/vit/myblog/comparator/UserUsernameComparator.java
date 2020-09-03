package by.vit.myblog.comparator;

import by.vit.myblog.entity.User;

import java.util.Comparator;

/**
 * This comparator compares users by username.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public class UserUsernameComparator implements Comparator<User> {

    @Override
    public int compare(final User user1, final User user2) {
        return user1.getUsername().compareTo(user2.getUsername());
    }

}
