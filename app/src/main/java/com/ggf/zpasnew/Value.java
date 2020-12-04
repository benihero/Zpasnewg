package com.ggf.zpasnew;

import java.util.List;

public class Value {

    String value;
    String message;
    List<ResultSPK> result;

    public List<ResultTK> getResultKIT() {
        return resultKIT;
    }
    List<ResultTK> resultTK;
    List<ResultTK> resultKIT;

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
