/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufsc.ine5605.controleacesso.Persistencia;

import br.ufsc.ine5605.controleacesso.Model.Sala;
import com.sun.imageio.spi.FileImageOutputStreamSpi;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.tools.FileObject;

/**
 *
 * @author Linnety3
 */
public class SalaDAO {

    private static SalaDAO instancia;
    private HashMap<String, Sala> cacheSala;
    private final String fileName;

    public static SalaDAO getInstancia() {
        if (instancia == null) {
            instancia = new SalaDAO();
        }
        return instancia;
    }

    public SalaDAO() {
        this.fileName = "salas.dat";
        cacheSala = new HashMap<>();
        load();
    }

    public Sala getSala(String codigo) {
        return cacheSala.get(codigo);
    }

    public void put(Sala sala) {
        cacheSala.put(sala.getCodigoSala(), sala);
        persist();
    }

    public void load() {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            this.cacheSala = (HashMap<String, Sala>) ois.readObject();

            ois.close();
            fis.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Arquivo não encontrado: " + ex.getMessage());
            persist();
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }

    public void persist() {

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(cacheSala);

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

    public ArrayList<Sala> getList() {

        return new ArrayList(this.cacheSala.values());
    }

    public void remove(Sala salaParaDeletar) {
        cacheSala.remove(salaParaDeletar.getCodigoSala());
        persist();
    }
}
