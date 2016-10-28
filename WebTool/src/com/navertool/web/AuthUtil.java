package com.navertool.web;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.navertool.search.AuthRequest;
public class AuthUtil {

    private static Logger m_logger = LoggerFactory.getLogger(AuthUtil.class);

    //Default Constructor
    public AuthUtil() {
    }

    
    public static void authenticate(HttpServletRequest request, AuthRequest authRequest) throws Exception {
        
        if ( !authRequest.getPhrase().equals("죠이리빙짱"))
            throw new Exception("Authentication Failed");

        
        HttpSession session = request.getSession ();
        
        session.setAttribute("AuthRequest", authRequest );
    }
    
    public static void checkAndUpdateAuthentication(HttpServletRequest request) throws Exception  {
        
        HttpSession session = request.getSession ();
        
        AuthRequest auth = (AuthRequest) session.getAttribute("AuthRequest");
        
        if ( auth == null || auth.expired())
            throw new Exception();
        
        auth.extend();
    }
    
}
