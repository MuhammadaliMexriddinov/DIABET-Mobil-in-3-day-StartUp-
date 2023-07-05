package uz.alpha.qandlidiabetstartup.presentation.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.alpha.qandlidiabetstartup.data.local.SharedPref
import uz.alpha.qandlidiabetstartup.presentation.viewmodel.ProfileViewModel



class ProfileViewModelImpl : ViewModel(), ProfileViewModel {

    private val sharedPreference = SharedPref.getInstance()

    override val nameLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.getName())

    override val imageLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreference.getImage())

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeNameLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeImageLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val aboutUsLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val contactLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val supportLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val languageLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val informationLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val shareLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun shareApp() {
        shareLiveData.postValue(Unit)
    }
    override fun language() {
        languageLiveData.postValue(Unit)
    }

    override fun info() {
        informationLiveData.postValue(Unit)
    }

    override fun changeName() {
        changeNameLiveData.postValue(Unit)
    }

    override fun changeImage() {
        changeImageLiveData.postValue(Unit)
    }

    override fun aboutClicked() {
        aboutUsLiveData.postValue(Unit)
    }

    override fun helpClicked() {
        contactLiveData.postValue(Unit)
    }

    override fun supportClicked() {
        supportLiveData.postValue(Unit)
    }

    override fun setName(name: String) {
        sharedPreference.setName(name)
        nameLiveData.postValue(name)
    }

    override fun setImage() {

    }

    override fun setImage(str: String) {
        sharedPreference.setImage(str)
        imageLiveData.postValue(str)
    }

    override fun backClick() {
        backLiveData.postValue(Unit)
    }
}