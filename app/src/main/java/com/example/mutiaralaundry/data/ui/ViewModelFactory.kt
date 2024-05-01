package com.example.mutiaralaundry.data.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mutiaralaundry.data.repository.UserRepository
import com.example.mutiaralaundry.data.ui.add.AddViewModel
import com.example.mutiaralaundry.data.ui.history.HistoryViewModel
import com.example.mutiaralaundry.data.ui.login.LoginViewModel
import com.example.mutiaralaundry.data.ui.register.RegisterViewModel
import com.example.mutiaralaundry.di.Injection

class ViewModelFactory private constructor(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory()  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(AddViewModel::class.java) -> {
                AddViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HistoryViewModel::class.java) -> {
                HistoryViewModel(repository) as T
            }


            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideUserRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}