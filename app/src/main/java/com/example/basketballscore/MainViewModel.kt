package com.example.basketballscore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    // se ponen privadas para que solo puedan accederse aqui y el activity lo consume a traves del liveData de solo lectura que se declara en el encapsulamiento
    private var _localScore: MutableLiveData<Int> = MutableLiveData()
    private var _visitorScore: MutableLiveData<Int> = MutableLiveData()

    //Encapsulamiento para evitar la escritura del LiveDataMutable desde el activity. Se pone como publico el LiveData comun (solo lectura):
    val localScore: LiveData<Int>
        get() = _localScore

    val visitorScore: LiveData<Int>
        get() = _visitorScore


    init {          // para garantizar que no sean nulos se llama al reset siempre al comienzo así ya le asigna "0"
        resetPoints()
    }

    fun resetPoints() {
        _visitorScore.value = 0
        _localScore.value = 0
    }

    fun addPoint(point: Int, localTeam: Boolean) {
        if (localTeam) {
            _localScore.value =
                _localScore.value?.plus(point)   // .plus(point) es igual a poner " + point" solo que queda mejor
        } else {
            _visitorScore.value = _visitorScore.value?.plus(point)
        }
    }

    fun subtractPoint(point: Int, localTeam: Boolean) {
        if (localTeam) {
            if (_localScore.value!! <= 0) _localScore.value = 0 else _localScore.value =
                _localScore.value?.minus(point)  // .minus(point) es igual a poner " - point" solo que queda mejor
        } else {
            if (_visitorScore.value!! <= 0) _visitorScore.value = 0 else _visitorScore.value =
                _visitorScore.value?.minus(point)
        }
    }
}