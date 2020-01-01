package com.example.heroesapiforclass;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestWithMockito {

    @Test
    public void test_when_thenReturn(){
        LoginActivity activity = Mockito.mock(LoginActivity.class);
        when(LoginActivity.TAG).thenReturn("LoginActivity");
        assertThat(activity.getClass().getName(),is("LoginActivity"));
    }
}