package by.vit.myblog.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Date;

/**
 * Post entity.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Entity
@Table(name = "post")
@Data
@EqualsAndHashCode(callSuper = false)
public class Post extends MyEntity {

    /** Post title. */
    @Column(name = "title", nullable = false)
    private String title;

    /** Post content. */
    @Column(name = "content", nullable = false)
    private String content;

    /** Date of post creation. */
    @Column(name = "creation", nullable = false)
    private Date creation = new Date(System.currentTimeMillis());

    /** Author of post. */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
