package com.pahanaedu.librarymanagement.schema;

import java.sql.Connection;

public interface Entity {
    void createTable(Connection conn);
}
