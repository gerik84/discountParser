package com.company.work.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

/**
 * Created by Pavel G on 14.10.2016.
 */
public class DBHelper {

    public static void init() {
        String pka = "CREATE TABLE IF NOT EXISTS pka (" +
                "`id` VARCHAR(128) NOT NULL , " +
                "`region_id` BIGINT(20) NOT NULL , " +
                "`name` VARCHAR(64) NOT NULL , " +
                "`image_small` VARCHAR(128) NOT NULL , " +
                "`image_big` VARCHAR(128) NOT NULL , " +
                "`regular_price` FLOAT NOT NULL , " +
                "`discount_price` VARCHAR(128) NOT NULL , " +
                "`discount_name` VARCHAR(128) NOT NULL , " +
                "`discount_type` VARCHAR(128) NOT NULL , " +
                "PRIMARY KEY (`id`)," +
                "KEY (`region_id`)" +
                ") ENGINE = InnoDB;";

        DBConnection.getInstance().execute(pka);
    }

    public static boolean insertPka(Integer region_id, String name, String image_small, String image_big, Float regular_price, Float discount_price, String discount_name, String discount_type) {
        String insert = "INSERT INTO pka (`id`, `region_id`, `name`, `image_small`, `image_big`, `regular_price`, `discount_price`, `discount_name`, `discount_type`) VALUES(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement = DBConnection
                    .createStatement(insert)
                    .setArg(UUID.randomUUID().toString())
                    .setArg(region_id)
                    .setArg(name)
                    .setArg(image_small)
                    .setArg(image_big)
                    .setArg(regular_price)
                    .setArg(discount_price)
                    .setArg(discount_name)
                    .setArg(discount_type)
                    .build();

            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
