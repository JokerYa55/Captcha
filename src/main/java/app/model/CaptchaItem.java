package app.model;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author vasil
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CaptchaItem implements Serializable {

    @Id
    String id = UUID.randomUUID().toString();
    @Column(name = "date_created")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date dateCreated;
    String code;

    @Transient
    byte[] image;
}
