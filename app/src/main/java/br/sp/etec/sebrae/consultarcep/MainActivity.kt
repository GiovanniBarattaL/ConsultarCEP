package br.sp.etec.sebrae.consultarcep

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import br.sp.etec.sebrae.consultarcep.api.ViaCepClient
import br.sp.etec.sebrae.consultarcep.api.ViaCepService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtCep = findViewById<EditText>(R.id.edtCep)
        val btnConsultar = findViewById<Button>(R.id.btnConsultar)
        val txtLogradouro = findViewById<EditText>(R.id.txtLogradouro)
        val txtBairro = findViewById<EditText>(R.id.txtBairro)
        val txtCidade = findViewById<EditText>(R.id.txtCidade)
        val txtUf = findViewById<EditText>(R.id.txtUf)
        val txtDdd = findViewById<EditText>(R.id.txtDdd)


        btnConsultar.setOnClickListener {
            val cep = edtCep.text.toString()
            if (cep.length != 8) {
                Toast.makeText(this, "CEP inválido.", Toast.LENGTH_LONG).show()
            }
            lifecycleScope.launch {

                try {
                    val endereco = ViaCepClient.instance.buscarEndereco(cep)
                    txtLogradouro.setText(endereco.logradouro)
                    txtBairro.setText(endereco.bairro)
                    txtCidade.setText(endereco.localidade)
                    txtUf.setText(endereco.uf)
                    txtDdd.setText(endereco.ddd)
                }catch (e: Exception){
                    Toast.makeText(
                        this@MainActivity,
                        "Erro ao Consultar CEP",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
        }

    }
