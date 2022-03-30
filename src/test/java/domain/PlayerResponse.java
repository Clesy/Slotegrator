package domain;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PlayerResponse {
    @SerializedName("id")
    Integer id;
    @SerializedName("country_id")
    String countryId;
    @SerializedName("timezone_id")
    String timezoneId;
    @SerializedName("username")
    String username;
    @SerializedName("email")
    String email;
    @SerializedName("name")
    String name;
    @SerializedName("surname")
    String surname;
    @SerializedName("gender")
    String gender;
    @SerializedName("phone_number")
    String phoneNumber;
    @SerializedName("birthdate")
    String birthdate;
    @SerializedName("bonuses_allowed")
    boolean bonusesAllowed;
    @SerializedName("is_verified")
    boolean isVerified;
}
