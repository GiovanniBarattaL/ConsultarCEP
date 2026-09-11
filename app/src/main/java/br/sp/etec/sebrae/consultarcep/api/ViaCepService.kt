package br.sp.etec.sebrae.consultarcep.api

import br.sp.etec.sebrae.consultarcep.model.ResponseEndereco
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {

    @GET("/ws/{cep}/json/")
    suspend fun buscarEndereco(
        @Path("cep") cep: String
    ): ResponseEndereco
}

