package com.seisme.dimas.ui.screens.mapScreen


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import com.seisme.dimas.data.model.EarthquakeData
import com.seisme.dimas.data.model.ShakeReport
import com.seisme.dimas.data.model.UserData
import com.seisme.dimas.data.repository.MapRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: MapRepository,
    private val locationProvider: LocationProvider,
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _userLocation = MutableStateFlow<LatLng?>(null)
    val userLocation: StateFlow<LatLng?> get() = _userLocation

    private val _earthquakeData = MutableLiveData<EarthquakeData?>()
    val earthquakeData: LiveData<EarthquakeData?> get() = _earthquakeData

    private val _shakingReports = mutableStateOf<List<ShakeReport>>(emptyList())
    val shakingReports: State<List<ShakeReport>> get() = _shakingReports

    private val _members = MutableLiveData<List<UserData>>()
    val members: LiveData<List<UserData>> = _members

    init {
        fetchUserLocation()
    }

    fun fetchLatestEarthquake() {
        viewModelScope.launch {
            try {
                val data = repository.getLatestEarthquake()
                _earthquakeData.value = data
            } catch (e: Exception) {
                // Handle error bisa ditambahkan nanti bisa berupa ui atau log
            }
        }
    }

    fun fetchRecentShakeReports() {
        viewModelScope.launch {
            _shakingReports.value = repository.getRecentShakeReports()
        }
    }

    private fun fetchUserLocation() {
        viewModelScope.launch {
            _userLocation.value = locationProvider.getCurrentLocation()
        }
    }

    fun fetchMembers() {
        val userId = auth.currentUser?.uid ?: return
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("users")
            .document(userId)
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Ambil daftar anggota (sebagai list of maps yang bisa dikonversi menjadi list of UserData)
                    val members = document.toObject(UserData::class.java)?.members ?: emptyList()

                    // Konversi setiap item di members ke UserData
                    val memberList = members.mapNotNull { memberMap ->
                        // Konversi dari Map<String, Any> ke UserData
                        try {
                            val memberJson = Gson().toJson(memberMap)  // Konversi map ke JSON
                            Gson().fromJson(memberJson, UserData::class.java)  // Konversi JSON ke UserData
                        } catch (e: Exception) {
                            Log.e("FetchMembersError", "Error converting member map to UserData", e)
                            null
                        }
                    }

                    // Update nilai LiveData atau State dengan daftar anggota
                    _members.value = memberList
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FetchUserError", "Error fetching user document", exception)
            }
    }

}
