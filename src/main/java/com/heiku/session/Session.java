package com.heiku.session;

import lombok.Data;

/**
 * @Author: Heiku
 * @Date: 2019/7/6
 */

@Data
public class Session {

    private String userId;

    private String username;

    public Session(String userId, String username){
        this.userId = userId;
        this.username = username;
    }
}
