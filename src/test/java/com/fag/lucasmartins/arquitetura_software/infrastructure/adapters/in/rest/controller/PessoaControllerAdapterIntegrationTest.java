package com.fag.lucasmartins.arquitetura_software.infrastructure.adapters.in.rest.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PessoaControllerAdapterIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCadastrarPessoaValida() throws Exception {
        String requestBody = "{"
                + "\"nomeCompleto\":\"Maria da Silva\","
                + "\"cpf\":\"12345678901\","
                + "\"dataNascimento\":\"1990-05-20\","
                + "\"email\":\"maria.silva@example.com\","
                + "\"telefone\":\"41988887777\""
                + "}";

        mockMvc.perform(post("/api/v1/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.nomeCompleto").value("Maria da Silva"))
                .andExpect(jsonPath("$.cpf").value("12345678901"))
                .andExpect(jsonPath("$.dataNascimento").value("1990-05-20"))
                .andExpect(jsonPath("$.email").value("maria.silva@example.com"))
                .andExpect(jsonPath("$.telefone").value("41988887777"));
    }

    @Test
    void deveRetornarErroQuandoPessoaForMenorDeIdade() throws Exception {
        String requestBody = "{"
                + "\"nomeCompleto\":\"Joao da Silva\","
                + "\"cpf\":\"12345678901\","
                + "\"dataNascimento\":\"2010-05-20\","
                + "\"email\":\"joao.silva@example.com\","
                + "\"telefone\":\"41988887777\""
                + "}";

        mockMvc.perform(post("/api/v1/pessoas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Idade minima de 18 anos nao atendida."))
                .andExpect(jsonPath("$.status").value(400));
    }
}
