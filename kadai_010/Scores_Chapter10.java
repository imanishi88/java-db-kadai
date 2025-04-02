package java_db_study.kadai_010;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Scores_Chapter10 {
    public static void main(String[] args) {
        // データベース接続情報
       

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // (1) データベースに接続する
            conn = DriverManager.getConnection(
            		"jdbc:mysql://localhost/challenge_java", 
            		"root", 
            		"Imanishi0810"
            		);
            // (2) SQLクエリを準備する
            stmt = conn.createStatement();
            System.out.println("データベースに接続成功：" + stmt.toString());


            // (3) 点数データを更新する
            String updateSql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5";
            System.out.println("レコード更新を実行します");
            int rowsAffected = stmt.executeUpdate(updateSql);
            System.out.println(rowsAffected + "件のレコードが更新されました");

            // (4) 点数順に並べる
            String Sql = "SELECT id,name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC";
            rs = stmt.executeQuery(Sql);

            // (5) 並べ替え結果を表示する
            System.out.println("数学・英語の点数が高い順に並べ替えました");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score_math = rs.getInt("score_math");
                int score_english = rs.getInt("score_english");
                System.out.println(rs.getRow() + "件目：生徒ID=" +id + "／氏名=" + name + "／数学=" + score_math +"英語=" + score_english);
            }

        } catch (SQLException e) {
        	System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // 解放する
        	if( stmt != null ) {
                try { stmt.close(); } catch(SQLException ignore) {}
            }
            if( conn != null ) {
                try { conn.close(); } catch(SQLException ignore) {}
            }
            }
        }
    }

