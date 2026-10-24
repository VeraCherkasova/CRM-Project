package applications.api.crm.pojo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class UserCrmPost {

    private String firstName;
    private boolean isActive;
    private String lastName;
    private String name;
    private String salutationName;
    private String type;
    private String userName;
}
