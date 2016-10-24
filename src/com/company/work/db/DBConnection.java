package com.company.work.db;

import com.company.Config;
import com.company.work.Tools;

import java.math.BigInteger;
import java.sql.*;
import java.util.UUID;

/**
 * Created by Pavel G on 14.10.2016.
 */
class DBConnection {

    private static DBConnection sInstance;

    private Connection mConnection;
    private Statement mStmt;

    private DBConnection() {
        try {
            String urlConnection = "jdbc:mysql://" + Config.DB_HOST + "/" + Config.DB_NAME + "?user=root&password=repytxbr&useSSL=false";
            mConnection = DriverManager.getConnection(urlConnection);
            mStmt = mConnection.createStatement();
        } catch (SQLException e) {
            Tools.print("Error connection to DB: ");
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {

        return sInstance == null ? sInstance = new DBConnection() : sInstance;
    }

    public ResultSet executeQuery(String query) {
        try {
            return mStmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean execute(String query) {
        try {
            return mStmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Builder create(String query) throws SQLException {
        return new Builder(query);
    }

    public static class Builder {

        private PreparedStatement mPrepareStetment;
        int position = 1;

        private Builder (String query) throws SQLException {
            mPrepareStetment =  DBConnection.getInstance().mConnection.prepareStatement(query);
        }

        public Builder setArg(String arg) throws SQLException {
            mPrepareStetment.setString(position++, arg);
            return this;
        }

        public Builder setArg(Integer arg) throws SQLException {
            mPrepareStetment.setInt(position++, arg);
            return this;
        }

        public Builder setArg(Float arg) throws SQLException {
            mPrepareStetment.setFloat(position++, arg);
            return this;
        }

        public PreparedStatement build() {
            return mPrepareStetment;
        }


    }



}
