package ua.lv.halya.app;

import org.flywaydb.core.Flyway;

public class Main {
    public static void main(String[] args) {

        Flyway flyway = new Flyway();
        flyway.setDataSource("jdbc:mysql://localhost/myFinance?createDatabaseIfNotExist=true", "root", "root");
        flyway.migrate();

    }
}
