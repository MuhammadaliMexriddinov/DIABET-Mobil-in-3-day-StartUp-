package uz.alpha.qandlidiabetstartup.presentation.viewmodel

import androidx.lifecycle.LiveData

interface ProfileViewModel {

    val nameLiveData: LiveData<String>

    val imageLiveData: LiveData<String>

    val backLiveData: LiveData<Unit>

    val changeNameLiveData: LiveData<Unit>

    val changeImageLiveData: LiveData<Unit>

    val aboutUsLiveData: LiveData<Unit>

    val contactLiveData: LiveData<Unit>

    val supportLiveData: LiveData<Unit>

    val  languageLiveData : LiveData<Unit>

    val  informationLiveData : LiveData<Unit>

    val  shareLiveData : LiveData<Unit>

    fun shareApp()

    fun language()

    fun info()

    fun changeName()

    fun changeImage()

    fun aboutClicked()

    fun helpClicked()

    fun supportClicked()

    fun setName(name: String)

    fun setImage()

    fun backClick()

    fun setImage(str: String)

}