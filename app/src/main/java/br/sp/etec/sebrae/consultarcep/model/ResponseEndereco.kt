package br.sp.etec.sebrae.consultarcep.model

data class ResponseEndereco(
    val logradouro : String,
    val bairro : String,
    val uf : String,
    val localidade: String,
    val ddd : String
)
