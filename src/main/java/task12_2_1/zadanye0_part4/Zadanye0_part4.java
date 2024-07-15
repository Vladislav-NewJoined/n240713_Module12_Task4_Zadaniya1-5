package task12_2_1.zadanye0_part4;

import java.sql.*;

public class Zadanye0_part4 {
    public static void main(String[] args) {
        System.out.println("""
            Задание:\s
            Модуль 12. Базы данных и Git. Задание №2:\s
            Цель задания: знакомство и формирование базовых навыков с по работе  с SQLite\s
                Задание:
                2. Напишите запрос для отображения имен (first_name, last_name), используя псевдонимы «Имя»,
                   «Фамилия».  (Пример таблицы  см. таблица 1)
            
                Решение:
            \s""");

        String url = "jdbc:sqlite:src/main/java/task12_2_1/sqlite.dbase";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            // Создание таблицы 'Users'
            String createTableQuery = "create table if not exists Users (" +
                    "id integer primary key autoincrement, " +
                    "first_name varchar(20) not null, " +
                    "last_name varchar(20) not null);";
            System.out.println(createTableQuery);
            stmt.execute(createTableQuery);

            // Вставка данных в таблицу 'Users'
            String insertDataQuery = "insert into Users (first_name, last_name) values " +
                    "('Petya', 'Ivanov'), " +
                    "('Vasya', 'Petrov'), " +
                    "('Katya', 'Sidorova'), " +
                    "('Sasha', 'Chernov'), " +
                    "('Pasha', 'Belov'), " +
                    "('Misha', 'Smirnov');";
            System.out.println(insertDataQuery);
            stmt.execute(insertDataQuery);

            // Запрос на выборку данных
            String selectQuery = "select id, first_name, last_name from Users where first_name like '%Petya%'";
            System.out.println(selectQuery);
            ResultSet rs = stmt.executeQuery(selectQuery);

            // Вывод результатов запроса
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                System.out.println("ID: " + id + ", Имя: " + first_name + ", Фамилия: " + last_name);
            }

            System.out.println("Выборка данных выполнена успешно.");

        } catch (SQLException e) {
            System.err.println("Ошибка выполнения SQL запроса: " + e.getMessage());
        }
    }
}