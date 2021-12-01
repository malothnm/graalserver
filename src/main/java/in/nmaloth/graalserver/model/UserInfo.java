package in.nmaloth.graalserver.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.gemfire.mapping.annotation.PartitionRegion;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@PartitionRegion(name = "user")
public class UserInfo {

    @Id
    private String id;

    private String name;
    private Integer badgeId;

}
