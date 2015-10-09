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
    public  Token SaveUserToken(String email , String password ){
        Token token = null;
        if( (userDAO.getUserByMail(email) != null) && password.equals(userDAO.getPasswordByMail(email))){
            token = new Token();
            tokenDAO.createToken(email,token.getToken(),token.getDate());

        }
//        else if(password != null & userDAO.getUserByMail(email) == null){
//            userDAO.createUser(email,password);
//            tokenDAO.createToken(email,token,new Date());
//            return true;
//        }
        return token;

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
