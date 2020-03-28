package com.lethanh98.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
public class UserControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation))
                .build();
    }
    @Test
    public void getByIdOK() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/users/{id}",1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("api/users/get-by-id",
                        pathParameters(parameterWithName("id")
                                .description("Identifier of the person to be obtained.")),
                        responseFields(
                                fieldWithPath("id")
                                        .description("Id của user"),
                                fieldWithPath("firstName")
                                        .description("firstName của user"),
                                fieldWithPath("lastName")
                                        .description("lastName của user")
                        )
                ));
    }
    @Test
    public void getALL() throws Exception {
        this.mockMvc.perform(RestDocumentationRequestBuilders.get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andDo(document("api/users/get-all",
                        pathParameters(),
                        responseFields(
                                fieldWithPath("[]").description("An array"),
                                fieldWithPath("[].id")
                                        .description("Id của user"),
                                fieldWithPath("[].firstName")
                                        .description("firstName của user"),
                                fieldWithPath("[].lastName")
                                        .description("lastName của user")
                        )
                ));
    }
}