package conexao;
 
import static java.lang.System.exit;
import java.util.Scanner;
import javax.swing.JOptionPane;
 
/**
 *
 * @author FernandoR.
 */
public class TestaConexao {
 
    public static void main(String[] args) {
        Conexao conexao = new Conexao();
        Scanner teclado = new Scanner(System.in);
        int id,cpf_cnpj;
        String nome = null;
        boolean ativo=false;
        Float total;
        
        JOptionPane.showMessageDialog(null,"Iniciando conexão!");
        conexao.conectar();
        
        if(conexao.sentinela == true){
        id=Integer.parseInt(JOptionPane.showInputDialog("Digite o id:","Digite aqui"));
        cpf_cnpj=Integer.parseInt(JOptionPane.showInputDialog("Digite o cpf pu cnpj:","Digite aqui"));
        JOptionPane.showInputDialog("Digite o nome:","Digite aqui");
        ativo = true;
        total=Float.parseFloat(JOptionPane.showInputDialog("Digite o saldo:","Digite aqui"));
        
        conexao.inserir(id,cpf_cnpj,nome,ativo,total);
         
        conexao.media();
        }
        
        conexao.desconectar();
        JOptionPane.showMessageDialog(null,"\nDesconectado!");
 
    }
 
}
