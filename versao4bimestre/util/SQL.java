/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.makery.address.util;

import ch.makery.address.table.Pessoa;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author USER
 */
public class SQL {
    private static final EntityManagerFactory CONNECTION;

    static {
        CONNECTION = Persistence.createEntityManagerFactory("AddressApp");
    }
    
    public static boolean adicionarPessoa(Pessoa pessoa) {
        EntityManager manager = CONNECTION.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Save the student object
            manager.persist(pessoa);
            System.out.println(transaction.isActive());

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex);
            return false;
        } finally {
            // Close the EntityManager
            manager.close();
            return true;
        }
    }
    
    public static List<Pessoa> getPessoas(){
        List<Pessoa> pessoas = null;

        // Create an EntityManager
        EntityManager manager = CONNECTION.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get a List of Students
            pessoas = manager.createQuery("SELECT p FROM Pessoa p",
                    Pessoa.class).getResultList();

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return pessoas;
    }
    
    public static boolean deletePessoa(String codCPF){
        // Create an EntityManager
        EntityManager manager = CONNECTION.createEntityManager();
        EntityTransaction transaction = null;

        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            // Get the Student object
            Pessoa pessoa = manager.find(Pessoa.class, codCPF);

            // Delete the student
            manager.remove(pessoa);

            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
        } finally {
            // Close the EntityManager
            manager.close();
            return true;
        }
    }
    
    public static boolean updatePessoa(Pessoa pessoa, String codCPF){
        EntityManager manager = CONNECTION.createEntityManager();
        EntityTransaction transaction = null;
        Boolean res = null;
        try {
            // Get a transaction
            transaction = manager.getTransaction();
            // Begin the transaction
            transaction.begin();

            Pessoa pessoaAux = manager.find(Pessoa.class, codCPF);
            
            if(pessoaAux != null){
                pessoaAux.setCidade(pessoa.getCidade());
                pessoaAux.setRua(pessoa.getRua());
                pessoaAux.setCodPostal(pessoa.getCodPostal());
                pessoaAux.setDataNasc(pessoa.getDataNasc());
                pessoaAux.setNomPrim(pessoa.getNomPrim());
                pessoaAux.setNomUlt(pessoa.getNomUlt());
                if(!pessoa.getCodCPF().equals(codCPF)){
                    Query query = manager.createQuery("UPDATE Pessoa p SET cod_CPF = '" + pessoa.getCodCPF() + "' WHERE cod_cpf = '" + codCPF + "' " );
                    query.executeUpdate();
                }
            }
            
            
            res = true;
            // Commit the transaction
            transaction.commit();
        } catch (Exception ex) {
            // If there are any exceptions, roll back the changes
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println(ex);
            res = false;
        } finally {
            // Close the EntityManager
            manager.close();
        }
        return res;
    }
    
    

}
