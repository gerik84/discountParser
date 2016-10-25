package com.company;

import com.company.work.Tools;
import com.company.work.api.ConnManager;
import com.company.work.db.DBHelper;
import com.company.work.worker.PkaWorker;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class Main {

    public static void main(String[] args) {


        DBHelper.init();

//        DBHelper.insertPka(123, "firs", "small", "big", 123f, 110f, "1+1", "1+1 type");

        try {
            new PkaWorker().start();
        } catch (IOException var2) {
            var2.printStackTrace();
        }


//        try {
//            ConnManager.newInstance()
//                    .url("https://5ka.ru/api/special_offers/?records_per_page=15&page=14")
//                    .listener(new ConnManager.ResponseIO() {
//                        @Override
//                        public void onSuccess(int code, InputStream is) {
//                            try {
//                                JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
//                                reader.beginObject();
//                                while (reader.hasNext())
//                                {
//                                    String name = reader.nextName();
//                                    switch (name){
//                                        case "results":
//                                            Tools.print("asdas");
//                                            break;
//                                        default:
//                                            reader.skipValue();
//                                    }
//
//                                }
//
//
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }).send();
//
//        } catch (Exception e) {
//            Tools.print("asdas");
//
//        }

    }
}
