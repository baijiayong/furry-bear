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
}