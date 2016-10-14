package com.company.work.worker;

import com.company.work.Tools;
import com.company.work.api.ConnManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

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
    protected void run(String response) throws IOException {
        Tools.print(response);
    }


}
