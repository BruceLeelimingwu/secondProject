package getdata;

/**
 * Created by limingwu on 2016/10/26.
 */

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class GetAllData extends HttpServlet {


    private String connectionUrl;
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    private Statement stmt_sleep;
    private Statement stmt_morning;
    private Statement stmt_afternoon;
    private Statement stmt_sum;
    private Statement stmt_nag;
    private Statement stmt_flow;

    private ResultSet rs_sleep;
    private ResultSet rs_morning;
    private ResultSet rs_afternoon;
    private ResultSet rs_sum;
    private ResultSet rs_flow;
    private ResultSet rs_nag;

    String visittime;
    String url;
    String sourceaddr;
    String sleep;
    String morning;
    String afternoon;
    String sumofall;
    String flowdata;
    String sumofallaboutfilter;
    String sumofunhealth;
    String responseContent;

    public String  getDatafromDB(String IP)
    {
        //........................................................
        connectionUrl = "jdbc:sqlserver://123.207.169.35:1433;"+"databaseName=IDCMon;user=sa;password=305windowsServer;";
        //connectionUrl = "jdbc:sqlserver://10.141.100.219:1433;"+"databaseName=IDCMon;user=sa;password=305windowsServer;";


        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(connectionUrl);

            stmt = con.createStatement();
            String SQL = "SELECT TOP 20 [SaveTime],[Source_Addr],[URL] FROM [IDCMon].[dbo].[http_log] WHERE [Source_Addr]='"+IP+"' ORDER BY [SaveTime] DESC ;";
            rs = stmt.executeQuery(SQL);
            int listnumber=0;

            while (rs.next())
            {
                visittime="最后访问时间："+rs.getString("SaveTime")+"\n";

                sourceaddr="源IP："+rs.getString("Source_Addr")+"\n";

                url="访问URL:"+rs.getString("URL")+"\n";
                listnumber++;

            }
            if(listnumber==0)
            {
                visittime="最后访问时间："+"没有数据"+"\n";

                sourceaddr="源IP："+"没有数据"+"\n";

                url="访问URL："+"没有数据"+"\n";
            }
//.................................................................

            stmt_sum = con.createStatement();
            stmt_sleep = con.createStatement();
            stmt_morning = con.createStatement();
            stmt_afternoon = con.createStatement();
            rs_sleep = stmt_sleep.executeQuery("SELECT sleeptime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='"+IP+"' AND convert(varchar(24),SaveTime,114)<'07:00:00:00' OR convert(varchar(24),SaveTime,114)>'22:00:00:00'");

            rs_morning = stmt_morning.executeQuery("SELECT morningtime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='"+IP+"' AND convert(varchar(24),SaveTime,114)>'08:30:00:00' AND convert(varchar(24),SaveTime,114)<'11:30:00:00'");

            rs_afternoon = stmt_afternoon.executeQuery("SELECT afternoontime=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='"+IP+"' AND convert(varchar(24),SaveTime,114)>'14:30:00:00' AND convert(varchar(24),SaveTime,114)<'18:00:00:00'");

            rs_sum = stmt_sum.executeQuery("SELECT sumofall=COUNT(Source_Addr) FROM http_log WHERE Source_Addr='"+IP+"'");

            listnumber=0;

            while (rs_sum.next()&&rs_sleep.next()&&rs_afternoon.next()&&rs_morning.next())
            {
                sleep="夜间（22:00~7：00）访问："+rs_sleep.getString("sleeptime")+"\n";

                morning="上午（8:30~11:30）访问："+rs_morning.getString("morningtime")+"\n";

                afternoon="下午（14:30~18:00）访问："+rs_afternoon.getString("afternoontime")+"\n";

                sumofall="访问总量："+rs_sum.getString("sumofall")+"\n";

                listnumber++;

            }
            if(listnumber==0)
            {
                sleep="夜间（22:00~7：00）访问："+"没有数据"+"\n";

                morning="上午（8:30~11:30）访问："+"没有数据"+"\n";

                afternoon="下午（14:30~18:00）访问："+"没有数据"+"\n";

                sumofall="访问总量："+"没有数据"+"\n";
            }

//.................................................................................
            stmt_flow = con.createStatement();
            String SQL_flow = "SELECT TOP 1 [TotalFlow] FROM [IDCMon].[dbo].[HostFlowInfo_log] WHERE IP='"+IP+"'ORDER BY [SaveTime] DESC;";
            rs_flow = stmt_flow.executeQuery(SQL_flow);
            listnumber=0;

            while (rs_flow.next())
            {
                flowdata="流量："+rs_flow.getString("TotalFlow")+"\n";

                listnumber++;

            }
            if(listnumber==0)
            {
                flowdata="流量："+"没有数据"+"\n";
            }
//...........................................................................
            /*stmt_nag = con.createStatement();
            String SQL_nag = "SELECT sumofallaboutfilter=COUNT(CResult),sumofunhealth=sum(CResult) FROM http_classify WHERE Source_Addr='"+IP+"';";
            rs_nag = stmt_nag.executeQuery(SQL_nag);
            listnumber=0;

            while (rs_nag.next())
            {
                sumofallaboutfilter="过滤数量："+rs_nag.getString("sumofallaboutfilter")+"\n";

                sumofunhealth="非健康网页数量："+rs_nag.getString(("sumofunhealth"))+"\n";

                listnumber++;

            }

            if(listnumber==0)
            {
                sumofallaboutfilter="过滤数量："+"没有数据"+"\n";
                sumofunhealth="非健康网页数量："+"没有数据"+"\n";
            }*/

            responseContent=visittime+sourceaddr+url+sourceaddr+sleep+morning+afternoon+sumofall+flowdata+sumofallaboutfilter+sumofunhealth;
            return responseContent;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return "Database Connection ERROR ...";
    }


    @Override
    public void destroy() {
        try{
            if(rs_sum!=null)
                rs_sum.close();
        }
        catch(Exception e)
        {

        }

        try{
            if(rs_sleep!=null)
                rs_sleep.close();
        }
        catch(Exception e)
        {

        }

        try{
            if(rs_morning!=null)
                rs_morning.close();
        }
        catch(Exception e)
        {

        }

        try{
            if(rs_afternoon!=null)
                rs_afternoon.close();
        }
        catch(Exception e)
        {

        }


        try{
            if(stmt_sleep!=null)
                stmt_sleep.close();
        }
        catch(Exception e)
        {

        }

        try{if(stmt_sum!=null)
            stmt_sum.close();
        }
        catch(Exception e)
        {

        }
        try{   if(stmt_afternoon!=null)
            stmt_afternoon.close();
        }
        catch(Exception e)
        {

        }
        try{ if(stmt_morning!=null)
            stmt_morning.close();
        }
        catch(Exception e)
        {

        }

        try{ if(stmt_nag!=null)
            stmt_nag.close();
        }
        catch(Exception e)
        {

        }

        try{ if(stmt_flow!=null)
            stmt_flow.close();
        }
        catch(Exception e)
        {

        }

        try{ if(rs_nag!=null)
            rs_nag.close();
        }
        catch(Exception e)
        {

        }

        try{ if(rs_flow!=null)
            rs_flow.close();
        }
        catch(Exception e)
        {

        }
        try{
            if(rs!=null)
                rs.close();
        }
        catch(Exception e)
        {

        }

        try{
            if(stmt!=null)
                stmt.close();
        }
        catch(Exception e)
        {

        }

        try{
            if(con!=null)
                con.close();
        }
        catch(Exception e)
        {

        }

    }



}
