package by.vit.myblog.comparator;

import by.vit.myblog.entity.Post;

import java.util.Comparator;

/**
 * This comparator compares posts by creation date.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
public class PostDateComparator implements Comparator<Post> {

    @Override
    public int compare(final Post post1, final Post post2) {
        return post1.getCreation().compareTo(post2.getCreation());
    }

}
