package com.android.classiccarselling.viewmodel;

import static com.android.classiccarselling.global.Constants.CART_COLLECTION;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.classiccarselling.model.Cart;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class SignUpVM extends ViewModel {

    private final FirebaseAuth firebaseAuth;
    private final FirebaseFirestore db;

    public MutableLiveData<FirebaseUser> newUser = new MutableLiveData<>();
    public MutableLiveData<Cart> newUserCart = new MutableLiveData<>();

    public MutableLiveData<Exception> error = new MutableLiveData<>();

    public SignUpVM() {

        firebaseAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        newUser.setValue(firebaseAuth.getCurrentUser());

    }

    public void signUp(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {

            if (!task.isSuccessful()) {

                error.setValue(task.getException());
                return;
            }

            createNewUserCart(task.getResult().getUser().getUid());
        });
    }

    public void createNewUserCart(String uid) {

        Cart cart = new Cart(uid);

        db.collection(CART_COLLECTION)
                .document(uid)
                .set(cart)
                .addOnCompleteListener(task -> {

                    if (!task.isSuccessful()) {

                        error.setValue(task.getException());
                        return;
                    }

                    newUser.setValue(Objects.requireNonNull(firebaseAuth.getCurrentUser()));
                });
    }
}
