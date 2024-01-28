package org.gestao.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import org.gestao.dto.ClienteDTO;
import org.gestao.entity.Cliente;
import org.gestao.service.ClienteService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Path("/api/cliente")
public class ClienteController {

    @Inject
    ClienteService clienteService;

    @POST
    @Transactional
    public void salvarCliente(Cliente cliente) {
        if (Objects.isNull(cliente.getCelular()) || Objects.isNull(cliente.getNome_completo()) || Objects.isNull(cliente.getDivida())) {
            throw new IllegalArgumentException("Não é possível salvar valores null para um cliente");
        }
        if (cliente.getNome_completo().isBlank() || cliente.getCelular().isBlank()) {
            throw new IllegalArgumentException("Não é possível salvar valores vazios para um cliente");
        }
        clienteService.adicionarCliente(cliente);
    }

    @GET
    @Path("/listar-tudo")
    public List<Cliente> buscarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = clienteService.listarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @GET
    @Path("/listar/{id}")
    public Cliente buscarCliente(@PathParam("id") long id) {
        if (Objects.isNull(clienteService.listarClienteById(id))) {
            throw new NotFoundException("O id: " + id + " não existe no sistema");
        }
        return clienteService.listarClienteById(id);
    }

    @PUT
    @Path("/editar/{id}")
    @Transactional
    public void editarCliente(@PathParam("id") long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.listarClienteById(id);
        if (!Objects.isNull(clienteDTO.getNome_completo())) {
            cliente.setNome_completo(clienteDTO.getNome_completo());
        }
        if (!Objects.isNull(clienteDTO.getCelular())) {
            cliente.setCelular(clienteDTO.getCelular());
        }
        if (!Objects.isNull(clienteDTO.getDivida())) {
            cliente.setDivida(clienteDTO.getDivida());
        }
        clienteService.editarCliente(id);
    }

    @DELETE
    @Path("/excluir/{id}")
    @Transactional
    public void deletarCliente(@PathParam("id") long id) {
        if (Objects.isNull(clienteService.listarClienteById(id))) {
            throw new NotFoundException("O id: " + id + " não existe no sistema");
        }
        clienteService.deletarCliente(id);
    }
}
