package com.ggf.zpasnew;

import com.ggf.zpasnew.Model.ResultAktifitas;
import com.ggf.zpasnew.Model.ResultCountSPK;
import com.ggf.zpasnew.Model.ResultDataRealAktifitas;
import com.ggf.zpasnew.Model.ResultInputSPK;
import com.ggf.zpasnew.Model.ResultJumlahTK;
import com.ggf.zpasnew.Model.ResultKegiatan;
import com.ggf.zpasnew.Model.ResultMandor;
import com.ggf.zpasnew.Model.ResultSPK;
import com.ggf.zpasnew.Model.ResultSumTk;
import com.ggf.zpasnew.Model.ResultSummary;
import com.ggf.zpasnew.Model.ResultTK;
import com.ggf.zpasnew.Model.ResultTKtemp;
import com.ggf.zpasnew.Model.ResultTargetHasil;
import com.ggf.zpasnew.Model.ResultTotalUpah;

import java.util.List;

public class Value {

    String value;

    List<ResultSPK> result;
    List<ResultTK> resultTKadd;
    List<ResultDataRealAktifitas> resultRealAktifitas;
    List<ResultMandor> resultmandorID;
    List<ResultCountSPK> resultsum;
    List<ResultTK> resultTK;
    List<ResultTK> resultTKData;
    List<ResultSumTk> resultsummatk;
    List<ResultTK> resultKIT;
    List<ResultKegiatan> resultKegiatan;
    List<ResultJumlahTK> JmlhTK;
    List<ResultJumlahTK> resultJumlahTKtemp;
    List<ResultInputSPK> resultSPK;
    List<ResultInputSPK> resultAktifitass;
    List<ResultAktifitas> resultAktifitas;
    List<ResultAktifitas> resultAllAktifitas;
    List<ResultAktifitas> resultJenisUpah;
    List<ResultAktifitas> resultJumlahTKtarget;
    List<ResultAktifitas> resultnamaspk;
    List<ResultSummary> resultAllSummary;
    List<ResultTKtemp> resultTKtemp;
    List<ResultTargetHasil> resultTargetHasil;
    List<ResultTotalUpah> JmlhUpahAktifitas;

    public List<ResultTotalUpah> getJmlhUpahAktifitas() {
        return JmlhUpahAktifitas;
    }
    public List<ResultAktifitas> getResultJumlahTKtarget() {
        return resultJumlahTKtarget;
    }
    public List<ResultTargetHasil> getResultTargetHasil() {
        return resultTargetHasil;
    }
    public List<ResultAktifitas> getResultJenisUpah() {
        return resultJenisUpah;
    }
    public List<ResultJumlahTK> getResultJumlahTKtemp() {
        return resultJumlahTKtemp;
    }
    public List<ResultTKtemp> getResultTKtemp() {
        return resultTKtemp;
    }
    public List<ResultTK> getResultTKadd() {
        return resultTKadd;
    }
    public List<ResultCountSPK> getResultsum() {
        return resultsum;
    }
    public List<ResultMandor> getResultmandorID() {
        return resultmandorID;
    }
    public List<ResultDataRealAktifitas> getResultRealAktifitas() {
        return resultRealAktifitas;
    }
    public List<ResultTK> getResultKIT() {
        return resultKIT;
    }
    public List<ResultSumTk> getResultSumTk() {
        return resultsummatk;
    }
    public List<ResultTK> getResultTKData() {
        return resultTKData;
    }
    public List<ResultKegiatan> getResultKegiatan() {
        return resultKegiatan;
    }
    public List<ResultJumlahTK> getJmlhTK() {
        return JmlhTK;
    }
    public List<ResultInputSPK> getResultAktifitass() {
        return resultAktifitass;
    }
    public List<ResultInputSPK> getResultSPK() {
        return resultSPK;
    }
    public List<ResultTK> getResultTK() {
        return resultTK;
    }
    public List<ResultAktifitas> getResultnamaspk() {
        return resultnamaspk;
    }
    public List<ResultSummary> getResultAllSummary() {
        return resultAllSummary;
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

}
