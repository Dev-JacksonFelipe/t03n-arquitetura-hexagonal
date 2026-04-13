package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.out.persistence.h2.entity.PessoaEntity;

public class PessoaMapper {

    private PessoaMapper() {
    }

    public static PessoaEntity toEntity(PessoaBO pessoaBO) {
        PessoaEntity pessoaEntity = new PessoaEntity();
        pessoaEntity.setId(pessoaBO.getId());
        pessoaEntity.setNomeCompleto(pessoaBO.getNomeCompleto());
        pessoaEntity.setCpf(pessoaBO.getCpf());
        pessoaEntity.setDataNascimento(pessoaBO.getDataNascimento());
        pessoaEntity.setEmail(pessoaBO.getEmail());
        pessoaEntity.setTelefone(pessoaBO.getTelefone());

        return pessoaEntity;
    }

    public static PessoaBO toBO(PessoaEntity pessoaEntity) {
        return PessoaBO.reconstituir(
                pessoaEntity.getId(),
                pessoaEntity.getNomeCompleto(),
                pessoaEntity.getCpf(),
                pessoaEntity.getDataNascimento(),
                pessoaEntity.getEmail(),
                pessoaEntity.getTelefone()
        );
    }
}
