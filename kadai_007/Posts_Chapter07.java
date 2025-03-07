package kadai_007;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts_Chapter07 {
    public static void main(String[] args) {
    	
    	  Connection con = null;
          PreparedStatement statement = null;
          ResultSet resultSet = null;
    	
          try {
              // データベースに接続
              con = DriverManager.getConnection(
                  "jdbc:mysql://localhost/challenge_java",
                  "root",
                  "Imanishi0810"
              );
              System.out.println("データベース接続成功：" + con);
    	
    	

              // SQLクエリを準備
      
              String sql = """
              	INSERT INTO posts  (user_id, posted_at,post_content,likes) VALUES
                  (1003, "2023-02-08", "昨日の夜は徹夜でした・・", 13),
                  (1002, "2023-02-08", "お疲れ様です！", 12),
                  (1003, "2023-02-09", "今日も頑張ります", 18),
                  (1001, "2023-02-09", "無理は禁物ですよ！", 17),
                  (1002, "2023-02-10", "明日から連休ですね！", 20);
              """;


              // SQLクエリを実行（DBMSに送信）
              statement = con.prepareStatement(sql);
              System.out.println("レコード更新追加を実行します"  );
              int rowCnt = statement.executeUpdate();
              System.out.println( rowCnt + "件のレコードが追加されました");
              
              //検索
              String selectSql = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = ?";
              statement = con.prepareStatement(selectSql);
              statement.setInt(1, 1002);

              System.out.println("ユーザーIDが1002のレコードを検索しました");

              resultSet = statement.executeQuery();
              int count = 1;
              while (resultSet.next()) {
                  String postedAt = resultSet.getString("posted_at");
                  String postContent = resultSet.getString("post_content");
                  int likes = resultSet.getInt("likes");

                  System.out.println(count + "件目：投稿日時=" + postedAt + 
                                     "／投稿内容=" + postContent + 
                                     "／いいね数=" + likes);
                  count++;
              }

          } catch (SQLException e) {
              System.out.println("エラー発生：" + e.getMessage());
          } finally {
              // リソースを解放
              if (resultSet != null) {
                  try { resultSet.close(); } catch (SQLException ignore) {}
              }
              if (statement != null) {
                  try { statement.close(); } catch (SQLException ignore) {}
              }
              if (con != null) {
                  try { con.close(); } catch (SQLException ignore) {}
              }
          }
    }
}              
         