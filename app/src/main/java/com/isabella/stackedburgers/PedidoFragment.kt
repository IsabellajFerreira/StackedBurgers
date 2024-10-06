package com.isabella.stackedburgers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView

class PedidoFragment : Fragment(R.layout.fragment_pedido) {


    private var nome: String? = null
    private var hamburguer: String? = null
    private var pontoCarne: String? = null
    private var observacao: String? = null
    private var adicionais: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
            nome = it.getString("nome")
            hamburguer = it.getString("hamburguer")
            pontoCarne = it.getString("pontoCarne")
            observacao = it.getString("observacao")

            if (it.getBoolean("adicional_queijo", false)) adicionais += "Queijo Extra\n"
            if (it.getBoolean("adicional_molho", false)) adicionais += "Molho Especial\n"
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val txtResultado: TextView = view.findViewById(R.id.txtResultado)

        txtResultado.text = """
            Nome: $nome
            Hambúrguer: $hamburguer
            Ponto da Carne: $pontoCarne
            Observação: $observacao
            Adicionais: $adicionais
        """.trimIndent()
    }
}
