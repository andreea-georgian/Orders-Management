package dataAccess;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

public class AbstractDataAccess<T> {
    Logger logger = Logger.getLogger(AbstractDataAccess.class.getName());
    Class<T> type;

    /**
     * Constructor fara parametrii pentru crearea obiectului
     */
    public AbstractDataAccess() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Metoda populeaza un tabel cu datele listei de obiecte
     * @param table
     * @param t
     * @throws IntrospectionException
     */
    public void populateTable(JTable table, List<T> t) throws IntrospectionException {
        Field[] declaredFields = type.getDeclaredFields();
        DefaultTableModel model = new DefaultTableModel();
        table.setModel(model);
        for (Field f : declaredFields) {
            model.addColumn(f.getName());
        }
        for (T aux : t) {
            Vector<Object> newRow = new Vector<>();
            for (Field f : declaredFields) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(f.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    try {
                        newRow.add(method.invoke(aux));
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
            }
            model.addRow(newRow);
        }
    }

    /**
     * Metoda returneaza un String care reprezinta tiparul query-ului select
     * @param field
     * @return
     */
    public String createSelectQuery(String field) {
        String sb = "SELECT * FROM ";
        sb += type.getSimpleName();
        sb += (" WHERE " + field + " =?");
        return sb;
    }

    /**
     * Metoda returneaza un String care reprezinta tiparul query-ului insert
     * @return
     */
    public String createInsertQuery() {
        String sb = "INSERT INTO ";
        sb += type.getSimpleName();
        sb += "(";
        Field[] declaredFields = type.getDeclaredFields();
        for (int i = 0; i < declaredFields.length; i++) {
            if (i == declaredFields.length - 1)
                sb += declaredFields[i].getName() + ") ";
            else
                sb += declaredFields[i].getName() + ", ";
        }
        sb += "VALUES (";
        for (int i = 0; i < declaredFields.length; i++) {
            if (i == declaredFields.length - 1)
                sb += "?)";
            else
                sb += "?, ";
        }
        return sb;
    }

    /**
     * Metoda returneaza un String care reprezinta tiparul query-ului delete
     * @return
     */
    public String createDeleteQuery() {
        return "DELETE FROM " + type.getSimpleName() + " WHERE id = ?";
    }

    /**
     * Metoda returneaza un String care reprezinta tiparul query-ului update
     * @param fieldName
     * @return
     */
    public String createUpdateQuery(String fieldName) {
        return "UPDATE " + type.getSimpleName() + " SET " + fieldName + " = ? WHERE ID = ?";
    }

    /**
     * Metoda returneaza obiectul care are id-ul dat ca parametru
     * @param id
     * @return
     */
    public T findByID(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery("id");

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            return createObjects(resultSet).get(0);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda returneaza lista de elemente existente de un anumit tip
     * @return
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda transforma rezultatul unui query intr-o lista de obiecte
     * @param resultSet
     * @return
     */
    public List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] constructors = type.getDeclaredConstructors();
        Constructor constructor = null;

        for (Constructor ctor : constructors)
            if (ctor.getGenericParameterTypes().length == 0) {
                constructor = ctor;
                break;
            }
        try {
            while (resultSet.next()) {
                constructor.setAccessible(true);
                T instance = (T) constructor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda insereaza in baza de date un obiect si returneaza cheia generata
     * @param t
     * @return
     */
    public int insert(T t) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createInsertQuery();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int insertedID = -1;
        try {
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            int i = 0;
            for (Field field : type.getDeclaredFields()) {
                i++;
                String fieldName = field.getName();
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                Method method = propertyDescriptor.getReadMethod();
                statement.setObject(i, method.invoke(t));
            }
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                insertedID = resultSet.getInt(1);
            }
        } catch (SQLException | IntrospectionException throwables) {
            throwables.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
            ConnectionFactory.close(resultSet);
        }
        return insertedID;
    }

    /**
     * Metoda sterge obiectul care are id-ul dat ca parametru din baza de date
     * @param id
     */
    public void delete(int id) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createDeleteQuery();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Metoda modifica in baza de date field-ul obiectului cu id-ul dat
     * @param id
     * @param fieldName
     * @param newValue
     */
    public void update(int id, String fieldName, Object newValue) {
        Connection connection = ConnectionFactory.getConnection();
        String query = createUpdateQuery(fieldName);
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(query);
            statement.setObject(1, newValue);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
