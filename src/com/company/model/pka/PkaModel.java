package com.company.model.pka;

import com.company.model.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pavel on 25.10.16.
 */
public class PkaModel extends PkaBaseModel {

    @SerializedName("image_small")
    public String image_small;

    @SerializedName("image_big")
    public String image_big;

    @SerializedName("description")
    public String description;

    @SerializedName("name")
    public String name;

    @SerializedName("params")
    public PkaParamsModel params;

    @SerializedName("terms_type")
    public PkaTerms_typeModel terms_type;

    public PkaTerms_typeModel getTerms_type() {
        return  terms_type == null ? new PkaTerms_typeModel() : terms_type;
    }
}
