package srl.ios.models;

import java.util.Map;

import srl.ios.sqlconnector.RecordController;
import srl.ios.sqlconnector.RunQuery;

public class GenericRecord implements RecordController {
    private String table;
    private Integer index;
    private Map<String, String> recordMap = null;
    private String recordString = null;

    public GenericRecord(String tableName, int id) {
        index = id;
        table = tableName;
    }

    public GenericRecord(String tableName, String columnName, String columnValue) {
        table = tableName;
        index = find(columnName, columnValue);
    }

    public Map<String, String> getRecordMap() {
        if (recordMap == null) {
            String query = "SELECT * FROM " + table + "FROM my_table WHERE id = ?";
            try {
                recordMap = RunQuery.mapRecordById(query, index);
            } catch (Exception e) {
                System.out.println("could not retrieve " + index + " from " + table);
            }
        }
        return recordMap;
    }

    public String getRecordString() {
        if (recordString == null) {
            String query = "SELECT * FROM " + table + "FROM my_table WHERE id = ?";
            try {
                recordString = RunQuery.stringifyRecordById(query, index);
            } catch (Exception e) {
                System.out.println("could not retrieve " + index + " from " + table);
            }
        }
        return recordString;
    }

    public int find(String columnName, String columnValue) {
        String sql = "SELECT * FROM " + table + " WHERE " + columnName + "= ?;";
        return RunQuery.getIndexByFieldValue(sql, columnValue);
    }

    public void delete() {
        RecordController.delete(table, index);
    }
}
