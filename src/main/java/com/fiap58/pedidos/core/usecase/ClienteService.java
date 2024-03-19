package com.fiap58.pedidos.core.usecase;

import com.fiap58.pedidos.gateway.ClienteRepository;
import com.fiap58.pedidos.presenters.dto.entrada.DadosClienteCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.EnderecoCadastro;
import com.fiap58.pedidos.presenters.dto.entrada.TelefoneCadastro;
import com.fiap58.pedidos.presenters.dto.saida.DadosClienteDto;
import com.fiap58.pedidos.core.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private TelefoneService telefoneService;

    public Cliente cadastrarCliente(Cliente cliente){
        cliente.setCriadoEm(Instant.now());
        cliente.setAtualizadoEm(Instant.now());
        return repository.save(cliente);
    }

    public DadosClienteDto cadastrarCliente(DadosClienteCadastro dto){
        if(buscarClientePorCpf(dto.cpf()) == null){
            Cliente cliente = new Cliente(dto);
            Cliente clienteCadastrado = repository.save(cliente);
            for(EnderecoCadastro enderecoCadastro : dto.enderecos()){
                enderecoService.cadstrarEndereco(enderecoCadastro, clienteCadastrado);
            }
            for(TelefoneCadastro telefoneCadastro : dto.telefones()){
                telefoneService.cadastrarTelefone(telefoneCadastro, clienteCadastrado);
            }
            return mapperClienteDto(cliente);
        } else {
            return null;
        }
    }

    public DadosClienteDto retornaClienteCpf(String cpf){
        return mapperClienteDto(buscarClientePorCpf(cpf));
    }

    public DadosClienteDto retornaClienteId(Long id){
        return mapperClienteDto(buscarClientePorId(id));
    }

    public Cliente buscarClientePorCpf(String cpf) {
        return repository.findByCpf(cpf);
    }
    public Cliente buscarClientePorId(Long id) {
        return repository.findById(id).orElse(null);
    }
    public List<DadosClienteDto> listarClientes() {
        return repository.findAll().stream().filter(cliente -> cliente.getDeletadoEm() == null).map(DadosClienteDto::new).toList();
    }

    private DadosClienteDto mapperClienteDto(Cliente cliente){
        return new DadosClienteDto(cliente);
    }

    private List<DadosClienteDto> mapperListClienteDto(List<Cliente> clientes){
        return clientes.stream().map(this::mapperClienteDto).collect(Collectors.toList());
    }

}
