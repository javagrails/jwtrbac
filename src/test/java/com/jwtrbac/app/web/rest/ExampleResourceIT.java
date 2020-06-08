package com.jwtrbac.app.web.rest;

import com.jwtrbac.app.JwtrbacApp;
import com.jwtrbac.app.web.rest.vm.HeaderInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Integration tests for the {@link AccountResource} REST controller.
 */
@AutoConfigureMockMvc
//@WithMockUser(value = TEST_USER_LOGIN)
@SpringBootTest(classes = JwtrbacApp.class)
public class ExampleResourceIT {
    private final static String  BASE_ULR = "/api/examples/";
    private              MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new ExampleResource()).build();
    }

    private void printResponseContent(ResultActions resultActions) throws UnsupportedEncodingException {
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        System.out.println("==================ContentAsString==================");
        System.out.println(contentAsString);
        System.out.println("==================ContentAsString==================");
    }

    private void printHeaders(ResultActions resultActions) throws UnsupportedEncodingException {
        Collection<String> headerKeys = resultActions.andReturn().getResponse().getHeaderNames();
        for (String headerKey : headerKeys) {
            String headerValue = resultActions.andReturn().getResponse().getHeader(headerKey);
            System.out.println("==================PrintHeaders==================");
            System.out.println(headerKey + ":" + headerValue);
            System.out.println("==================PrintHeaders==================");
        }
    }

    @Test
    public void whenGetRequestSentToGreeting_thenStatusOKAndGreetingReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "greeting").header("accept-language", "de"))
            .andExpect(status().isOk())
            .andExpect(content().string("Hallo!"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToDouble_thenStatusOKAndCorrectResultReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "double").header("my-number", 2))
            .andExpect(status().isOk())
            .andExpect(content().string("2 * 2 = 4"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToAllHeaders_thenStatusOkAndTextReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "listHeaders").header("my-header", "Test"))
            .andExpect(status().isOk())
            .andExpect(content().string("Listed 1 headers"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToMultiValue_thenStatusOkAndTextReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "multiValue").header("my-header", "ABC", "DEF", "GHI"))
            .andExpect(status().isOk())
            .andExpect(content().string("Listed 1 headers"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void multipleHeaders() throws Exception {
        HeaderInfo headerInfo1 = new HeaderInfo(1l, "key1", "value1");
        HeaderInfo headerInfo2 = new HeaderInfo(2l, "key2", "value2");
        HeaderInfo headerInfo3 = new HeaderInfo(3l, "key3", "value3");
        List<HeaderInfo> headerInfos = new ArrayList<>(Arrays.asList(headerInfo2,headerInfo3));

        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "multiple-headers")
            .header("header-key-1", "header-key-1-value-1", "header-key-1-value-2", "header-key-1-value-3")
            .header("header-key-2", "header-key-2-value-2")
            .header("header-key-3", "header-key-3-value-3")
            .header("headerInfo1", headerInfo1)
            .header("headerInfos", headerInfos))
            .andExpect(status().isOk())
            .andExpect(content().string("Listed 5 headers"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToGetBaseUrl_thenStatusOkAndHostReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "getBaseUrl").header("host", "localhost:8080"))
            .andExpect(status().isOk())
            .andExpect(content().string("Base URL = http://localhost:8080"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToNonRequiredHeaderWithoutHeader_thenStatusOKAndMessageReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "nonRequiredHeader"))
            .andExpect(status().isOk())
            .andExpect(content().string("Was the optional header present? No!"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

    @Test
    public void whenGetRequestSentToDefaultWithoutHeader_thenStatusOKAndMessageReturned() throws Exception {
        ResultActions resultActions = mockMvc.perform(get(BASE_ULR + "default"))
            .andExpect(status().isOk())
            .andExpect(content().string("Optional Header is 3600"));
        printResponseContent(resultActions);
        printHeaders(resultActions);
        System.out.println("CALL_END");
    }

}
