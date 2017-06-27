package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Conexao {

    public boolean sentinela=false;
    private int id;
    private Connection con;
    private Statement st;
    private ResultSet rs;
    final String url = "jbc:mysql://localhost:3306/tb_customer_account";
    
    public void conectar(){
        try{
            con = DriverManager.getConnection(url, "root", "");
            JOptionPane.showMessageDialog(null,"Conectado!");
            sentinela=true;
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"erro na conexao!\n"+ex);
        }
    }
    public void desconectar(){
        try{
            con.close();
            JOptionPane.showMessageDialog(null,"Desconectado!");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null,"Erro ao desconectar!"+ex);
        }
    }
    public void inserir(int id,int cpf_cnpj,String nome,boolean ativo,float total){
        try{
        String s="INSERT INTO tb_customer_account (id_customer,cpf_cnpj,nm_customer,is_active,vl_total) VALUES (' "+id+" ',' "+cpf_cnpj+" ',' "+nome+" ',' "+ativo+" ',' "+total+" ')";
        st=con.createStatement();
        st.executeUpdate(s);
        st.close();
        }catch(SQLException i){
            JOptionPane.showMessageDialog(null,"Erro ao inserir\n"+i);
        }
    }
    public void media(){
        String s="";
        float media = 0;
        try{
            st=con.createStatement();
            rs=st.executeQuery("SELECT * FROM tb_customer_account ORDER BY vl_total");
            if(rs.next()){
                s += "ID: "+rs.getInt("id_customer")+" Cpf_Cnpj: "+rs.getInt("cpf_cnpj")+" Nome: " + rs.getString("nm_customer") + " Ativo: " + rs.getBoolean("is_active") + " Saldo: " + rs.getFloat("vl_total")+"\n";
                if((rs.getFloat("vl_total") > 560) && ((rs.getInt("id_customer") > 1499) && (rs.getInt("id_customer") < 2701))){
                    media += rs.getFloat("vl_total");
                }
            }
            JOptionPane.showMessageDialog(null,"Cliente: "+s+"\nMedia do Cliente: "+media);
        }catch(SQLException m){
                    JOptionPane.showMessageDialog(null,"Erro ao calcular\n"+m);
            }
        }
    }
