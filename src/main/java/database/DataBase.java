package database;

import interfaces.InterfaceSearchPanel;
import pages.HHResultResumePage;

import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;


public class DataBase {

    public static Connection connection;
    public static Statement statement;

    public static void connectDateBase() {

        System.out.println("------- Проверка подключения -------");

        connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataBase.class.getName()).log(Level.SEVERE,
                    null, ex);
        }

        if (null != connection) {
            System.out.println("------- Подключение установлено -------");
        } else {
            System.out.println("------- Подключение НЕ установлено -------");
        }

    }
    public static void createDataBase(){
        System.out.println("Создание таблицы..");
        try{
            statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS hhbase1(id integer NOT NULL," +
                    " position text NOT NULL," +
                    " link text NOT NULL, hh_id text NOT NULL," +
                    "CONSTRAINT hhbase1_pkey PRIMARY KEY (id));");
            System.out.println("Таблица создана..");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertDataBase() {
        try {
            System.out.println("Запись данных в бд.");
            statement = null;
            for (int i = 1; i <= InterfaceSearchPanel.countResume; i++) {
                PreparedStatement insertSQL = connection.prepareStatement
                        ("INSERT INTO hhbase1 VALUES (?,?,?,?)");
                statement = connection.createStatement();
                int countid = -1;
                ResultSet resultSet = statement.executeQuery
                        ("SELECT count(*) as countid from hhbase1");
                if (resultSet.next()) {
                    String temp = resultSet.getString("countid");
                    countid = Integer.parseInt(temp);
                    insertSQL.setInt(1, countid + 1);
                    insertSQL.setString(2, String.valueOf(HHResultResumePage.resumeItem.get(i - 1).getText()));
                    insertSQL.setString(3, HHResultResumePage.url.get(i - 1));
                    insertSQL.setString(4, HHResultResumePage.idHH.get(i - 1));
                    System.out.println("Запись добавлена.");
                    insertSQL.executeUpdate();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void saveListResume() throws IOException, SQLException {
        Statement statement = null;
        statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery
                ("SELECT id, position, link from hhbase1");

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.TXT","*.*");
        JFileChooser fc = new JFileChooser();
        fc.setFileFilter(filter);
        FileWriter fw;
        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
            File file = fc.getSelectedFile();
            fw = new FileWriter(file);
            while (resultSet.next()){
                int i = resultSet.getInt("id");
                String position = resultSet.getString("position");
                String hh_link = resultSet.getString("link");
                fw.write(i+". "+position+" "+hh_link+"\r\n");
            }
            fw.flush();
            fw.close();
        }

    }
   /* public static void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }*/


}


