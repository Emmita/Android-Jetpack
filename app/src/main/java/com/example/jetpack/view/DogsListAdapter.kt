package com.example.jetpack.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.jetpack.R
import com.example.jetpack.model.DogBreed
import kotlinx.android.synthetic.main.item_dog.view.*

class DogsListAdapter(val dogList: ArrayList<DogBreed>):
    RecyclerView.Adapter<DogsListAdapter.ViewHolder>() {

    fun updateDogList(newDogList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsListAdapter.ViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_dog, parent, false)
        return ViewHolder(view)

    }

    override fun getItemCount(): Int {

        return dogList.size

    }

    override fun onBindViewHolder(holder: DogsListAdapter.ViewHolder, position: Int) {

        holder.view.name.text = dogList[position].dogBreed
        holder.view.lifespan.text = dogList[position].lifespan
        holder.view.setOnClickListener {
            Navigation.findNavController(it).navigate(ListFragmentDirections.actionDetailFragment())
        }

    }

    class ViewHolder(var view: View): RecyclerView.ViewHolder(view)

}