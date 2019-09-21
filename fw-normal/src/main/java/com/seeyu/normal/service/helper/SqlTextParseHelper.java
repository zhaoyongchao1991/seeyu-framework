package com.seeyu.normal.service.helper;

import com.seeyu.lang.utils.IOUtils;
import com.seeyu.normal.config.GlobalConfig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author seeyu
 * @date 2019/6/6
 */
public class SqlTextParseHelper {

    private static final String SQL_END_TAG = ";";

    private String sqlFilePath;
    private boolean noting = false;

    public SqlTextParseHelper(String sqlFilePath){
        this.sqlFilePath = sqlFilePath;
    }


    public List<String> parseSqlFile() throws IOException {
        List<String> sqls = new LinkedList<>();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(GlobalConfig.loadClassPathResourceAsStream(sqlFilePath)));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
                 line = line.trim();
                if(line.length() == 0 || isSqlNote(line)){
                    continue;
                }
                sb.append(line).append("\n");
                if(isSqlEnd(line)){
                    String sql = sb.toString();
                    sqls.add(sql.substring(0, sql.length()-2));
                    sb = new StringBuilder();
                }
            }
        }
        finally {
            IOUtils.closeStream(br);
        }
        return sqls;
    }

    private boolean isSqlEnd(String line){
        return line.trim().endsWith(SQL_END_TAG);
    }

    private boolean isSqlNote(String line){
        if(isNoteLine(line)){
            return true;
        }
        boolean isNote = this.noting = this.noting || isNoteStart(line);
        if(isNoteEnd(line)){
            this.noting = false;
        }
        return isNote;
    }



    private boolean isNoteLine(String line){
        return line.startsWith("-- ");
    }

    private boolean isNoteStart(String line){
        return line.startsWith("/*");

    }

    private boolean isNoteEnd(String line){
        return line.endsWith("*/");
    }



}
