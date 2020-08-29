package by.vit.myblog.entity;

import lombok.Data;

import java.util.Date;

/**
 * Person entity. Used to update personal info.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Data
public class Person {

    /** First name. */
    private String firstName;

    /** Last name. */
    private String lastName;

    /** Date of birth. */
    private Date dob;

    /** Bio. */
    private String bio;

}
