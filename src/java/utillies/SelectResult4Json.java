package utillies;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class SelectResult4Json {

    public static String getJsonResult(Class<?> resultObjectClss, ResultSet resultSet, HashMap<String, String> fieldDatabaseMap) {
        try {
            if (resultSet.last()) {
                resultSet.beforeFirst();
            } else {
                return null;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block

        }

        Field[] fields = resultObjectClss.getDeclaredFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        try {
            long a = System.currentTimeMillis();
            while (resultSet.next()){
                stringBuilder.append("{");
                for (Field field : fields) {
                    switch (field.getType().getSimpleName()) {
                        case "int":
                            stringBuilder.append("\"").append(field.getName()).append("\":").append(resultSet.getInt(fieldDatabaseMap.get(field.getName()))).append(",");
                            break;
                        case "float":
                            stringBuilder.append("\"").append(field.getName()).append("\":").append(resultSet.getFloat(fieldDatabaseMap.get(field.getName()))).append(",");
                            break;
                        case "double":
                            stringBuilder.append("\"").append(field.getName()).append("\":").append(resultSet.getDouble(fieldDatabaseMap.get(field.getName()))).append(",");
                            break;
                        default:
                            stringBuilder.append("\"").append(field.getName()).append("\":\"").append(resultSet.getString(fieldDatabaseMap.get(field.getName()))).append("\",");
                            break;
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("},");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
            System.out.println(System.currentTimeMillis() - a);
        } catch (SQLException e) {
        }
        return stringBuilder.toString();
    }

    public static String getJsonResult(HashMap<String, String> fieldTypeMap, ResultSet resultSet, HashMap<String, String> fieldDatabaseMap) {
        try {
            if (resultSet.last()) {
                resultSet.beforeFirst();
            } else {
                return null;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block

        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        try {
            long a = System.currentTimeMillis();
            while (resultSet.next()) {
                stringBuilder.append("{");
                for (String field : fieldTypeMap.keySet()) {
                    String fieldType = fieldTypeMap.get(field).toLowerCase();
                    switch (fieldType) {
                        case "int":
                            stringBuilder.append("\"").append(field).append("\":").append(resultSet.getInt(fieldDatabaseMap.get(field))).append(",");
                            break;
                        case "float":
                            stringBuilder.append("\"").append(field).append("\":").append(resultSet.getFloat(fieldDatabaseMap.get(field))).append(",");
                            break;
                        case "double":
                            stringBuilder.append("\"").append(field).append("\":").append(resultSet.getDouble(fieldDatabaseMap.get(field))).append(",");
                            break;
                        default:
                            stringBuilder.append("\"").append(field).append("\":\"").append(resultSet.getString(fieldDatabaseMap.get(field))).append("\",");
                            break;
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("},");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
            System.out.println(System.currentTimeMillis() - a);
        } catch (SQLException e) {
        }
        return stringBuilder.toString();
    }

    public static String getJsonResult(ResultSet resultSet) throws SQLException {
//		try {
//			if(resultSet.last()) {
//				resultSet.beforeFirst();
//			}else {
//				return null;
//			}
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        try {
            long a = System.currentTimeMillis();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int length = metaData.getColumnCount();
            while (resultSet.next()) {
                 stringBuilder.append("{");
                for (int i = 1; i <= length; i++) {
                    String fieldType = metaData.getColumnTypeName(i).toUpperCase();
                    String fieleLable = metaData.getColumnLabel(i);
                    switch (fieldType) {
                        case "NUMBER":
                            stringBuilder.append("\"").append(fieleLable).append("\":").append(resultSet.getFloat(fieleLable)).append(",");
                            break;
                        case "DOUBLE":
                            stringBuilder.append("\"").append(fieleLable).append("\":").append(resultSet.getDouble(fieleLable)).append(",");
                            break;
                        case "INTEGER":
                        case "SMALLINT":
                            stringBuilder.append("\"").append(fieleLable).append("\":").append(resultSet.getInt(fieleLable)).append(",");
                            break;
                        case "DECIMAL":
                        case "NUMERIC":
                            stringBuilder.append("\"").append(fieleLable).append("\":").append(resultSet.getDouble(fieleLable)).append(",");
                            break;
                        case "VARCHAR2":
                            stringBuilder.append("\"").append(fieleLable).append("\":").append("\"").append(resultSet.getString(fieleLable)).append("\",");
                            break;
                        default:
                            stringBuilder.append("\"").append(fieleLable).append("\":").append("\"").append(resultSet.getString(fieleLable)).append("\",");
                            break;
                    }
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringBuilder.append("},");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            stringBuilder.append("]");
            System.out.println(System.currentTimeMillis() - a);
        } catch (SQLException e) {
            return e.getMessage();
        }
        return stringBuilder.toString();
    }

}
