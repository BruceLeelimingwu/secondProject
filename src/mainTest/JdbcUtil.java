
package mainTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


/*
public class JdbcUtil {

    public static void main(String args[]) throws Exception {

        String connectionUrl;
        Connection con;
        Statement stmt;
        ResultSet rs;
        Statement stmt_sleep;
        Statement stmt_morning;
        Statement stmt_afternoon;
        Statement stmt_sum;
        Statement stmt_nag;
        Statement stmt_flow;

        ResultSet rs_sleep;
        ResultSet rs_morning;
        ResultSet rs_afternoon;
        ResultSet rs_sum;
        ResultSet rs_flow;
        ResultSet rs_nag;

        String visittime="";
        String url="";
        String sourceaddr="";
        String sleep="";
        String morning="";
        String afternoon="";
        String sumofall="";
        String flowdata="";
        String sumofallaboutfilter="";
        String sumofunhealth="";
        String responseContent;
        connectionUrl = "jdbc:sqlserver://123.207.169.35:1433;" +
                "databaseName=IDCMon;user=sa;password=305windowsServer;";
        String IP="10.0.3.147";
        //connectionUrl = "jdbc:sqlserver://10.141.100.219:1433;"+"databaseName=IDCMon;user=sa;password=305windowsServer;";


        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            stmt = con.createStatement();
            String SQL = "SELECT TOP 20 [SaveTime],[Source_Addr],[URL] FROM [IDCMon].[dbo].[http_log] " +
                    "WHERE [Source_Addr]='" + IP + "' ORDER BY [SaveTime] DESC ;";
            rs = stmt.executeQuery(SQL);
            int listnumber = 0;

            while (rs.next()) {
                visittime = "最后访问时间：" + rs.getString("SaveTime") + "\n";

                sourceaddr = "源IP：" + rs.getString("Source_Addr") + "\n";

                url = "访问URL:" + rs.getString("URL") + "\n";
                listnumber++;

            }
            if (listnumber == 0) {
                visittime = "最后访问时间：" +"暂无数据" + "\n";

                sourceaddr ="源IP：" +"暂无数据" + "\n";

                url = "访问URL:"+"暂无数据" + "\n";
            }
//.................................................................

            stmt_sum = con.createStatement();
            stmt_sleep = con.createStatement();
            stmt_morning = con.createStatement();
            stmt_afternoon = con.createStatement();
            rs_sleep = stmt_sleep.executeQuery("SELECT sleeptime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='" + IP + "' AND convert(varchar(24),SaveTime,114)<'07:00:00:00' OR convert(varchar(24),SaveTime,114)>'22:00:00:00'");

            rs_morning = stmt_morning.executeQuery("SELECT morningtime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='" + IP + "' AND convert(varchar(24),SaveTime,114)>'08:30:00:00' AND convert(varchar(24),SaveTime,114)<'11:30:00:00'");

            rs_afternoon = stmt_afternoon.executeQuery("SELECT afternoontime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='" + IP + "' AND convert(varchar(24),SaveTime,114)>'14:30:00:00' AND convert(varchar(24),SaveTime,114)<'18:00:00:00'");

            rs_sum = stmt_sum.executeQuery("SELECT sumofall=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='" + IP + "'");

            listnumber = 0;

            while (rs_sum.next() && rs_sleep.next() && rs_afternoon.next() && rs_morning.next()) {
                sleep = "夜间（22:00~7：00）访问：" + rs_sleep.getString("sleeptime") + "\n";

                morning = "上午（8:30~11:30）访问：" + rs_morning.getString("morningtime") + "\n";

                afternoon = "下午（14:30~18:00）访问：" + rs_afternoon.getString("afternoontime") + "\n";

                sumofall = "访问总量：" + rs_sum.getString("sumofall") + "\n";

                listnumber++;

            }
            if (listnumber == 0) {
                sleep = "夜间（22:00~7：00）访问：" +"暂无数据" + "\n";

                morning = "上午（8:30~11:30）访问：" +"暂无数据" + "\n";

                afternoon = "下午（14:30~18:00）访问："+"暂无数据" + "\n";

                sumofall = "访问总量：" +"暂无数据" + "\n";
            }

//.................................................................................
            stmt_flow = con.createStatement();
            String SQL_flow = "SELECT TOP 1 [TotalFlow] FROM [IDCMon].[dbo].[HostFlowInfo_log] " +
                    "WHERE IP='" + IP + "'ORDER BY [SaveTime] DESC;";
            rs_flow = stmt_flow.executeQuery(SQL_flow);
            listnumber = 0;

            while (rs_flow.next()) {
                flowdata = "流量：" + rs_flow.getString("TotalFlow") + "\n";

                listnumber++;

            }
            if (listnumber == 0) {
                flowdata = "流量：" +"暂无数据" + "\n";
            }
//.分类信息
            stmt_nag = con.createStatement();
            String SQL_nag = "SELECT sumofallaboutfilter=COUNT(CResult),sumofunhealth=sum(CResult) FROM [IDCMon].[dbo].[http_classify] " +
                    "WHERE Source_Addr='" + IP + "';";
            rs_nag = stmt_nag.executeQuery(SQL_nag);
            listnumber = 0;

            while (rs_nag.next()) {
                sumofallaboutfilter = "过滤数量：" + rs_nag.getString("sumofallaboutfilter") + "\n";

                sumofunhealth = "非健康网页数量：" + rs_nag.getString(("sumofunhealth")) + "\n";

                listnumber++;

            }

            if (listnumber == 0) {
                sumofallaboutfilter = "过滤数量：" + "暂无数据" + "\n";
                sumofunhealth = "非健康网页数量：" +"暂无数据" + "\n";
            }

            responseContent = visittime+sourceaddr+url + sourceaddr + sleep + morning + afternoon + sumofall + flowdata + sumofallaboutfilter + sumofunhealth;
            System.out.println(responseContent);
            //return responseContent;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Database Connection ERROR ...");
        }

    }
}*/
        //return "Database Connection ERROR ...";}

/*public class JdbcUtil {

    public static void main(String args[]) throws Exception {
        Connection conn = null;
        // 加载数据库驱动
        //Class.forName("com.mysql.jdbc.Driver");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        // 获取数据库连接
        try {
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://123.207.169.35:1433;"+
                            "databaseName=IDCMon;user=sa;password=305windowsServer;");
        } catch (Exception e) {

            e.printStackTrace();

            System.out.println("数据库链接失败!");
        }
        // Statement里面有很多方法
        Statement stmt = conn.createStatement();
        // sql
        String IP="10.0.3.147";
        String SQL = "SELECT TOP 20 [SaveTime],[Source_Addr],[URL] FROM [IDCMon].[dbo].[http_log] " +
                "WHERE [Source_Addr]='"+IP+"' ORDER BY [SaveTime] DESC ;";
        ResultSet rs = stmt.executeQuery(SQL);
        // 执行sql

        while (rs.next()) {

            System.out.println(rs.getString("SaveTime"));
        }
    }

}*/
