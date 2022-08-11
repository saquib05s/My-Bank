package bank_application;

import java.sql.*;

public class Bank_Application {
    public static String transfer(String Num1,String Num2,Double Amt){
        String s="";
        Connection conn;
        try{
            String URLDB="jdbc:mysql://localhost:3306/bank";
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(URLDB,"root","");
            
            PreparedStatement stmt1=conn.prepareStatement("Select balance from acc1 where number=?");
            PreparedStatement stmt2=conn.prepareStatement("Select balance from acc1 where number=?");
            
            stmt1.setString(1,Num1);
            stmt2.setString(1,Num2);
            
            int count1=0;
            Double c_amt1=0.0;
            ResultSet rs=stmt1.executeQuery();
            while(rs.next()){
                count1++;
                c_amt1=Double.parseDouble(rs.getString(1));}
            
            int count2=0;
            Double c_amt2=0.0;
            rs=stmt2.executeQuery();
            while(rs.next()){
                count2++;
                c_amt2=Double.parseDouble(rs.getString(1));}
            
            if(Amt>c_amt1 && count1!=0 && count2!=0)
                s="Amount inefficient";
            else if(count1!=0 && count2!=0)
            {
                Double u_amt1=c_amt1-Amt;
                Double u_amt2=c_amt2+Amt;
                Statement stmtr=conn.createStatement();
                stmtr.addBatch("Update acc1 set balance="+ u_amt1 + "where number="+ Num1);
                stmtr.addBatch("Update acc1 set balance="+ u_amt2 + "where number="+ Num2);
                stmtr.executeBatch();
                s="Payment Successfull";
            }
            else
                s="Account Does Not Exist";
        conn.close();
        } 
        catch(Exception e)
        {
            s="Account Does Not Exist";
            //System.out.println(e);
        }
    return s;
    }
    public static String getLoginDetails(String Num1) {
        String pswd="";
        int count=1;
        Connection conn;
        try{
            String URLDB="jdbc:mysql://localhost:3306/bank";
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(URLDB,"root","");
            PreparedStatement stmt1=conn.prepareStatement("Select * from acc1 where number=?");
            stmt1.setString(1,Num1);
            
            ResultSet rs=stmt1.executeQuery();
            while(rs.next()){
                pswd=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                count=0;
            }
            if(count==1)
                pswd="Account Does Not Exist";
        conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return pswd;
    }
    public static String getStatement(String s) {
        java.util.ArrayList<String> sars=new java.util.ArrayList<String>();
        String pswd="";
        int count=1;
        Connection conn;
        try{
            String URLDB="jdbc:mysql://localhost:3306/bank";
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(URLDB,"root","");
            PreparedStatement stmt1=conn.prepareStatement("Select * from "+s);
            
            ResultSet rs=stmt1.executeQuery();
            while(rs.next()){
                pswd=rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5);
                count=0;
                sars.add(pswd);
            }
            if(count==1)
                pswd="Account Does Not Exist";//System.out.println(pswd);
        conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        StringBuffer temp=new StringBuffer(sars.toString());
        temp.deleteCharAt(0);
        temp.deleteCharAt(temp.length()-1);
        return temp.toString();
    }
    public static void records(String rec1, String rec2){
        System.out.println("Rec1="+rec1);
        String indiv1[]=rec1.split(" ");
        String indiv2[]=rec2.split(" ");
        Connection conn;
        try{
            String URLDB="jdbc:mysql://localhost:3306/bank";
            Class.forName("com.mysql.jdbc.Driver");
            conn=DriverManager.getConnection(URLDB,"root","");
            Statement s=conn.createStatement();
            
            String indivs1=indiv1[2]+" "+indiv1[3];
            String indivs2=indiv2[2]+" "+indiv2[3];
            
            s.addBatch("Insert into "+indiv1[0]+" values ('"+indiv1[1]+"','"+indivs1+"','"+indiv1[4]+"','"+indiv1[5]+"','"+indiv1[6]+"')");
            s.addBatch("Insert into "+indiv2[0]+" values ('"+indiv2[1]+"','"+indivs2+"','"+indiv2[4]+"','"+indiv2[5]+"','"+indiv2[6]+"')");
            int results[]=s.executeBatch();
        conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public  static void main(String []args)
    {  
        
    }    
}
