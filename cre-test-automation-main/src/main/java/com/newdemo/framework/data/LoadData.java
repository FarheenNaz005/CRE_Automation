package com.newdemo.framework.data;

import java.io.FileInputStream;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.newdemo.framework.base.Utilites;
import com.newdemo.framework.base.BaseSetupClass;


public class LoadData {
    Utilites functions = null;
    BaseSetupClass enviornment;

    //===================DECLARE PARAMETERIZATION VARIABLES======================
    HSSFWorkbook objWB = null;
    HashMap hmParamsNValues = null;
    int intParamInputRow = 0;

    //====================FIELD VARIABLES DECLARATION=============================
    public String strDTURL = "";
    public String strDTUserName = "";
    public String strDTPassword = "";
    public String strDTSearch = "";

    public LoadData(String strParametersNValues) throws Exception {
        functions = new Utilites();


        //=============================PARAMETERIZATION STEPS==========================================
        hmParamsNValues = functions.splitNStoreParamsInHashMap(strParametersNValues);
        intParamInputRow = Integer.parseInt((String) hmParamsNValues.get("InputDataRow"));

        objWB = new HSSFWorkbook(new FileInputStream(BaseSetupClass.getEnviornment()));

        HSSFSheet objSHInputSheet = objWB.getSheet("CRE");
        strDTURL = functions.getCellValueForRowNum(objSHInputSheet, "URL", intParamInputRow);
        //System.out.println(strDTURL);
        strDTUserName = functions.getCellValueForRowNum(objSHInputSheet, "UserName", intParamInputRow);
        strDTPassword = functions.getCellValueForRowNum(objSHInputSheet, "Password", intParamInputRow);
        strDTSearch = functions.getCellValueForRowNum(objSHInputSheet, "searchValue", intParamInputRow);
    }

}
