package dao;

import connection.ConnectionDB;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AbstractDAO<T> {
    private final Class<T> type;

    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private String createSelectQuery (String field){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" *");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE " + field + " =?");
        return sb.toString();

    }

    private String createSelectQuery (){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT");
        sb.append(" *");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        return sb.toString();

    }

    private String createInsertQuery (T t){
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT");
        sb.append(" INTO ");
        sb.append(type.getSimpleName());
        sb.append(" (");

        Field[]fields = type.getDeclaredFields();
        sb.append(fields[0].getName());

        for(int i = 1; i < fields.length; i++){
            sb.append(", " + fields[i].getName());
        }

        sb.append(" )");
        sb.append(" VALUES (");

        try {
            fields[0].setAccessible(true);
            Object value = fields[0].get(t);
            sb.append("'" + value + "'");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }



        for(int i = 1; i < fields.length; i++){

            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(t);
                sb.append(", '" + value + "'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }

        sb.append(" )");

        return sb.toString();

    }

    private String createUpdateQuery(int oldId,T t){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field []fields = type.getDeclaredFields();
        sb.append(fields[0].getName() + " = ");
        try {
            fields[0].setAccessible(true);
            Object value = fields[0].get(t);
            sb.append("'" + value + "'");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(int i = 1; i < fields.length; i++){
            sb.append(", " + fields[i].getName() + " = ");
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(t);
                sb.append("'" + value + "'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append(" WHERE (id = '" + oldId + "')");

        return sb.toString();
    }

    private String createUpdateQuery(String oldUsername,T t){
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field []fields = type.getDeclaredFields();
        sb.append(fields[0].getName() + " = ");
        try {
            fields[0].setAccessible(true);
            Object value = fields[0].get(t);
            sb.append("'" + value + "'");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        for(int i = 1; i < fields.length; i++){
            sb.append(", " + fields[i].getName() + " = ");
            try {
                fields[i].setAccessible(true);
                Object value = fields[i].get(t);
                sb.append("'" + value + "'");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        sb.append(" WHERE (username = '" + oldUsername + "')");



        return sb.toString();
    }

    private String createDeleteQuery(int id){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE (id = '" + id + "')");

        return sb.toString();
    }

    private String createDeleteQuery(String username){
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE (username = '" + username + "')");

        return sb.toString();
    }

    private List<T> createObjects (ResultSet resultSet){
        List<T> list = new ArrayList<T>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;

        for(int i = 0; i < constructors.length; i++){
            constructor = constructors[i];
            if(constructor.getGenericParameterTypes().length == 0)
                break;
        }

        try{
            while (resultSet.next()){
                constructor.setAccessible(true);
                T instance = (T)constructor.newInstance();
                for(Field field : type.getDeclaredFields()){
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName,type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance,value);
                }
                list.add(instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return  list;
    }

    public T findById(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resulSet = null;
        String query = createSelectQuery("id");

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            resulSet = statement.executeQuery();

            return createObjects(resulSet).get(0);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(resulSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }

        return null;

    }

    public List<T> findAll(){

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resulSet = null;
        String query = createSelectQuery();

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            resulSet = statement.executeQuery();

            return createObjects(resulSet);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(resulSet);
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }

        return null;
    }

    public T insert (T t){

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createInsertQuery(t);

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }

        return null;
    }

    public T update(int oldId,T t){

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(oldId,t);

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }

        return null;
    }

    public T update(String oldUsername,T t){

        Connection connection = null;
        PreparedStatement statement = null;
        String query = createUpdateQuery(oldUsername,t);

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

            return t;

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }

        return null;
    }

    public void delete(int id){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(id);

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }

    public void delete(String username){
        Connection connection = null;
        PreparedStatement statement = null;
        String query = createDeleteQuery(username);

        try{

            connection = ConnectionDB.createConnection();
            statement = connection.prepareStatement(query);
            statement.executeUpdate();

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            ConnectionDB.close(statement);
            ConnectionDB.close(connection);
        }
    }
}
