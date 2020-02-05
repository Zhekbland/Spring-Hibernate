package com.zhekbland.jdbc;

import com.zhekbland.persistence.CustomerDB;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CustomerDBTest {

    private final CustomerDB customerDB = CustomerDB.getInstance();

    @Before
    public void init() {
        this.customerDB.checkTableExist();
    }

    @After
    public void destroy() {
        this.customerDB.deleteTable();
    }

    @Test
    public void whenWeCreateDB() {
        System.out.println("Hello");
    }
}