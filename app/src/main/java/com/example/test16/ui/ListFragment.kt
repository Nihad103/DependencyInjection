package com.example.test16.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test16.adapter.RecyclerViewAdapter
import com.example.test16.databinding.FragmentListBinding
import com.example.test16.viewmodel.CryptoViewModel
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.core.scope.Scope

class ListFragment : Fragment(), AndroidScopeComponent {

    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private var cryptoAdapter = RecyclerViewAdapter(arrayListOf())
    val viewModel by viewModel<CryptoViewModel>()
    override val scope: Scope by fragmentScope()
    private val example by inject<String>(qualifier(name = "TheEnd"))



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(this.context)
        binding.recyclerView.layoutManager = layoutManager
       // viewModel = ViewModelProvider(this).get(CryptoViewModel::class.java)
        viewModel.getDataFromApi()
        observeLiveData()
        println(example)
    }


    fun observeLiveData() {
        viewModel.cryptoList.observe(viewLifecycleOwner, Observer {
            it?.let {
                cryptoAdapter = RecyclerViewAdapter(ArrayList(it.data ?: arrayListOf()))
                binding.recyclerView.adapter = cryptoAdapter
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}