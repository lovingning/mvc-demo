package com.knowledge.mnlin.znd;

import java.util.logging.Logger;

/**
 * Created on 2018/11/12  10:59
 * function :
 *
 * @author mnlin
 */
public interface CommonI {
    default Logger getL(){
        return Logger.getLogger(getClass().getName());
    }
}
