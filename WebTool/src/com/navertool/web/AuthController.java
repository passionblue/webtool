package com.navertool.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.navertool.search.AuthRequest;
import com.navertool.search.SearchManager;

@Controller
public class AuthController {

    private static Logger m_logger = LoggerFactory.getLogger(AuthController.class);

    private SearchManager searchManager = new SearchManager();
    
  
}
