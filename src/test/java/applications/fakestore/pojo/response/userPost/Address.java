package applications.fakestore.pojo.response.userPost;

import applications.fakestore.pojo.response.userPost.Geolocation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private Geolocation geolocation;
    private String city;
    private String street;
    private int number;
    private String zipcode;
}
