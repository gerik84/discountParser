package com.company.work.worker;

import com.company.model.pka.PkaModel;
import com.company.work.Tools;
import com.company.work.db.DBHelper;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Pavel G on 14.10.2016.
 */
public class PkaWorker extends BaseWorker {

    @Override
    protected String baseUrl() {
        return "https://5ka.ru";
    }

    @Override
    protected String apiUrl() {
        return "api/special_offers/?records_per_page=1000&page=1&all_prev=1";
    }

    @Override
    protected void run(InputStream is) throws IOException {
        List<PkaModel> list = new ArrayList<>();
        try {
            JsonReader reader = new JsonReader(new InputStreamReader(is, "UTF-8"));
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                switch (name) {
                    case "results":
//                        reader.beginArray();

                        PkaModel[] models = new Gson().fromJson(reader, PkaModel[].class);
                        list.addAll(Arrays.asList(models));
                        Tools.print("asdas");
//                        reader.endArray();
                        break;
                    default:
                        reader.skipValue();
                }

            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            for (PkaModel model : list) {
                DBHelper.insertPka(
                        23,
                        model.name,
                        model.image_small,
                        model.image_big,
                        model.params.regular_price,
                        model.params.special_price,
                        model.getTerms_type().getName(),
                        model.getTerms_type().getDescription());
            }
        }
    }


}
