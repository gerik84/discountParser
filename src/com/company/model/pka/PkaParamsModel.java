package com.company.model.pka;

import com.company.model.BaseModel;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pavel on 25.10.16.
 */
public class PkaParamsModel extends PkaBaseModel {

    @SerializedName("special_price")
    public Float special_price;

    @SerializedName("regular_price")
    public Float regular_price;
}
