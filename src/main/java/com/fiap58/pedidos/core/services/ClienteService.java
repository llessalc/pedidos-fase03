package com.fiap58.pedidos.core.services;

import com.fiap58.pedidos.core.domain.entity.Pedido;
import com.fiap58.pedidos.core.usecase.IPedidoService;
import com.fiap58.pedidos.gateway.ClienteRepository;
import com.fiap58.pedidos.presenters.dto.entrada.BuscaClienteDto;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.core.domain.entity.Cliente;
import com.fiap58.pedidos.core.usecase.IClienteService;
import com.fiap58.pedidos.core.usecase.IEnderecoService;
import com.fiap58.pedidos.core.usecase.ITelefoneService;

import com.fiap58.pedidos.presenters.dto.saida.DadosClienteExclusaoDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService implements IClienteService {

    private final ClienteRepository repository;

    private final IEnderecoService enderecoService;

    private final ITelefoneService telefoneService;


    public ClienteService(ClienteRepository repository, IEnderecoService enderecoService,
            ITelefoneService telefoneService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
        this.telefoneService = telefoneService;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        cliente.setCriadoEm(Instant.now());
        cliente.setAtualizadoEm(Instant.now());
        return repository.save(cliente);
    }

    @Override
    public DadosClienteDto cadastrarCliente(DadosClienteCadastro dto) {
        if (buscarClientePorCpf(dto.cpf()) == null) {
            Cliente cliente = new Cliente(dto);
            Cliente clienteCadastrado = repository.save(cliente);
            for (EnderecoCadastro enderecoCadastro : dto.enderecos()) {
                enderecoService.cadstrarEndereco(enderecoCadastro, clienteCadastrado);
            }
            for (TelefoneCadastro telefoneCadastro : dto.telefones()) {
                telefoneService.cadastrarTelefone(telefoneCadastro, clienteCadastrado);
            }
            return mapperClienteDto(cliente);
        } else {
            return null;
        }
    }

    @Override
    public DadosClienteDto retornaClienteCpf(String cpf) {
        return mapperClienteDto(buscarClientePorCpf(cpf));
    }

    @Override
    public DadosClienteDto retornaClienteId(Long id) {
        return mapperClienteDto(buscarClientePorId(id));
    }

    @Override
    public Cliente buscarClientePorCpf(String cpf) {
        return verificaClientesAtivos(repository.findByCpf(cpf));
    }

    @Override
    public Cliente buscarClientePorId(Long id) {
        return verificaClientesAtivos(repository.findById(id).orElse(null));
    }

    @Override
    public List<DadosClienteDto> listarClientes() {
        return repository.findAll().stream().filter(cliente -> cliente.getDeletadoEm() == null)
                .map(DadosClienteDto::new).toList();
    }

    @Override
    public DadosClienteExclusaoDto excluirCliente(BuscaClienteDto dto) throws Exception {
        Cliente cliente = new Cliente();
        if(dto.cpf() != null){
            cliente = buscarClientePorCpf(dto.cpf());
        } else if(dto.nome() != null){
            cliente = buscarClientePorNome(dto.nome());
        } else {
            throw new Exception("Alguma informação deve ser fornecida");
        }

        cliente = verificaClientesAtivos(cliente);

        if(cliente != null && dto.excluirCliente()){
            cliente.setDeletadoEm(Instant.now());
            repository.save(cliente);
            telefoneService.excluirTelefoneCliente(cliente.getIdCliente());
            enderecoService.excluirEndereco(cliente.getIdCliente());
            return null;
        }

        if(cliente != null){
            List<EnderecoCadastro> enderecos = cliente.getEnderecos().stream().map(EnderecoCadastro::new).toList();
            List<TelefoneCadastro> telefones = cliente.getTelefones().stream().map(TelefoneCadastro::new).toList();

            // FALTA IMPLEMENTAR para excluir pedidos tbm

            return new DadosClienteExclusaoDto(cliente, enderecos, telefones);
        }

        return null;

    }

    @Override
    public Cliente buscarClientePorNome(String nome){return repository.findByNome(nome);}

    private DadosClienteDto mapperClienteDto(Cliente cliente) {
        cliente = verificaClientesAtivos(cliente);
        if(cliente != null){
            return new DadosClienteDto(cliente);
        }
        return null;
    }

    private Cliente verificaClientesAtivos(Cliente cliente){
        if (cliente == null || cliente.getDeletadoEm() != null){
            return null;
        } else {
            return cliente;
        }
    }

}
