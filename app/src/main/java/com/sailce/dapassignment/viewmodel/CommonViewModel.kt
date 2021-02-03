package com.sailce.dapassignment.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.sailce.dapassignment.db.RoomDataBase
import com.sailce.dapassignment.db.UserEntity

/*
    I have used same view Model for 3 screen.
    If required I can separete it.
 */

class CommonViewModel(app: Application): AndroidViewModel(app) {

    private  var allUsers : MutableLiveData<List<UserEntity>> = MutableLiveData()
    private var isInsert : MutableLiveData<String> = MutableLiveData("No")
    private var showUsers : MutableLiveData<Boolean> = MutableLiveData( false)
    private var insertUser : MutableLiveData<Boolean> = MutableLiveData( false)
    private var errorString : MutableLiveData<String> = MutableLiveData("No")
    private val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

    //return error text to user which observe for error text
    fun getErrorValidation(): MutableLiveData<String>
    {
        return errorString
    }
    fun getShowUser(): MutableLiveData<Boolean> {
        return showUsers
    }
    fun getinsertUser(): MutableLiveData<Boolean> {
        return insertUser
    }
    fun getAllUsersObservers(): MutableLiveData<List<UserEntity>> {
        return allUsers
    }
    fun getInsertObservers(): MutableLiveData<String> {
        return isInsert
    }
    fun getAllUsers() {
        val userDao = RoomDataBase.getAppDatabase((getApplication()))?.userDao()
        val list = userDao?.getAllUserInfo()
        allUsers.postValue(list)
    }
    //Calling for Insert user data from AddUserActivity
    fun insertUserInfo(entity: UserEntity){
      // checking first if any validation or not then insert in database
        var validate: Boolean = false
        var validationError: String = ""
        if (entity.name.equals("") || entity.phone.equals("") ||entity.address.equals("") || entity.email.equals("") ) {
            validate = false
            validationError = "No Filed need not empty"

        }
        else
        {
            if (entity.email.matches(emailPattern.toRegex()))
                validate = true
            else {
                validate = false
                validationError = "Please give valid Phone number"
                errorString.postValue(validationError)
            }

            val length: Int = entity.phone.toString().length
            if (length == 10)
                validate = true
            else {
                validate = false
                validationError = "Please give valid Phone number Length 10 digit"
                errorString.postValue(validationError)
            }

        }
      //according validation flag we insert in room
        if(validate == true){
            val userDao = RoomDataBase.getAppDatabase(getApplication())?.userDao()
            userDao?.insertUser(entity)
            isInsert.postValue("User Data Inserted successfully") //problem need to check
        }
        else{
            //While error happen we update errorString mutable live data which is observer for shing
            // error toast to user
            errorString.postValue(validationError)
        }
    }
    fun openUserListing(){
       showUsers.postValue(true)
   }
    fun openUserInsert(){
        insertUser.postValue(true)
    }

}