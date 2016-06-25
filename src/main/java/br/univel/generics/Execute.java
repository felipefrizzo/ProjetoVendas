package br.univel.generics;

import br.univel.annotation.Column;
import br.univel.annotation.SerialUID;
import br.univel.annotation.Table;
import br.univel.database.SqlGenerator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by felipefrizzo on 4/20/16.
 */
public class Execute extends SqlGenerator {
    public Execute() {
    }

    @Override
    public String getCreateTable(Connection con, Object obj) {
        try {
            String nameTable;
            Class<?> cl = obj.getClass();

            StringBuilder sb = new StringBuilder();

            if (cl.isAnnotationPresent(Table.class)) {
                Table annotationTable = cl.getAnnotation(Table.class);
                nameTable = annotationTable.value();
            } else {
                nameTable = cl.getSimpleName().toUpperCase();
            }
            sb.append("CREATE TABLE ").append(nameTable).append(" (");

            Field[] attributes = cl.getDeclaredFields();

            for (int i = 0; i < attributes.length; i++) {
                Field field = attributes[i];

                String nameColumn = "";
                String typeColumn = null;

                if (!field.isAnnotationPresent(SerialUID.class)) {
                    if (field.isAnnotationPresent(Column.class)) {
                        Column annotationColumn = field.getAnnotation(Column.class);

                        if (annotationColumn.name().isEmpty()) {
                            nameColumn = field.getName().toUpperCase();
                        } else {
                            nameColumn = annotationColumn.name();
                        }
                    } else {
                        nameColumn = field.getName().toUpperCase();
                    }

                    Class<?> typeParemetros = field.getType();

                    if (typeParemetros.equals(String.class)) {
                        if (field.getAnnotation(Column.class).size() > -1) {
                            typeColumn = "VARCHAR(" + field.getAnnotation(Column.class).size() + ")";
                        } else {
                            typeColumn = "VARCHAR(254)";
                        }
                    } else if (typeParemetros.equals(int.class)){
                        if (field.getAnnotation(Column.class).pk()) {
                            typeColumn = "SERIAL";
                        } else {
                            typeColumn = "INT";
                        }
                    } else if (typeParemetros.isEnum()) {
                        typeColumn = "INT";
                    } else if (typeParemetros.equals(BigDecimal.class)) {
                        typeColumn = "DECIMAL";
                    }

                    if (i > 1) sb.append(",");

                    sb.append("\n\t").append(nameColumn).append(" ").append(typeColumn);
                }
            }

            sb.append(",\n\tPRIMARY KEY(");
            for (int y = 0; y < attributes.length; y++) {
                int get = 0;
                Field fields = attributes[y];
                if (!fields.isAnnotationPresent(SerialUID.class)) {
                    if (fields.isAnnotationPresent(Column.class)) {
                        Column annotationColumn = fields.getAnnotation(Column.class);

                        if (annotationColumn.pk()) {
                            if (get > 0) sb.append(", ");

                            if (annotationColumn.name().isEmpty()) {
                                sb.append(fields.getName().toUpperCase());
                            } else {
                                sb.append(annotationColumn.name());
                            }
                            get++;
                        }
                    }
                    if (y == attributes.length - 1) {
                        sb.append(")");
                    }
                }
            }
            sb.append("\n);");

            String create = sb.toString();
            Statement execute = con.createStatement();
            execute.executeUpdate(create);

            return create;

        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getDropTable(Connection con, Object obj) {
        try {
            String nameTable;
            StringBuilder sb = new StringBuilder();

            Class<?> cl = obj.getClass();

            if (cl.isAnnotationPresent(Table.class)) {
                Table table = cl.getAnnotation(Table.class);
                nameTable = table.value();
            } else {
                nameTable = cl.getSimpleName().toUpperCase();
            }

            sb.append("DROP TABLE ").append(nameTable).append(";");
            String drop = sb.toString();

            Statement execute = con.createStatement();
            execute.executeUpdate(drop);
            return drop;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public PreparedStatement getSqlInsert(Connection con, Object obj) {
        Class<?> cl = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String nameTable;

        if (cl.isAnnotationPresent(Table.class)) {
            Table table = cl.getAnnotation(Table.class);
            nameTable = table.value();
        } else {
            nameTable = cl.getSimpleName().toUpperCase();
        }

        sb.append("INSERT INTO ").append(nameTable).append(" (");

        Field[] attributes = cl.getDeclaredFields();

        for (int i = 0; i < attributes.length; i++) {
            Field field = attributes[i];
            String nameColumn;
            if (!field.isAnnotationPresent(SerialUID.class) && !field.getAnnotation(Column.class).pk()) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column.name().isEmpty()) {
                        nameColumn = field.getName().toUpperCase();
                    } else {
                        nameColumn = column.name();
                    }
                } else {
                    nameColumn = field.getName().toUpperCase();
                }

                if (i > 2) {
                    sb.append(", ");
                }

                sb.append(nameColumn);
            }
        }

        sb.append(") VALUES (");

        for (int i = 0; i < attributes.length; i++) {
            Field field = attributes[i];
            if (!field.isAnnotationPresent(SerialUID.class) && !field.getAnnotation(Column.class).pk()) {
                if (i > 2) sb.append(", ");

                sb.append("?");
            }
        }
        sb.append(")");
        String insert = sb.toString();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(insert);

            for (int i = 0; i < attributes.length; i++) {
                Field field = attributes[i];
                Object type = field.getType();

                field.setAccessible(true);
                if (!field.isAnnotationPresent(SerialUID.class) && !field.getAnnotation(Column.class).pk()) {
                    if (type.equals(int.class)) {
                        ps.setInt(i - 1, field.getInt(obj));
                    } else if (type.equals(String.class)) {
                        ps.setString(i - 1, String.valueOf(field.get(obj)));
                    } else if (field.getType().isEnum()) {
                        Object value = field.get(obj);
                        Method m = value.getClass().getMethod("ordinal");
                        ps.setInt(i - 1, (Integer) m.invoke(value, null));
                    } else if (type.equals(BigDecimal.class)) {
                        ps.setBigDecimal(i - 1, BigDecimal.valueOf((Double) field.get(obj)));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return ps;
    }

    @Override
    public PreparedStatement getSqlSelectAll(Connection con, Object obj) {
        Class<?> cl = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String nameTable;

        if (cl.isAnnotationPresent(Table.class)) {
            nameTable = cl.getAnnotation(Table.class).value();
        } else {
            nameTable = cl.getSimpleName().toUpperCase();
        }
        sb.append("SELECT * FROM ").append(nameTable).append(";");

        String select = sb.toString();
        System.out.println(select);
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    @Override
    public PreparedStatement getSqlSelectById(Connection con, Object obj, int id) {
        Class<?> cl = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String nameTable;

        if (cl.isAnnotationPresent(Table.class)) {
            nameTable = cl.getAnnotation(Table.class).value();
        } else {
            nameTable = cl.getSimpleName().toUpperCase();
        }

        sb.append("SELECT * FROM ").append(nameTable).append(" WHERE ID=").append(id).append(";");
        String select = sb.toString();
        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(select);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    @Override
    public PreparedStatement getSqlUpdateById(Connection con, Object obj, int id) {
        Class<?> cl = obj.getClass();
        StringBuilder sb = new StringBuilder();
        String nameTable;

        if (cl.isAnnotationPresent(Table.class)) {
            nameTable = cl.getAnnotation(Table.class).value();
        } else {
            nameTable = cl.getSimpleName().toUpperCase();
        }

        sb.append("UPDATE ").append(nameTable).append(" SET ");

        Field[] attributes = cl.getDeclaredFields();

        for (int i = 0; i < attributes.length; i++) {
            Field field = attributes[i];
            String nameColumn;
            if (!field.isAnnotationPresent(SerialUID.class)) {
                if (field.isAnnotationPresent(Column.class)) {
                    Column column = field.getAnnotation(Column.class);
                    if (column.name().isEmpty()) {
                        nameColumn = field.getName().toUpperCase();
                    } else {
                        nameColumn = column.name();
                    }
                } else {
                    nameColumn = field.getName().toUpperCase();
                }

                if (i > 1) {
                    sb.append(", ");
                }

                sb.append(nameColumn).append(" = ?");
            }
        }
        sb.append(" WHERE ID = ").append(id);
        String update = sb.toString();

        PreparedStatement ps = null;

        try {
            ps = con.prepareStatement(update);

            for (int i = 0; i < attributes.length; i++) {
                Field field = attributes[i];
                Object type = field.getType();

                field.setAccessible(true);
                if (!field.isAnnotationPresent(SerialUID.class)) {
                    if (type.equals(int.class)) {
                        ps.setInt(i, field.getInt(obj));
                    } else if (type.equals(String.class)) {
                        ps.setString(i, String.valueOf(field.get(obj)));
                    } else if (field.getType().isEnum()) {
                        Object value = field.get(obj);
                        Method m = value.getClass().getMethod("ordinal");
                        ps.setInt(i, (Integer) m.invoke(value, null));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return ps;
    }

    @Override
    public PreparedStatement getSqlDeleteById(Connection con, Object obj, int id) {
        PreparedStatement ps = null;
        try {
            Class<?> cl = obj.getClass();
            StringBuilder sb = new StringBuilder();
            String nameTable;

            if (cl.isAnnotationPresent(Table.class)) {
                nameTable = cl.getAnnotation(Table.class).value();
            } else {
                nameTable = cl.getSimpleName().toUpperCase();
            }

            sb.append("DELETE FROM ").append(nameTable).append(" WHERE ID = ").append(id).append(";");
            String delete = sb.toString();

            ps = con.prepareStatement(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }
}
