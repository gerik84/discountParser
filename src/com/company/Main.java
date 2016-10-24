package com.company;

import com.company.work.db.DBHelper;

public class Main {

    public static void main(String[] args) {


        DBHelper.init();

        DBHelper.insertPka(123, "firs", "small", "big", 123f, 110f, "1+1", "1+1 type");



      /*  try {
            ConnManager.newInstance()
                    .url("https://5ka.ru/api/special_offers/?records_per_page=15&page=14")
                    .listener(new ConnManager.Response() {
                        @Override
                        public void onSuccess(int code, String response) {
                            Tools.print(response);
                        }
                    }).send();

        } catch (Exception e) {

        }*/

    }
}
