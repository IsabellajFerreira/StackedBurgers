package com.isabella.stackedburgers

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Spinner

class CadastrarFragment : Fragment(R.layout.fragment_cadastrar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinnerHamburguer(view)
        setupSpinnerPontoCarne(view)

        val btnEnviar: Button = view.findViewById(R.id.btnEnviar)
        val editTextNome: EditText = view.findViewById(R.id.editTextText)
        val editTextObservacao: EditText = view.findViewById(R.id.editTextText2)
        val checkQueijo: CheckBox = view.findViewById(R.id.checkBoxQueijo)
        val checkMolho: CheckBox = view.findViewById(R.id.checkBoxMolho)

        btnEnviar.setOnClickListener {
            val nome = editTextNome.text.toString()
            val observacao = editTextObservacao.text.toString()
            val spinnerHamburguer: Spinner = view.findViewById(R.id.spinnerHambuguer)
            val spinnerPontoCarne: Spinner = view.findViewById(R.id.spinnerCarne)
            val hamburguerSelecionado = spinnerHamburguer.selectedItem?.toString() ?: ""
            val pontoCarneSelecionado = spinnerPontoCarne.selectedItem?.toString() ?: ""

            Log.d("CadastrarFragment", "Nome: $nome, Hamburguer: $hamburguerSelecionado, Ponto da Carne: $pontoCarneSelecionado, Observação: $observacao")

            if (nome.isNotEmpty() && hamburguerSelecionado.isNotEmpty() && pontoCarneSelecionado.isNotEmpty()) {
                val bundle = Bundle().apply {
                    putString("nome", nome)
                    putString("hamburguer", hamburguerSelecionado)
                    putString("pontoCarne", pontoCarneSelecionado)
                    putString("observacao", observacao)
                    putBoolean("adicional_queijo", checkQueijo.isChecked)
                    putBoolean("adicional_molho", checkMolho.isChecked)
                }

                val pedidoFragment = PedidoFragment().apply {
                    arguments = bundle
                }

                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView2,pedidoFragment)
                    .addToBackStack(null)
                    .commit()
            } else {
                Log.e("CadastrarFragment", "Campos obrigatórios não preenchidos.")
            }
        }
    }
    private fun setupSpinnerHamburguer(view: View) {
        val hamburguers = listOf("Hambúrguer de Carne", "Hambúrguer de Frango", "Hambúrguer Vegetariano")
        val spinnerHamburguer: Spinner = view.findViewById(R.id.spinnerHambuguer)
        spinnerHamburguer.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            hamburguers
        )
    }
    private fun setupSpinnerPontoCarne(view: View) {
        val pontos = listOf("Mal Passado", "Ao Ponto", "Bem Passado")
        val spinnerPontoCarne: Spinner = view.findViewById(R.id.spinnerCarne)
        spinnerPontoCarne.adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            pontos
        )
    }
}
