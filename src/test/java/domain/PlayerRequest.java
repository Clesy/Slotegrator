package domain;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nonnull;

public record PlayerRequest(@Nonnull @SerializedName("username") String username,
                            @Nonnull @SerializedName("password_change") String passwordChange,
                            @Nonnull @SerializedName("password_repeat") String repeatPassword,
                            @Nonnull @SerializedName("email") String email,
                            @SerializedName("name") String name,
                            @SerializedName("surname") String surname,
                            @SerializedName("currency_code") String currencyCode) {
}
