package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
@Entity(name = "corrections")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Corrections {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String target;
    @NotNull
    private String category;
    @NotNull
    private String value;
    @NotNull
    @ManyToOne(cascade = CascadeType.MERGE)
    private Config config;
}
