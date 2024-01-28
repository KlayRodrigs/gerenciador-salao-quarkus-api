package org.gestao.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.gestao.entity.Cliente;
import org.gestao.repository.ClienteRepository;

import java.util.List;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository;

    public void adicionarCliente(Cliente cliente){
        clienteRepository.persist(cliente);
    }
    public List<Cliente> listarClientes(){
        return clienteRepository.findAll().list();
    }
    public Cliente listarClienteById(long id){
        return clienteRepository.findById(id);
    }
    public void editarCliente(long id){
        Cliente cliente = clienteRepository.findById(id);
        clienteRepository.persist(cliente);
    }
    public void deletarCliente(long id){
        clienteRepository.deleteById(id);
    }
}
