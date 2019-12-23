package com.zhekbland.jdbc;

import org.junit.Test;

import static org.junit.Assert.*;

public class DataCreatorTest {

    @Test
    public void whenWeCreateDB() {
        DataCreator dataCreator = new DataCreator();
        dataCreator.getConnection();
        dataCreator.checkTableExist();
    }

}