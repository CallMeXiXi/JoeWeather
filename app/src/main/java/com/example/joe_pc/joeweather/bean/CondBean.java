package com.example.joe_pc.joeweather.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Joe_PC on 2017/3/19.
 */

public class CondBean implements Serializable {

    @SerializedName("code_d")
    private String codeD;
    @SerializedName("code_n")
    private String codeN;
    @SerializedName("txt_d")
    private String txtD;
    @SerializedName("txt_n")
    private String txtN;

    public void setCodeD(String codeD) {
        this.codeD = codeD;
    }

    public String getCodeD() {
        return codeD;
    }

    public void setCodeN(String codeN) {
        this.codeN = codeN;
    }

    public String getCodeN() {
        return codeN;
    }

    public void setTxtD(String txtD) {
        this.txtD = txtD;
    }

    public String getTxtD() {
        return txtD;
    }

    public void setTxtN(String txtN) {
        this.txtN = txtN;
    }

    public String getTxtN() {
        return txtN;
    }
}
