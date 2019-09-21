package com.seeyu.lang.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author nurteen_tyler
 */
public class ExcelUtils {

    /**
     * 从xlsx文件获取数据
     * @param file
     * @return
     * @throws Exception
     */
    public static List<List<String[]>> getDataFromXlsxFileSheet(File file) throws Exception{
        return getDataFromXlsxFileSheet(new FileInputStream(file));
    }


    /**
     * 从xlsx文件获取数据
     * @param input
     * @return
     * @throws Exception
     */
    public static List<List<String[]>> getDataFromXlsxFileSheet(InputStream input) throws Exception {
        List<List<String[]>> mdatas = new ArrayList();
        XSSFWorkbook hssfWorkbook = new XSSFWorkbook(input);
        for(int is = 0; is < hssfWorkbook.getNumberOfSheets(); ++is) {
            XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(is);
            List<String[]> datas = new ArrayList();
            for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); ++rowNum) {
                XSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if (hssfRow == null){
                    datas.add(null);
                }
                else{
                    String[] ss = new String[hssfRow.getPhysicalNumberOfCells()];
                    for(int cellNum = 0; cellNum < hssfRow.getPhysicalNumberOfCells(); ++cellNum) {
                        XSSFCell hssfCell = hssfRow.getCell(cellNum);
                        if (hssfCell != null) {
                            String value = null;
                            switch(hssfCell.getCellType()) {
                                case 0:
                                    if (HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                                        value = (new SimpleDateFormat("yyyy-MM-dd")).format(hssfCell.getDateCellValue());
                                    } else {
                                        Double tn = hssfCell.getNumericCellValue();
                                        if (tn == (double)tn.intValue()) {
                                            value = String.valueOf(tn.intValue());
                                        } else {
                                            value = String.valueOf(tn);
                                        }
                                    }
                                    break;
                                case 1:
                                    value = String.valueOf(hssfCell.getStringCellValue().trim());
                                    break;
                                case 2:
                                default:
                                    value = null;
                                    break;
                                case 3:
                                    value = "";
                                    break;
                                case 4:
                                    value = String.valueOf(hssfCell.getBooleanCellValue());
                            }
                            ss[cellNum] = value;
                        }
                    }
                    datas.add(ss);
                }
            }
            removeEndBlankRow(datas);
            mdatas.add(datas);
        }
        return mdatas;
    }



    /**
     * 从xls文件获取数据
     * @param file
     * @return
     * @throws Exception
     */
    public static List<List<String[]>> getDataFromXlsFileSheet(File file) throws Exception{
       return getDataFromXlsFileSheet(new FileInputStream(file));
    }

    /**
     * 从xls文件获取数据
     * @param input
     * @return
     * @throws Exception
     */
    public static List<List<String[]>> getDataFromXlsFileSheet(InputStream input) throws Exception{
        List<List<String[]>> mdatas=new ArrayList<List<String[]>>();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(input);
        for(int is=0;is<hssfWorkbook.getNumberOfSheets();is++){
            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(is);
            List<String[]> datas=new ArrayList<String[]>();
            for(int rowNum = 0; rowNum <= hssfSheet.getLastRowNum(); rowNum++){
                HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                if(hssfRow != null){
                    String[] ss=new String[hssfRow.getPhysicalNumberOfCells()];
                    for(int cellNum = 0; cellNum < hssfRow.getPhysicalNumberOfCells(); cellNum++){
                        HSSFCell hssfCell = hssfRow.getCell(cellNum);
                        if(hssfCell!=null){
                            String value=null;
                            switch(hssfCell.getCellType()){
                                case HSSFCell.CELL_TYPE_NUMERIC :
                                    if(HSSFDateUtil.isCellDateFormatted(hssfCell)) {
                                        value = new SimpleDateFormat("yyyy-MM-dd").format(hssfCell.getDateCellValue());
                                    }else{
                                        Double tn=hssfCell.getNumericCellValue();
                                        if(tn.doubleValue()==tn.intValue()){
                                            value=String.valueOf(tn.intValue());
                                        }else{
                                            value=String.valueOf(tn);
                                        }
                                    };break;
                                case HSSFCell.CELL_TYPE_BOOLEAN :
                                    value=String.valueOf(hssfCell.getBooleanCellValue()); break;
                                case HSSFCell.CELL_TYPE_BLANK :
                                    value=""; break;
                                case HSSFCell.CELL_TYPE_STRING :
                                    value=String.valueOf(hssfCell.getStringCellValue().trim()); break;
                                default : value=null;break;
                            }
                            ss[cellNum]=value;
                        }
                    }
                    datas.add(ss);
                }
            }
            removeEndBlankRow(datas);
            mdatas.add(datas);
        }
        return mdatas;
    }


    private static void removeEndBlankRow(List<String[]> data){
        for(int i = data.size()-1; i>=0; i--){
            String[] row = data.get(i);
            if(rowIsBlank(row)){
                data.remove(i);
            }
            else{
                break;
            }
        }
    }

    private static boolean rowIsBlank(String[] row){
        if(row == null){
            return true;
        }
        for(String cell : row){
            if(StringUtils.isNotBlank(cell)){
                return false;
            }
        }
        return true;
    }


}
