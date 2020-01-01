package com.example.heroesapiforclass;

import org.hamcrest.core.IsNull;
import org.junit.Test;
import java.io.IOException;
import heroesapi.HeroesAPI;
import model.LoginSignupResponse;
import retrofit2.Call;
import retrofit2.Response;
import url.Url;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class HeroesAPITest {

    @Test
    public void testLogin() throws IOException
    {
        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);
        Call<LoginSignupResponse> call = heroesAPI.checkUser("kiran","kiran");
        try
        {
            // Synchronous method
            Response<LoginSignupResponse> response= call.execute();
            LoginSignupResponse loginSignupResponse = response.body();
            assertTrue(response.isSuccessful() && loginSignupResponse.getSuccess());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Test
    public void testCookie()
    {
        HeroesAPI heroesAPI = Url.getInstance().create(HeroesAPI.class);
        Call<LoginSignupResponse> call = heroesAPI.checkUser("kiran","kiran");
        try
        {
            // Synchronous method
            Response<LoginSignupResponse> response= call.execute();
            Url.Cookie = response.headers().get("Set-Cookie");
            assertThat(Url.Cookie,is(IsNull.notNullValue()));
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}




