import java.sql.*;
import java.util.Scanner;

public class Connect {
    private final String url = "jdbc:postgresql://localhost:5432/study_database";
    private final String user = "postgres";
    private final String password = "1007";
    Scanner scanner = new Scanner(System.in);
    public Connection connection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void addNews(News news) {
        String SQL = "INSERT INTO \"public\".news" +
                " (header, news_text)" +
                " VALUES (?, ?); ";
        try (Connection conn = connection();
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setString(1, news.getHeader());
            stmt.setString(2, news.getNews_text());
            stmt.executeUpdate();
            ResultSet rs = stmt.getResultSet();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewNews() {

        try{ Statement statment = connection().createStatement();
             String SQL = "SELECT header, news_text FROM \"public\".news;";

            ResultSet rs = statment.executeQuery(SQL);
            while (rs.next()) {
                System.out.println(rs.getString("header") +" "+ rs.getString("news_text"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteNews() {
        String SQL = "delete from \"public\".news where id = ?;\n";
        try
                (Connection connect = connection();
                 PreparedStatement stmt = connect.prepareStatement(SQL)) {
            System.out.println("Введите id для удаления");
            int deleteNews = scanner.nextInt();
            stmt.setInt(1,deleteNews);
            stmt.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void changeNews() {
        String SQL = "update \"public\".news set header= ?, news_text = ? where id = ?";

        try
                (Connection connect = connection();
                 PreparedStatement stmt = connect.prepareStatement(SQL)) {
            System.out.println("Введите id для изменения новости");
            int newsId = scanner.nextInt();
            stmt.setInt(3, newsId);
            System.out.println("Введите новый заголовок: ");
            String head = scanner.next();
            stmt.setString(1,head);
            System.out.println("Введите новость: ");
            String news = scanner.next();
            stmt.setString(2,news);
            stmt.executeUpdate();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}

