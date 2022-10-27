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
    private String path;
    @ElementCollection
    private Set<String> ignoredHeaders;
    private Boolean split;
    private String splitHeader;
    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private Options options;
    @OneToMany(mappedBy = "config", cascade = CascadeType.MERGE)
    private List<Corrections> corrections;
    @ManyToMany(mappedBy = "configs", cascade = CascadeType.MERGE)
    private List<Path> paths;
    @OneToOne(cascade = CascadeType.ALL)
    private EmailAuthorization authorized;
}
