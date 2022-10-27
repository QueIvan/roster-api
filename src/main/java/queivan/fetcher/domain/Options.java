package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;
@Entity(name = "options")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Options {
    @Id
    @GeneratedValue
    private UUID id;
    @OneToOne(mappedBy = "options")
    private Config config;
    @NotNull
    private Boolean ignore;
    @NotNull
    private Boolean training;
    @NotNull
    private Boolean ranks;
    @NotNull
    private Boolean leo;
    @NotNull
    private Boolean certifications;
    @NotNull
    private Boolean removeLogo;
    @NotNull
    private Boolean subdivision;
    @NotNull
    private Boolean pathed;
}
