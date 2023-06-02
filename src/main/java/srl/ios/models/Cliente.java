package srl.ios.models;

import srl.ios.sqlconnector.RunQuery;

public final class Cliente extends GenericRecord {
    private static String tableName = "clienti";

    public Cliente(int id) {
        super(tableName, id);
    }

    public Cliente(String ragioneSociale) {
        super(tableName, "ragione_sociale", ragioneSociale);
    }

    public static void insert(String[] fields) {
        if (fields == null || fields.length != 1) {
            System.out.println("Cliente deve avere un singolo campo.");
        } else {
            String clienteTrovato = findRecord(fields[0]);
            if (findRecord(fields[0]) == null) {
                String sql = "INSERT INTO " + tableName + " (ragione_sociale) VALUES (?);";
                RunQuery.runPreparedStatement(sql, fields[0]);
            } else {
                System.out.println("Cliente pre-esistente non reinserito: \n" + clienteTrovato);
            }
        }
    }

    public static String getStringified(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE ID = ?;";
        return RunQuery.stringifyRecordById(sql, id);
    }

    public static void update(int id, String[] fields) {
        if (fields.length != 1) {
            System.out.println("Cliente deve avere un singolo campo.");
        } else {
            String sql = "UPDATE " + tableName + " SET ragione_sociale = ? WHERE id = ?;";
            RunQuery.updateRecordById(sql, id, fields[0]);
        }
    }

    public static String findRecord(String ragioneSociale) {
        String sql = "SELECT * FROM " + tableName + " WHERE ragione_sociale = ?;";
        return RunQuery.getStringByFieldValue(sql, ragioneSociale);
    }

    public static int find(String ragioneSociale) {
        String sql = "SELECT * FROM " + tableName + " WHERE ragione_sociale = ?;";
        return RunQuery.getIndexByFieldValue(sql, ragioneSociale);
    }

}
