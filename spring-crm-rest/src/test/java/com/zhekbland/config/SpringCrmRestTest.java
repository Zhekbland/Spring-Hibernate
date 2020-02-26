package com.zhekbland.config;

import com.zhekbland.persistence.CustomerDB;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpringCrmRestTest {

    @Test
    public void whenWeCreateDB() {
        CustomerDB db = CustomerDB.getInstance();
        db.checkTableExist();
    }

}