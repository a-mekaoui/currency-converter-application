package com.github.amekaoui.currencyconverter.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import java.security.Principal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

public class LoginControllerTest {

    private LoginController loginController;

    @Mock
    private Principal principal;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.loginController = new LoginController();
    }

    @Test
    public void whenIamOnTheRootContextPathIamRedirectedToLoginPath() throws Exception {
        MockMvc mockMvc = standaloneSetup(loginController).build();
        mockMvc.perform(get("/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:login"));
    }

    @Test
    public void redirectToLoginPageTest() {
        //given
        Principal principal = null;

        //when
        String viewName = this.loginController.home(principal);

        //then
        Assert.assertEquals("redirect:login", viewName);
    }

    @Test
    public void redirectToConversionPageTest() {
        //given a non null principal
        Principal principal = this.principal;

        //when
        String viewName = this.loginController.home(principal);

        //then
        Assert.assertEquals("redirect:/conversion", viewName);
    }
}