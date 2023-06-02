package srl.ios.sqlconnector;

public interface RecordController {

    public static void delete(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ?;";
        RunQuery.deleteRecordById(sql, id);
    }

    public static String findRecordString(String tableName, String column_name, String fieldValue) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + column_name + " = ?;";
        return RunQuery.getStringByFieldValue(sql, fieldValue);
    }

}
