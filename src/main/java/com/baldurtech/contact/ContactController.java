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
    
    @RequestMapping(value = "/create", method = REquestMethod.GET) 
    public String create() {
        return "contact/create";
    }
    
    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String show(@RequestParam(value="id", required=false, defaultValue="") String id, Model model) {
        model.addAttribute("contact", contactService.show(Long.valueOf(id)));
        return "contact/show";
    } 
}