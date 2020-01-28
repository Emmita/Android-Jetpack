package com.example.jetpack.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.jetpack.model.DogBreed

class DetailViewModel: ViewModel() {

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(){

        val dog = DogBreed("1", "Corgi", "15 tears", "breed group", "bredfor", "manso", "")
        dogLiveData.value = dog

    }

}