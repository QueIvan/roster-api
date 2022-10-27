package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "email_authorizations")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmailAuthorization {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String email;
    @NotNull
    private Boolean status;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "authorized")
    private Path path;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "authorized")
    private Config config;
}
