package by.vit.myblog.entity;

import lombok.Data;

/**
 * Password entity. Used to update the password.
 *
 * @author Vitaly Lobatsevich (vitaly.lobatsevich@gmail.com)
 */
@Data
public class Password {

    /** Current password. */
    private String currentPassword;

    /** New password. */
    private String newPassword;

}
