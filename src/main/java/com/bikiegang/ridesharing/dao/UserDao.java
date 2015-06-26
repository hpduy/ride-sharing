package com.bikiegang.ridesharing.dao;

import com.bikiegang.ridesharing.database.Database;
import com.bikiegang.ridesharing.pojo.User;
import org.apache.log4j.Logger;

/**
 * Created by hpduy17 on 6/26/15.
 */
public class UserDao {
    Logger logger = Logger.getLogger(this.getClass());
    private Database database = Database.getInstance();
    public boolean insert(User obj){
        boolean result = false;
        try{
            result = true;
        }catch (Exception ex){

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }
    public boolean delete(User obj){
        boolean result = false;
        try{
            result = true;
        }catch (Exception ex){

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }
    public boolean update(User obj){
        boolean result = false;
        try{
            result = true;
        }catch (Exception ex){

            logger.error(ex.getStackTrace());
            ex.printStackTrace();
        }
        return result;
    }

}
