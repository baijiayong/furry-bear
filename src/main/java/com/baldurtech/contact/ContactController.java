package com.baldurtech.contact;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;

import java.util.List;
import java.util.ArrayList;

@Controller
@RequestMapping("contact")
public class ContactController {
    ContactService contactService;
    
    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        model.addAttribute("list", contactService.getList());
        return "contact/list";
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.GET) 
    public String create() {
        return "contact/create";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@RequestParam("name") String name
        , @RequestParam("email") String email
        , @RequestParam("mobile") String mobile
        , @RequestParam("vpmn") String vpmn
        , @RequestParam("officeAddress") String officeAddress
        , @RequestParam("homeAddress") String homeAddress
        , @RequestParam("memo") String memo
        , @RequestParam("job") String job
        , @RequestParam("jobLevel") String jobLevel
        , Model model ) {
        Contact contact = new Contact();
        contact.setName(name);
        contact.setEmail(email);
        contact.setMobile(mobile);
        contact.setVpmn(vpmn);
        contact.setOfficeAddress(officeAddress);
        contact.setHomeAddress(homeAddress);
        contact.setMemo(memo);
        contact.setJob(job);
        contact.setJobLevel(Long.valueOf(jobLevel));
        
        contactService.save(contact);
        
        model.addAttribute("contactName", contact.getName());
        return "contact/save";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam(value="id", required=false, defaultValue="") String id, Model model) {
        model.addAttribute("contact", contactService.show(Long.valueOf(id)));
        return "contact/show";
    } 
   
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@RequestParam(value="id", required=false, defaultValue="") String id
        , @RequestParam(value = "name", required=false, defaultValue="")String name
        , @RequestParam(value = "mobile", required=false, defaultValue="")String mobile
        , @RequestParam(value = "vpmn", required=false, defaultValue="")String vpmn
        , @RequestParam(value = "email", required=false, defaultValue="")String email
        , @RequestParam(value = "homeAddress", required=false, defaultValue="")String homeAddress
        , @RequestParam(value = "officeAddress", required=false, defaultValue="")String officeAddress
        , @RequestParam(value = "job", required=false, defaultValue="")String job
        , @RequestParam(value = "jobLevel", required=false, defaultValue="")String jobLevel
        , @RequestParam(value = "memo", required=false, defaultValue="")String memo
        , String action
        , Model model) {
            
        Contact contact = new Contact();
        contact.setId(Long.valueOf(id));
        contact.setName(name);
        contact.setMobile(mobile);
        contact.setVpmn(vpmn);
        contact.setEmail(email);
        contact.setHomeAddress(homeAddress);
        contact.setOfficeAddress(officeAddress);
        contact.setJob(job);
        contact.setJobLevel(Long.valueOf(jobLevel));
        contact.setMemo(memo);
        
        model.addAttribute("contact", contactService.update(contact));
        return "contact/update";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST) 
    public String delete(@RequestParam(value="id", required=false, defaultValue="") String id
        ,Model model) {
        
        Contact contact = new Contact();
        contact.setId(Long.valueOf(id));
        
        model.addAttribute("contact",contact);
        return "contact/delete";
    }
}