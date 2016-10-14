package com.company.work.worker;

import com.company.work.Tools;
import com.company.work.api.ConnManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by Pavel G on 14.10.2016.
 */
abstract public class BaseWorker {

    ConnManager mConnection = ConnManager.newInstance();

    private static char SEPARATOR = '/';

    protected abstract String baseUrl();
    protected abstract String apiUrl();
    protected abstract void run(String response) throws IOException;

    public String getUrl() {

        String baseUrl = baseUrl();
        String apiUrl = apiUrl();
        Tools.isNull(baseUrl, apiUrl);

        if (baseUrl.charAt(baseUrl.length() - 1) != SEPARATOR)
            baseUrl = baseUrl + SEPARATOR;

        return baseUrl + apiUrl();
    }

    public void start() throws IOException {
        run(mConnection
                .url(getUrl())
                .timeout(10000)
                .sendSync()
        );
    }

}

