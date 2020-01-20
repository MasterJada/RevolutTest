package com.jetlaunch.revoluttest.converter

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.jetlaunch.revoluttest.R
import com.jetlaunch.revoluttest.databinding.ConverterFragmentBinding
import com.jetlaunch.revoluttest.models.Currency
import kotlinx.android.synthetic.main.converter_fragment.*

class ConverterFragment : Fragment() {

    companion object {
        fun newInstance() = ConverterFragment()
    }

    private lateinit var viewModel: ConverterViewModel
    private lateinit var binding: ConverterFragmentBinding
    private val adapter by lazy { CurrencyAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ConverterFragmentBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ConverterViewModel::class.java)
        binding.vm = viewModel

        //initial state
        viewModel.baseCurrency.postValue(Currency("EUR", 1F, true))
        viewModel.mediatorLiveData.observe(viewLifecycleOwner, Observer {
            if(it != null)
            adapter.data =  it
        })
        rv_currency.layoutManager = LinearLayoutManager(context)
        rv_currency.adapter = adapter
        adapter.setupClickListener {
            viewModel.setupBaseCurrency(it)
        }


        binding.include.etRate.addTextChangedListener {
            viewModel.upd(it.toString())
        }
    }

}
