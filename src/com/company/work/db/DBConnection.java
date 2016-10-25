package com.company.work.db;

import com.company.Config;
import com.company.work.Tools;

import java.sql.*;

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

    static Builder createStatement(String query) throws SQLException {
        return new Builder(query);
    }

    static class Builder {

        private PreparedStatement mPrepareStatement;
        int position = 1;

        private Builder(String query) throws SQLException {
            mPrepareStatement = DBConnection.getInstance().mConnection.prepareStatement(query);
        }

        Builder setArg(String arg) throws SQLException {
            mPrepareStatement.setString(position++, arg);
            return this;
        }

        Builder setArg(Integer arg) throws SQLException {
            mPrepareStatement.setInt(position++, arg);
            return this;
        }

        Builder setArg(Float arg) throws SQLException {
            mPrepareStatement.setFloat(position++, arg);
            return this;
        }

        PreparedStatement build() {
            return mPrepareStatement;
        }


    }


}
