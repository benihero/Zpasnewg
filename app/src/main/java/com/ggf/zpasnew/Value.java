package com.ggf.zpasnew;

import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Model.ResultDataRealAktifitas;
import com.ggf.zpasnew.Model.ResultInputSPK;
import com.ggf.zpasnew.Model.ResultJumlahTK;
import com.ggf.zpasnew.Model.ResultMandor;
import com.ggf.zpasnew.Model.ResultSPK;
import com.ggf.zpasnew.Model.ResultTK;

import java.util.List;

public class Value {

    String value;
    String message;

    List<ResultSPK> result;
    List<ResultDataRealAktifitas>resultRealAktifitas;
    List<ResultMandor> resultmandorID;

    public List<ResultMandor> getResultmandorID() {
        return resultmandorID;
    }

    public List<ResultDataRealAktifitas> getResultRealAktifitas() {
        return resultRealAktifitas;
    }

    public List<ResultTK> getResultKIT() {
        return resultKIT;
    }
    List<ResultTK> resultTK;
    List<ResultTK> resultTKData;

    public List<ResultTK> getResultTKData() {
        return resultTKData;
    }

    List<ResultTK> resultKIT;
    List<ResultJumlahTK> JmlhTK;

    public List<ResultJumlahTK> getJmlhTK() {
        return JmlhTK;
    }

    List<ResultInputSPK> resultSPK;
    List<ResultInputSPK> resultAktifitass;

    public List<ResultInputSPK> getResultAktifitass() {
        return resultAktifitass;
    }

    public List<ResultInputSPK> getResultSPK() {
        return resultSPK;
    }

    public List<ResultTK> getResultTK() {
        return resultTK;
    }

    List<ResultAktifitas> resultAktifitas;
    List<ResultAktifitas>resultAllAktifitas;
    List<ResultAktifitas>resultnamaspk;

    public List<ResultAktifitas> getResultnamaspk() {
        return resultnamaspk;
    }

    public List<ResultAktifitas> getResultAllAktifitas() {
        return resultAllAktifitas;
    }

    public List<ResultAktifitas> getResultAktifitas() {
        return resultAktifitas;
    }

    public List<ResultSPK> getResult() {
        return result;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
