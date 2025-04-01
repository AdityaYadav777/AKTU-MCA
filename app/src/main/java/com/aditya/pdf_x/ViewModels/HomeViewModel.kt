package com.aditya.pdf_x.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.pdf_x.Models.AllQuestionModel
import com.aditya.pdf_x.Models.SemesterModel
import com.aditya.pdf_x.Models.SliderModel
import com.aditya.pdf_x.Models.SubjectModel
import com.aditya.pdf_x.Utils
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val db:FirebaseFirestore):ViewModel() {

    private val _semesterData=MutableStateFlow<List<SemesterModel>>(emptyList())
    val semeterData= _semesterData.asStateFlow()


    private val _subjects=MutableStateFlow<List<SubjectModel>>(emptyList())
    val subjects=_subjects.asStateFlow()


    private val _allQuestions=MutableStateFlow<List<AllQuestionModel>>(emptyList())
    val allQuestions=_allQuestions.asStateFlow()


    private val _slider=MutableStateFlow<List<SliderModel>>(emptyList())
    val slider=_slider.asStateFlow()



    init {
        getAllSemester()
        getSliders()

    }


    fun getAllSemester(){
     viewModelScope.launch {
         db.collection("MCA Semesters").addSnapshotListener { value, error ->
             val data=value?.toObjects(SemesterModel::class.java)
             _semesterData.value=data!!
         }
     }
    }




    fun getSubjects(name:String){
        viewModelScope.launch {
            db.collection("MCA Semesters").document(name).collection(name).addSnapshotListener { value, error ->
            val data=value?.toObjects(SubjectModel::class.java)
                _subjects.value=data!!
            }
        }
    }




    fun getAllQuestions(name:String){
        viewModelScope.launch {
            db.collection("MCA Semesters").document(Utils.sememsterName!!).collection(Utils.sememsterName!!).document(name).collection(name).addSnapshotListener { value, error ->
                val data=value?.toObjects(AllQuestionModel::class.java)
               _allQuestions.value=data!!

            }
        }
    }




    fun getSliders(){
        viewModelScope.launch {
            db.collection("Sliders").addSnapshotListener { value, error ->
                val data=value?.toObjects(SliderModel::class.java)
                _slider.value=data!!
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
        db.clearPersistence()

    }




}