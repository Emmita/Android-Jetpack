package com.example.jetpack.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.jetpack.R
import com.example.jetpack.viewModel.ListViewModel
import kotlinx.android.synthetic.main.fragment_list.*

/**
 * A simple [Fragment] subclass.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel: ListViewModel
    private val dogsListAdapter = DogsListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refreshLayout.setOnRefreshListener {

            dogsList.visibility = View.GONE
            listError.visibility = View.GONE
            progressBar.visibility=  View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false

        }

        observeViewModel()

    }

    fun observeViewModel(){

        viewModel.dogs.observe(viewLifecycleOwner, Observer { dogs ->

            dogs?.let {
                dogsList.visibility = View.VISIBLE
                dogsListAdapter.updateDogList(dogs)
            }

        })

        viewModel.dogsLoadError.observe(viewLifecycleOwner, Observer { isError ->

            isError?.let {
                listError.visibility = if (it) View.VISIBLE else View.GONE

            }

        })

        viewModel.loading.observe(viewLifecycleOwner, Observer { loading ->

            loading?.let {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE

                if (it){

                    listError.visibility = View.GONE
                    dogsList.visibility = View.GONE

                }
            }

        })

    }
}
