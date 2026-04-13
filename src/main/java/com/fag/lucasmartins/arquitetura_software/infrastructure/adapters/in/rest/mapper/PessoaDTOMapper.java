package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.mapper;

import com.fag.lucasmartins.arquitetura_software.core.domain.bo.PessoaBO;
import com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.dto.PessoaDTO;

public class PessoaDTOMapper {

    private PessoaDTOMapper() {
    }

    public static PessoaBO toBo(PessoaDTO dto) {
        if (dto.getId() != null) {
            return PessoaBO.reconstituir(
                    dto.getId(),
                    dto.getNomeCompleto(),
                    dto.getCpf(),
                    dto.getDataNascimento(),
                    dto.getEmail(),
                    dto.getTelefone()
            );
        }

        return PessoaBO.criar(
                dto.getNomeCompleto(),
                dto.getCpf(),
                dto.getDataNascimento(),
                dto.getEmail(),
                dto.getTelefone()
        );
    }

    public static PessoaDTO toDto(PessoaBO bo) {
        PessoaDTO dto = new PessoaDTO();
        dto.setId(bo.getId());
        dto.setNomeCompleto(bo.getNomeCompleto());
        dto.setCpf(bo.getCpf());
        dto.setDataNascimento(bo.getDataNascimento());
        dto.setEmail(bo.getEmail());
        dto.setTelefone(bo.getTelefone());

        return dto;
    }
}
