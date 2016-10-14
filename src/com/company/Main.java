package com.company;

import com.company.work.Tools;
import com.company.work.api.ConnManager;
import com.company.work.worker.PkaWorker;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {


        try {
            new PkaWorker().start();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
