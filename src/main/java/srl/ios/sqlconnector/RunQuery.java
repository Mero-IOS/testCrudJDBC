package srl.ios.sqlconnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public final class RunQuery {
    private RunQuery() {
    }

    public static void runPreparedStatement(String query, String... columns) {
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            for (int i = 0; i < columns.length; i++) {
                pStatement.setString(i + 1, columns[i]);
            }
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateRecordById(String query, int id, String... columns) {
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            for (int i = 0; i < columns.length; i++) {
                pStatement.setString(i + 1, columns[i]);
            }
            pStatement.setInt(columns.length + 1, id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getRecordById(String query, int id) {
        ResultSet rs = null;
        String result = null;
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            pStatement.setInt(1, id);
            rs = pStatement.executeQuery();
            result = stringFromResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static String getRecordByFieldValue(String query, String toFind) {
        ResultSet rs = null;
        String result = null;
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            pStatement.setString(1, toFind);
            rs = pStatement.executeQuery();
            result = stringFromResultSet(rs);
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    public static int getIndexByFieldValue(String query, String toFind) {
        ResultSet rs = null;
        int index = -1;
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            pStatement.setString(1, toFind);
            rs = pStatement.executeQuery();
            if (rs.next()) {
                index = rs.getInt("id");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return index;
    }

    public static void deleteRecordById(String query, int id) {
        try (PreparedStatement pStatement = SqlConnection.getConnection().prepareStatement(query)) {
            pStatement.setInt(1, id);
            int rowsAffected = pStatement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("Nessun record eliminato.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String stringFromResultSet(ResultSet rs) throws SQLException {
        String record = null;
        if (rs.next()) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                Object value = rs.getObject(i);
                sb.append(columnName).append(": ").append(value).append(", ");
            }
            sb.delete(sb.length() - 2, sb.length());
            record = sb.toString();
        }
        return record;
    }

}
