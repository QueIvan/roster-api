package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "configs")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Config {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    private String wikiPath;
    private String rosterPath;
    @ElementCollection
    private Set<String> ignoredHeaders;
    private String splitPath;
    @ElementCollection
    private Set<String> splitHeaders;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Options options;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Corrections> corrections;
    @ManyToMany(mappedBy = "configs", cascade = CascadeType.ALL)
    private List<Path> paths;
    @OneToOne(cascade = CascadeType.ALL)
    private EmailAuthorization authorized;
}
