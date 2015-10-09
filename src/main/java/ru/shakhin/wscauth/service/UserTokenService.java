package ru.shakhin.wscauth.service;

import ru.shakhin.wscauth.dao.TokenDAO;
import ru.shakhin.wscauth.dao.UserDAO;
import ru.shakhin.wscauth.model.Token;
import ru.shakhin.wscauth.model.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Created by kshahin on 10/9/2015.
 */
public class UserTokenService {

    private UserDAO userDAO = null;
    private TokenDAO tokenDAO = null;
    public UserTokenService(){
        userDAO = new UserDAO();
        tokenDAO = new TokenDAO();
    }
    public  boolean SaveUserToken(String email , String password, String token ){
        if(password == null & userDAO.getUserByMail(email) != null){
            tokenDAO.createToken(email,token,new Date());
            return true;
        }
        else if(password != null & userDAO.getUserByMail(email) == null){
            userDAO.createUser(email,password);
            tokenDAO.createToken(email,token,new Date());
            return true;
        }
            return false;

    }
    public User getUserByEmail(String email){
        return userDAO.getUserByMail(email);
    }

    public List<Token> getHistoryToken(String email){
        try {
            return tokenDAO.getEtokenByUser(email);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
