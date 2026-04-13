package com.fag.lucasmartins.arquitetura_software.core.domain.bo;

import com.fag.lucasmartins.arquitetura_software.core.domain.exceptions.DomainException;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class PessoaBO {

    private static final int IDADE_MINIMA = 18;
    private static final int TAMANHO_CPF = 11;
    private static final int TAMANHO_TELEFONE = 11;

    private final UUID id;
    private final String nomeCompleto;
    private final String cpf;
    private final LocalDate dataNascimento;
    private final String email;
    private final String telefone;

    private PessoaBO(UUID id,
                     String nomeCompleto,
                     String cpf,
                     LocalDate dataNascimento,
                     String email,
                     String telefone) {
        validarId(id);
        this.nomeCompleto = normalizarTexto(nomeCompleto);
        this.cpf = normalizarTexto(cpf);
        this.dataNascimento = dataNascimento;
        this.email = normalizarTexto(email);
        this.telefone = normalizarTexto(telefone);

        validarCamposObrigatorios();
        validarMaioridade();
        validarCpf();
        validarEmail();
        validarTelefone();

        this.id = id;
    }

    public static PessoaBO criar(String nomeCompleto,
                                 String cpf,
                                 LocalDate dataNascimento,
                                 String email,
                                 String telefone) {
        return new PessoaBO(UUID.randomUUID(), nomeCompleto, cpf, dataNascimento, email, telefone);
    }

    public static PessoaBO reconstituir(UUID id,
                                        String nomeCompleto,
                                        String cpf,
                                        LocalDate dataNascimento,
                                        String email,
                                        String telefone) {
        return new PessoaBO(id, nomeCompleto, cpf, dataNascimento, email, telefone);
    }

    public UUID getId() {
        return id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    private void validarId(UUID id) {
        if (id == null) {
            throw new DomainException("Id da pessoa deve ser informado.");
        }
    }

    private void validarCamposObrigatorios() {
        if (nomeCompleto == null || nomeCompleto.isEmpty()) {
            throw new DomainException("Nome completo deve ser informado.");
        }
        if (cpf == null || cpf.isEmpty()) {
            throw new DomainException("CPF deve ser informado.");
        }
        if (dataNascimento == null) {
            throw new DomainException("Data de nascimento deve ser informada.");
        }
        if (email == null || email.isEmpty()) {
            throw new DomainException("E-mail deve ser informado.");
        }
        if (telefone == null || telefone.isEmpty()) {
            throw new DomainException("Telefone deve ser informado.");
        }
    }

    private void validarMaioridade() {
        if (Period.between(dataNascimento, LocalDate.now()).getYears() < IDADE_MINIMA) {
            throw new DomainException("Idade minima de 18 anos nao atendida.");
        }
    }

    private void validarCpf() {
        if (!cpf.matches("\\d{" + TAMANHO_CPF + "}")) {
            throw new DomainException("CPF deve conter 11 digitos.");
        }
    }

    private void validarEmail() {
        if (!email.contains("@")) {
            throw new DomainException("E-mail invalido.");
        }
    }

    private void validarTelefone() {
        if (!telefone.matches("\\d{" + TAMANHO_TELEFONE + "}")) {
            throw new DomainException("Telefone deve conter 11 digitos.");
        }
    }

    private String normalizarTexto(String valor) {
        if (valor == null) {
            return null;
        }
        return valor.trim();
    }
}
