/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.apress.ejb.chapter12.microservice.example;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author nardoma
 */
@RestController
@RequestMapping("/url")
public class NewRestController {
        
    public String sayHelloWorld(){
        return "Hello World";
    }
    
}

