package com.android.classiccarselling.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignUpVM extends ViewModel {

    private FirebaseAuth firebaseAuth;

    public MutableLiveData<FirebaseUser> newUser = new MutableLiveData<>();
    public MutableLiveData<Exception> error = new MutableLiveData<>();

    public SignUpVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        newUser.setValue(firebaseAuth.getCurrentUser());

    }

    public void signUp(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {

                error.setValue(task.getException());
                return;
            }

            newUser.setValue(Objects.requireNonNull(task.getResult()).getUser());
        });
    }
}
