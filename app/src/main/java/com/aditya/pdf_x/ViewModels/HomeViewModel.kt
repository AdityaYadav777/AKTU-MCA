package com.aditya.pdf_x.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aditya.pdf_x.Models.AllQuestionModel
import com.aditya.pdf_x.Models.HeadLineCollection
import com.aditya.pdf_x.Models.NoteModel
import com.aditya.pdf_x.Models.SemesterModel
import com.aditya.pdf_x.Models.SliderModel
import com.aditya.pdf_x.Models.SubjectModel
import com.aditya.pdf_x.Utils
import com.google.ai.client.generativeai.GenerativeModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val db: FirebaseFirestore, val ai: GenerativeModel) :
    ViewModel() {

    private val _semesterData = MutableStateFlow<List<SemesterModel>>(emptyList())
    val semeterData = _semesterData.asStateFlow()


    private val _subjects = MutableStateFlow<List<SubjectModel>>(emptyList())
    val subjects = _subjects.asStateFlow()


    private val _allQuestions = MutableStateFlow<List<AllQuestionModel>>(emptyList())
    val allQuestions = _allQuestions.asStateFlow()


    private val _slider = MutableStateFlow<List<SliderModel>>(emptyList())
    val slider = _slider.asStateFlow()

    private val _aiRespose = MutableStateFlow<List<String>>(emptyList())
    val aiResponse = _aiRespose.asStateFlow()

    var isLoading = MutableStateFlow<Boolean>(false)

    val _headlineCollection= MutableStateFlow<List<HeadLineCollection>>(emptyList())
    val headLineCollection=_headlineCollection.asStateFlow()

    private val _getNotes=MutableStateFlow<List<NoteModel>>(emptyList())
    val getNote=_getNotes.asStateFlow()


    init {
        getAllSemester()
        getSliders()

    }


    fun getAiResponse(message: String) {

        isLoading.value = true
        viewModelScope.launch {
            try {
                val data = ai.startChat()
                val response = data.sendMessage(message)
                val textResponse = response.text.orEmpty()  // Avoids null issues

                _aiRespose.value = listOf(textResponse)  // Directly assigning an immutable list

                Log.i("KELA", textResponse)
            } catch (e: Exception) {
                Log.e("KELA", "Error in AI response", e)
                _aiRespose.value = listOf("Failed to fetch response") // Default error message
            } finally {
                isLoading.value = false
            }
        }
    }


    fun getAllSemester() {
        viewModelScope.launch {
            db.collection("MCA Semesters").addSnapshotListener { value, error ->
                val data = value?.toObjects(SemesterModel::class.java)
                _semesterData.value = data!!
            }
        }
    }


    fun getSubjects(name: String) {
        viewModelScope.launch {
            db.collection("MCA Semesters").document(name).collection(name)
                .addSnapshotListener { value, error ->
                    val data = value?.toObjects(SubjectModel::class.java)
                    _subjects.value = data!!
                }
        }
    }


    fun getAllQuestions(name: String) {
        viewModelScope.launch {
            db.collection("MCA Semesters").document(Utils.sememsterName!!)
                .collection(Utils.sememsterName!!).document(name).collection(name)
                .addSnapshotListener { value, error ->
                    val data = value?.toObjects(AllQuestionModel::class.java)
                    _allQuestions.value = data!!
                }
        }
    }


    fun getAllNotes(name: String) {
        viewModelScope.launch {
            db.collection("MCA Semesters").document(Utils.sememsterName!!)
                .collection(Utils.sememsterName!!).document(name).collection("Notes")
                .addSnapshotListener { value, error ->
                    val data = value?.toObjects(NoteModel::class.java)
                    data?.let {
                        _getNotes.value=it
                    }
                }
        }
    }


    fun getSliders() {
        viewModelScope.launch {
            db.collection("Sliders").addSnapshotListener { value, error ->
                val data = value?.toObjects(SliderModel::class.java)
                _slider.value = data!!
            }
        }
    }




    fun getHeadlineCollection(collection:String) {
        _headlineCollection.value= emptyList()
        viewModelScope.launch {
            db.collection(collection).addSnapshotListener { value, error ->
                val data = value?.toObjects(HeadLineCollection::class.java)
                _headlineCollection.value=data!!
            }
        }
    }



    override fun onCleared() {
        super.onCleared()
        db.clearPersistence()
    }


}