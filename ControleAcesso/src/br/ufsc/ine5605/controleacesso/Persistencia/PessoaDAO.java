/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Persistencia;


import br.ufsc.ine5605.controleacesso.Model.Pessoa;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 07787552905
 */
public class PessoaDAO  {
    private static PessoaDAO instancia;
    private HashMap <Integer, Pessoa> cachePessoas;
    private final String fileName = "pessoas.dat";
   
    private  PessoaDAO() {
        cachePessoas = new HashMap<>();
        this.load();
    }
   
    public static PessoaDAO getInstancia(){
        if(instancia == null){
            instancia = new PessoaDAO();
        }
        return instancia;
    }
   
    public Pessoa getPessoa(Integer matricula){
        return cachePessoas.get(matricula);
    }
   
    public void put(Pessoa pessoa){
        cachePessoas.put(pessoa.getMatricula(), pessoa);
        persist();
    }
   
    public  void remove(Pessoa pessoa){
        cachePessoas.remove(pessoa.getMatricula());
        persist();
    }
   
    public ArrayList<Pessoa> getList(){
        return new ArrayList(this.cachePessoas.values());
       
    }
   
    public void load(){
       
      try{
         FileInputStream fis = new FileInputStream (fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);
        this.cachePessoas = (HashMap<Integer, Pessoa>) ois.readObject();
       
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
   
    public void persist(){
        try{
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
           
            oos.writeObject(cachePessoas);
           
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

	
	
	

