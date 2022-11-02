package queivan.fetcher.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "rosters")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Roster {
    @Id
    @GeneratedValue
    private UUID id;
    @NotNull
    private String name;
    @NotNull
    @Column(columnDefinition = "text")
    private String json;
}
