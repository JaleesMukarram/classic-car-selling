package com.android.classiccarselling.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class SignInVM extends ViewModel {

    private final FirebaseAuth firebaseAuth;

    public MutableLiveData<FirebaseUser> signedInUser = new MutableLiveData<>();
    public MutableLiveData<Exception> error = new MutableLiveData<>();

    public SignInVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        signedInUser.setValue(firebaseAuth.getCurrentUser());

    }

    public void signIn(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {

                error.setValue(task.getException());
                return;
            }

            signedInUser.setValue(Objects.requireNonNull(task.getResult()).getUser());
        });
    }


}
