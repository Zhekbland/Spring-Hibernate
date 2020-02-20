package com.zhekbland.springsecurity.persistence;

import org.junit.Test;

public class CustomerDBTest {

    @Test
    public void whenWeTestConnection() {
        CustomerDB db = CustomerDB.getInstance();
        db.checkTableExist();
    }

}