
public class Main {
    public static void main(String[] args) {
Connect connect = new Connect();
News news = new News();
news.setHeader("zagolovok");
news.setNews_text("sama novost`");
connect.addNews(news);
//        connect.viewNews();
//    connect.deleteNews();
//        connect.changeNews();
    }
}
