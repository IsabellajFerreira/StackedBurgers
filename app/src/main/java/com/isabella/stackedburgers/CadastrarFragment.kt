package com.isabella.stackedburgers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CadastrarFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CadastrarFragment : Fragment(R.layout.fragment_cadastrar) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinnerHamburguer(view)
        setupSpinnerPontoCarne(view)


        val btnEnviar: Button = view.findViewById(R.id.btnEnviar)
        btnEnviar.setOnClickListener {
            selecionarItensSpinner(view)
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


    private fun selecionarItensSpinner(view: View) {
        val spinnerHamburguer: Spinner = view.findViewById(R.id.spinnerHambuguer)
        val spinnerPontoCarne: Spinner = view.findViewById(R.id.spinnerCarne)

        val hamburguerSelecionado = spinnerHamburguer.selectedItem
        val pontoCarneSelecionado = spinnerPontoCarne.selectedItem

        val resultado: TextView = view.findViewById(R.id.txtResultado)
        resultado.text = "Hambúrguer: $hamburguerSelecionado\nPonto da Carne: $pontoCarneSelecionado"
    }
}

