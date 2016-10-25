package com.company.model.pka;

import com.google.gson.annotations.SerializedName;
import com.mysql.jdbc.StringUtils;

/**
 * Created by pavel on 25.10.16.
 */
public class PkaTerms_typeModel extends PkaBaseModel {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    public String getName() {
        return name == null ? "" : name;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

}
