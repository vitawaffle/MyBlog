package by.vit.myblog.entity;

import by.vit.myblog.validation.FirstName;
import by.vit.myblog.validation.LastName;
import lombok.Data;

/**
 * Person entity. Used to update personal info.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Data
public class Person {

    /** First name. */
    @FirstName
    private String firstName;

    /** Last name. */
    @LastName
    private String lastName;

    /** Date of birth. */
    private String dob;

    /** Bio. */
    private String bio;

}
