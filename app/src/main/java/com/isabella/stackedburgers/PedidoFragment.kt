package com.isabella.stackedburgers

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.graphics.Typeface
import androidx.fragment.app.Fragment

class PedidoFragment : Fragment(R.layout.fragment_pedido) {

    private var pedidos = mutableListOf<Pedido>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            val nome = bundle.getString("nome")
            val hamburguer = bundle.getString("hamburguer")
            val pontoCarne = bundle.getString("pontoCarne")
            val observacao = bundle.getString("observacao") ?: ""
            val adicionais = mutableListOf<String>()

            if (bundle.getBoolean("adicional_queijo", false)) {
                adicionais.add("Queijo Extra")
            }
            if (bundle.getBoolean("adicional_molho", false)) {
                adicionais.add("Molho Especial")
            }
            if (!nome.isNullOrEmpty() && !hamburguer.isNullOrEmpty() && !pontoCarne.isNullOrEmpty()) {
                val novoPedido = Pedido(nome, hamburguer, pontoCarne, observacao, adicionais)
                pedidos.add(novoPedido)
            }
        }

        for (pedido in pedidos) {
            adicionarPedidoView(pedido)
        }
    }

    private fun adicionarPedidoView(pedido: Pedido) {
        val pedidosContainer = view?.findViewById<LinearLayout>(R.id.pedidosContainer)

        val pedidoLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setPadding(0, 0, 0, 16)
        }

        pedidoLayout.addView(TextView(requireContext()).apply {
            text = "Nome: ${pedido.nome}"
            textSize = 18f
            setTypeface(null, Typeface.BOLD)
        })
        pedidoLayout.addView(TextView(requireContext()).apply {
            text = "Hambúrguer: ${pedido.hamburguer}"
            textSize = 16f
        })
        pedidoLayout.addView(TextView(requireContext()).apply {
            text = "Ponto da Carne: ${pedido.pontoCarne}"
            textSize = 16f
        })
        pedidoLayout.addView(TextView(requireContext()).apply {
            text = "Adicionais: ${pedido.adicionais.joinToString(", ")}"
            textSize = 16f
        })
        pedidoLayout.addView(TextView(requireContext()).apply {
            text = "Observação: ${pedido.observacao}"
            textSize = 16f
        })

        val buttonLayout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }

        val btnAtualizar = Button(requireContext()).apply {
            text = "Atualizar"
            setOnClickListener {
                editarPedido(pedido)
            }
        }

        val btnDeletar = Button(requireContext()).apply {
            text = "Deletar"
            setOnClickListener {
                deletarPedido(pedido)
                pedidosContainer?.removeView(pedidoLayout)
            }
        }
        buttonLayout.addView(btnAtualizar)
        buttonLayout.addView(btnDeletar)

        pedidosContainer?.addView(pedidoLayout)
        pedidosContainer?.addView(buttonLayout)
    }

    private fun deletarPedido(pedido: Pedido) {
        pedidos.remove(pedido)
    }

    private fun editarPedido(pedido: Pedido) {
        val cadastrarFragment = CadastrarFragment().apply {
            arguments = Bundle().apply {
                putString("nome", pedido.nome)
                putString("hamburguer", pedido.hamburguer)
                putString("pontoCarne", pedido.pontoCarne)
                putString("observacao", pedido.observacao)
                putStringArrayList("adicionais", ArrayList(pedido.adicionais))
            }
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView2, cadastrarFragment)
            .addToBackStack(null)
            .commit()
    }
}
