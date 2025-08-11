package com.pahanaedu.bookshopmanagement.schema;

import java.sql.Connection;

public interface Entity {
    void createTable(Connection conn);
}
