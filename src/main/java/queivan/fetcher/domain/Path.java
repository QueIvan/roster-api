package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "paths")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Path {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String title;
    @NotNull
    private String abbreviation;
    private String imageUrl;
    @ManyToMany
    private List<Config> configs;
    @OneToOne(cascade = CascadeType.ALL)
    private EmailAuthorization authorized;
}
