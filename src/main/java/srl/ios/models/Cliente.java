package srl.ios.models;

import srl.ios.sqlconnector.RunQuery;

public final class Cliente {
    private static String tableName = "clienti";

    private Cliente() {
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

    public static String read(int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE ID = ?;";
        return RunQuery.getRecordById(sql, id);
    }

    public static void update(int id, String[] fields) {
        if (fields.length != 1) {
            System.out.println("Cliente deve avere un singolo campo.");
        } else {
            String sql = "UPDATE " + tableName + " SET ragione_sociale = ? WHERE id = ?;";
            RunQuery.updateRecordById(sql, id, fields[0]);
        }
    }

    public static void delete(int id) {
        String sql = "DELETE FROM " + tableName + " WHERE ID = ?;";
        RunQuery.deleteRecordById(sql, id);
    }

    public static String findRecord(String ragioneSociale) {
        String sql = "SELECT * FROM " + tableName + " WHERE ragione_sociale = ?;";
        return RunQuery.getRecordByFieldValue(sql, ragioneSociale);
    }

    public static int findId(String ragioneSociale) {
        String sql = "SELECT * FROM " + tableName + " WHERE ragione_sociale = ?;";
        return RunQuery.getIndexByFieldValue(sql, ragioneSociale);
    }

}
