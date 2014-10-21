package com.baldurtech.contact;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.Before;
import static org.mockito.Mockito.verify;
import org.mockito.InjectMocks;
import com.baldurtech.config.WebAppConfigurationAware;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.ArrayList;

import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

import org.springframework.ui.Model;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class ContactControllerTest extends WebAppConfigurationAware {
    private Long CONTACT_ID = 1L;
    private Contact contact;
    
    @Mock
    ContactService contactService;
    
    @Mock
    Model model;
    
    @InjectMocks
    ContactController contactController;
    
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);  
    }
    
    @Before
    public void setup() {
        contact = new Contact();
        contact.setName("Xiaobai");
        contact.setMobile("18233333333");
        contact.setVpmn("63333");
        contact.setEmail("xiaobai@gmail.com");
        contact.setHomeAddress("Taiyuan");
        contact.setOfficeAddress("BeiZhang");
        contact.setJob("HR");
        contact.setJobLevel(9L);
    }
    
   @Test
    public void 当URI为contact_list时应该由ContactController处理() throws Exception {
        mockMvc.perform(get("/contact/list"))
            .andExpect(view().name("contact/list"))
            .andExpect(model().attributeExists("list"));

    }
    
    @Test
    public void 在ContactController中调用ContactService中的getList方法() {
        contactController.list(model);
        verify(contactService).getList();
    }
    
    @Test
    public void 当URI为contact_create时应该访问create页面() throws Exception {
        mockMvc.perform(get("/contact/create"))
            .andExpect(view().name("contact/create"));
    }

    @Test
    public void 当URI为contact_save时应该访问save页面() throws Exception {
        mockMvc.perform(post("/contact/save")
            .param("name",String.valueOf(contact.getName()))
            .param("mobile", String.valueOf(contact.getMobile()))
            .param("vpmn", String.valueOf(contact.getVpmn()))
            .param("email", String.valueOf(contact.getEmail()))
            .param("homeAddress", String.valueOf(contact.getHomeAddress()))
            .param("officeAddress", String.valueOf(contact.getOfficeAddress()))
            .param("job", String.valueOf(contact.getJob()))
            .param("jobLevel", String.valueOf(contact.getJobLevel()))
            .param("memo", String.valueOf(contact.getMemo())))
            .andExpect(view().name("contact/save"))
            .andExpect(model().attributeExists("contactName"));
               
    }
    
    @Ignore
    @Test
    public void 当URL为contact_show时应该访问show页面() throws Exception {
        mockMvc.perform(get("/contact/show").param("id",String.valueOf(CONTACT_ID)))
            .andExpect(view().name("contact/show"))
            .andExpect(status().isOk()) 
            .andExpect(model().attributeExists("contact"));
    }
    
    @Test
    public void 在ContactController中调用ContactService中的getById方法() {
        contactController.show(String.valueOf(CONTACT_ID), model);
        verify(contactService).show(CONTACT_ID);
    }
    
    @Test
    public void 在ContactController中调用ContactService中的save方法() {
        contactController.save(contact.getName() 
            , contact.getEmail()
            , contact.getMobile() 
            , contact.getVpmn()
            , contact.getOfficeAddress()
            , contact.getHomeAddress() 
            , contact.getMemo()
            , contact.getJob()
            , String.valueOf(contact.getJobLevel())
            , model);
        verify(contactService).save(any(Contact.class));
    }
}