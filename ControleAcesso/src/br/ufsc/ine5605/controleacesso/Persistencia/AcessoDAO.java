/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Persistencia;

import br.ufsc.ine5605.controleacesso.Controller.CtrlPrincipal;
import br.ufsc.ine5605.controleacesso.Model.Acesso;
import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import br.ufsc.ine5605.controleacesso.Model.Sala;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author caiocaio
 */
public class AcessoDAO {
    private static AcessoDAO instancia;
    private HashMap <Integer, Acesso> cacheAcessos;
    private final String fileName;

    private AcessoDAO(){
        cacheAcessos = new HashMap<>();
        fileName  = "acessos.dat";
        this.load();
    }
    
    public static AcessoDAO getInstancia(){
        if(instancia == null){
            instancia = new AcessoDAO();
        }
        return instancia;
    }
    
    public Acesso getAcesso(int matricula){
        return cacheAcessos.get(matricula);
    }
      
    
    
    public void put(Acesso acesso){
        cacheAcessos.put(acesso.getId(), acesso);
    }
    
    public void remove (Acesso acesso){
        cacheAcessos.remove(acesso.getId());
    }
    
        
    public ArrayList <Acesso> getList(){
        return new ArrayList(this.cacheAcessos.values());
    }
    
    private void load() {
        
       try{
        FileInputStream fis = new FileInputStream (fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.cacheAcessos = (HashMap<Integer, Acesso>) ois.readObject();
       
        ois.close();
        fis.close();
        }  catch(FileNotFoundException ex){
            System.out.println("Arquivo nao encontrado " + ex.getMessage());
            persist();
        } catch (IOException ex){
            System.out.println("Erro " + ex.getMessage());
        } catch (ClassNotFoundException ex){
            System.out.println("Erro " + ex.getMessage());
        }
    }

    public void persist() {
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
           
            oos.writeObject(cacheAcessos);
           
            oos.flush();
            fos.flush();
           
            oos.close();
            fos.close();
           
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());

        }
    }
}



